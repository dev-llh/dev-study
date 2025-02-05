importScripts('https://www.gstatic.com/firebasejs/11.2.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/11.2.0/firebase-messaging-compat.js');

firebase.initializeApp({
    apiKey: "AIzaSyCIgSoeCZeu6fye5UNuxb7ZZ1n7CAjS0MQ",
    authDomain: "week24-259cc.firebaseapp.com",
    projectId: "week24-259cc",
    storageBucket: "week24-259cc.firebasestorage.app",
    messagingSenderId: "888366872797",
    appId: "1:888366872797:web:3c8747a0a928dd3b890334"
});

const messaging = firebase.messaging();
console.log("service worker on");
messaging.onBackgroundMessage((payload) => {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    var title = payload.notification.title;
    var options = {
        body: payload.notification.body,
        icon: payload.notification.icon
    };
    var notification = new Notification(title,options);
});