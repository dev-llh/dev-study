package study.week36.factorymethod.notification.core;

import study.week36.factorymethod.notification.type.NotificationType;

public interface Notification {
    void send(String to, String message);
    NotificationType getType();
}