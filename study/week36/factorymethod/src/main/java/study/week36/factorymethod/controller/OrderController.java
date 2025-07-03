package study.week36.factorymethod.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.week36.factorymethod.notification.core.Notification;
import study.week36.factorymethod.notification.factory.NotificationFactory;
import study.week36.factorymethod.payment.core.Payment;
import study.week36.factorymethod.payment.factory.PaymentFactory;
import study.week36.factorymethod.payment.type.PaymentType;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class OrderController {

//    private final Map<String, NotificationFactory> notificationFactoryMap;

    @Autowired
    @Qualifier("smsNotificationFactory")
    private final NotificationFactory notificationFactory;

    private final PaymentFactory paymentFactory;


    @PostMapping
    public String order (
            @RequestParam PaymentType paymentType,
            @RequestParam String notificationType,
            @RequestParam String id,
            @RequestParam Long amount,
            @RequestParam String to,
            @RequestParam String message
    ) {
        Payment payment = paymentFactory.getPayment(paymentType);
        payment.pay(id, amount);

//        NotificationFactory notificationFactory = notificationFactoryMap.get(notificationType);
        Notification notification = notificationFactory.createNotification();
        notification.send(to, message);

        return "done";
    }
}
