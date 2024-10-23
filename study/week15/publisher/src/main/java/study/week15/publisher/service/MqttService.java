package study.week15.publisher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
import study.week15.publisher.dto.MqttDto;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MqttService {

    private final IMqttClient mqttClient;
    private final ObjectMapper objectMapper;

    public void publish(MqttDto dto) throws Exception {
        String payload = objectMapper.writeValueAsString(dto);

        int qos = 0;
        boolean retained = true; //true 시 마지막 메세지를 저장

        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);

        try {
            mqttClient.publish(dto.getTopic(), mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean connected() {
        return mqttClient.isConnected();
    }
}
