package study.week15.publisher.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class MqttConfig {
    private final String topic1 = "topic1";

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setAutomaticReconnect(true);

        return new MqttConnectOptions();
    }

    @Bean
    public IMqttClient mqttClient(@Value("${mqtt.clientId}") String clientId,
                                  @Value("${mqtt.hostname}") String hostname,
                                  @Value("${mqtt.port}") int port) throws MqttException {
        // 지정된 호스트 이름, 포트 및 clientId로 새로운 MQTT 클라이언트를 생성
        IMqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, clientId);

        mqttClient.connect(mqttConnectOptions()); // 제공된 옵션을 사용하여 클라이언트를 연결
        return mqttClient; // 연결된 MQTT 클라이언트를 반환
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel(); // MQTT 메시지를 위한 새로운 DirectChannel을 생성하여 반환
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("tcp://127.0.0.1:1883", "publisher");
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topic1);
        return messageHandler;
    }
}
