package study.week36.factorymethod.notification.factory;

import org.springframework.stereotype.Component;
import study.week36.factorymethod.notification.core.EmailNotification;
import study.week36.factorymethod.notification.core.Notification;
import study.week36.factorymethod.notification.core.PushNotification;
import study.week36.factorymethod.notification.type.NotificationType;

@Component
public class PushNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}
