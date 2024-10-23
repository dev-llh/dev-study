package study.week15.publisher.util;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.event.EventListener;
import org.springframework.integration.mqtt.core.MqttPahoComponent;
import org.springframework.integration.mqtt.event.MqttConnectionFailedEvent;
import org.springframework.stereotype.Component;

@Component
public class MqttEventHandler {
    @EventListener
    public void connectLost(MqttConnectionFailedEvent failedEvent){
        MqttPahoComponent source = failedEvent.getSourceAsType();
        MqttConnectOptions options = source.getConnectionInfo();
        System.out.println("MQTT Connection is broken!!");
    }
}
