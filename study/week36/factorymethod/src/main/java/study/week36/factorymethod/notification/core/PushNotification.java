package study.week36.factorymethod.notification.core;

import study.week36.factorymethod.notification.type.NotificationType;

public class PushNotification implements Notification {
    @Override
    public void send(String to, String message) {
        System.out.println("앱푸시 전송 to: " + to + ", 내용: " + message);
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }
}