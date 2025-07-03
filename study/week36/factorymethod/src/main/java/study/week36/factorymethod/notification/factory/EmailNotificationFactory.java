package study.week36.factorymethod.notification.factory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.week36.factorymethod.notification.core.EmailNotification;
import study.week36.factorymethod.notification.core.Notification;


@Component
@Primary
public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
