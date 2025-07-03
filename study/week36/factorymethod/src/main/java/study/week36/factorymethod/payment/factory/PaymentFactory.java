package study.week36.factorymethod.payment.factory;

import org.springframework.stereotype.Component;

import study.week36.factorymethod.payment.core.Payment;
import study.week36.factorymethod.payment.type.PaymentType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentFactory {

    private final Map<PaymentType, Payment> paymentMap = new EnumMap<>(PaymentType.class);

    public PaymentFactory(List<Payment> paymentList) {
        for (Payment payment : paymentList) {
            paymentMap.put(payment.getType(), payment);
        }
    }

    public Payment getPayment(PaymentType type) {
        return paymentMap.get(type);
    }
}