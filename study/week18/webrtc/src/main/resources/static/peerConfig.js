let localStreamElement = document.querySelector('#localStream');

// 자신을 식별하기 위한 고유 랜덤 키를 생성
const myKey = Math.random().toString(36).substring(2, 11);

let pcListMap = new Map(); // 다른 사용자와의 PeerConnection을 저장할 Map 객체
let roomId; // 현재 사용자가 접속한 방의 ID
let otherKeyList = []; // 다른 사용자의 키 목록
let localStream = undefined; // 로컬 스트림 데이터를 저장할 변수

// 카메라와 마이크를 활성화하는 함수
const startCam = async () => {
    if (navigator.mediaDevices !== undefined) {
        await navigator.mediaDevices.getUserMedia({ audio: true, video: true })
            .then(async (stream) => {
                console.log('Stream found');
                localStream = stream; // 로컬 스트림을 저장
                stream.getAudioTracks()[0].enabled = true; // 기본적으로 마이크를 활성화
                localStreamElement.srcObject = localStream; // 로컬 스트림을 HTML 요소에 연결
                document.querySelector('#myKey').innerHTML = myKey;
            })
            .catch(error => {
                console.error("Error accessing media devices:", error);
            });
    }
};

// WebSocket 연결 및 신호 처리 설정
const connectSocket = async () => {
    const socket = new SockJS('/signaling'); // SockJS를 사용하여 서버와 연결
    stompClient = Stomp.over(socket); // Stomp 클라이언트를 생성
    stompClient.debug = null; // 디버그 로그 비활성화

    stompClient.connect({}, function () {
        console.log('Connected to WebRTC server');

        // ICE 후보(peer 연결 정보를 위한 데이터) 교환을 위한 구독
        stompClient.subscribe(`/topic/peer/iceCandidate/${myKey}/${roomId}`, candidate => {
            const key = JSON.parse(candidate.body).key; // 상대방의 키를 추출
            const message = JSON.parse(candidate.body).body; // 전달받은 ICE 후보 정보를 추출
            pcListMap.get(key).addIceCandidate(new RTCIceCandidate({
                candidate: message.candidate,
                sdpMLineIndex: message.sdpMLineIndex,
                sdpMid: message.sdpMid
            })); // 해당 PeerConnection에 ICE 후보 추가
        });

        // Offer 메시지 수신 처리
        stompClient.subscribe(`/topic/peer/offer/${myKey}/${roomId}`, offer => {
            const key = JSON.parse(offer.body).key; // 상대방의 키
            const message = JSON.parse(offer.body).body; // Offer 메시지
            pcListMap.set(key, createPeerConnection(key)); // 새로운 PeerConnection 생성
            pcListMap.get(key).setRemoteDescription(new RTCSessionDescription({
                type: message.type,
                sdp: message.sdp
            })); // Offer를 설정
            sendAnswer(pcListMap.get(key), key); // Answer를 전송
        });

        // Answer 메시지 수신 처리
        stompClient.subscribe(`/topic/peer/answer/${myKey}/${roomId}`, answer => {
            const key = JSON.parse(answer.body).key; // 상대방의 키
            const message = JSON.parse(answer.body).body; // Answer 메시지
            pcListMap.get(key).setRemoteDescription(new RTCSessionDescription(message)); // RemoteDescription 설정
        });

        // 키 요청 신호 처리
        stompClient.subscribe(`/topic/call/key`, message => {
            stompClient.send(`/app/send/key`, {}, JSON.stringify(myKey)); // 자신의 키 전송
        });

        // 상대방의 키를 수신
        stompClient.subscribe(`/topic/send/key`, message => {
            const key = JSON.parse(message.body);
            if (myKey !== key && otherKeyList.find(mapKey => mapKey === key) === undefined) {
                otherKeyList.push(key); // 상대방 키를 목록에 추가
            }
        });
    });
};

// PeerConnection을 생성하는 함수
const createPeerConnection = (otherKey) => {
    const pc = new RTCPeerConnection(); // RTCPeerConnection 생성
    try {
        pc.addEventListener('icecandidate', (event) => {
            onIceCandidate(event, otherKey); // ICE 후보 등록
        });
        pc.addEventListener('track', (event) => {
            onTrack(event, otherKey); // 미디어 재생
        });

        if (localStream !== undefined) {
            localStream.getTracks().forEach(track => {
                pc.addTrack(track, localStream); // 로컬 스트림의 트랙을 추가
            });
        }
        console.log('PeerConnection created');
    } catch (error) {
        console.error('PeerConnection failed: ', error); // 에러 로그 출력
    }
    return pc; // 생성된 PeerConnection 반환
};

// ICE 후보 처리
let onIceCandidate = (event, otherKey) => {
    if (event.candidate) {
        console.log('ICE candidate');
        stompClient.send(`/app/peer/iceCandidate/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: event.candidate // ICE 후보를 상대방에게 전송
        }));
    }
};

// 원격 트랙 처리
let onTrack = (event, otherKey) => {
    if (document.getElementById(`${otherKey}`) === null) {
        const video = document.createElement('video'); // 새로운 비디오 요소 생성
        video.autoplay = true;
        video.controls = true;
        video.id = otherKey;
        video.srcObject = event.streams[0]; // 원격 스트림 연결

        const keyHtml = document.createElement('div');
        keyHtml.innerHTML = otherKey;
        document.getElementById('remoteStreamDiv').appendChild(video); // HTML에 추가
        document.getElementById('remoteStreamDiv').appendChild(keyHtml); // HTML에 추가
    }
};

// Offer 메시지 전송
let sendOffer = (pc, otherKey) => {
    pc.createOffer().then(offer => {
        setLocalAndSendMessage(pc, offer); // 로컬 SDP 설정
        stompClient.send(`/app/peer/offer/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: offer // Offer 전송
        }));
        console.log('Send offer');
    });
};

// Answer 메시지 전송
let sendAnswer = (pc, otherKey) => {
    pc.createAnswer().then(answer => {
        setLocalAndSendMessage(pc, answer); // 로컬 SDP 설정
        stompClient.send(`/app/peer/answer/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: answer // Answer 전송
        }));
        console.log('Send answer');
    });
};

// 로컬 SDP 설정
const setLocalAndSendMessage = (pc, sessionDescription) => {
    pc.setLocalDescription(sessionDescription);
};

// 방 입장 버튼 클릭 이벤트
document.querySelector('#enterRoomBtn').addEventListener('click', async () => {
    await startCam(); // 카메라 시작

    if (localStream !== undefined) {
        document.querySelector('#localStream').style.display = 'block';
        document.querySelector('#startSteamBtn').style.display = '';
    }
    roomId = document.querySelector('#roomIdInput').value; // 방 ID 저장
    document.querySelector('#roomIdInput').disabled = true;
    document.querySelector('#enterRoomBtn').disabled = true;

    await connectSocket(); // WebSocket 연결
});

// 스트림 시작 버튼 클릭 이벤트
document.querySelector('#startSteamBtn').addEventListener('click', async () => {
    await stompClient.send(`/app/call/key`, {}, {}); // 상대방 키 요청

    setTimeout(() => {
        otherKeyList.map((key) => {
            if (!pcListMap.has(key)) {
                pcListMap.set(key, createPeerConnection(key)); // PeerConnection 생성
                sendOffer(pcListMap.get(key), key); // Offer 전송
            }
        });
    }, 1000); // 1초 후에 실행
});
