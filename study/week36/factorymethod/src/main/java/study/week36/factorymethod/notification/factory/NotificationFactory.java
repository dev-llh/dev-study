package study.week36.factorymethod.notification.factory;

import study.week36.factorymethod.notification.core.Notification;
import study.week36.factorymethod.notification.type.NotificationType;

public interface NotificationFactory {
    Notification createNotification();
}
