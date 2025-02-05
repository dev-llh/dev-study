package study.week18.webpush.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import study.week18.webpush.dto.FCMMessage;
import study.week18.webpush.dto.Message;
import study.week18.webpush.dto.Notification;
import study.week18.webpush.dto.RequestPushDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FCMService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/week24-259cc/messages:send";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    public String sendMessageTo(RequestPushDto requestPushDto) throws IOException {
        String message = makeMessage(requestPushDto.token(), requestPushDto.title(), requestPushDto.body());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);

        return restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class).getBody();
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {

        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setBody(body);
        notification.setImage("http://localhost:8080/logo");

        Message message = new Message();
        message.setNotification(notification);
        message.setToken(targetToken);

        FCMMessage fcmMessage = new FCMMessage();
        fcmMessage.setMessage(message);
        fcmMessage.setValidate_only(false);

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "/firebase/week24-259cc-firebase-adminsdk-fbsvc-951515759c.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
