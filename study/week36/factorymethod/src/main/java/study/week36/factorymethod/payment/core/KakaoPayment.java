package study.week36.factorymethod.payment.core;

import org.springframework.stereotype.Component;
import study.week36.factorymethod.payment.type.PaymentType;

@Component
public class KakaoPayment implements Payment {
    @Override
    public void pay(String transactionId, Long amount) {
        System.out.println("KaKao " + transactionId + " payed " + amount);
    }

    @Override
    public PaymentType getType() {
        return PaymentType.KAKAO;
    }
}
