package study.week36.factorymethod.payment.core;

import study.week36.factorymethod.payment.type.PaymentType;

public interface Payment {
    void pay(String transactionId, Long amount);
    PaymentType getType();
}

