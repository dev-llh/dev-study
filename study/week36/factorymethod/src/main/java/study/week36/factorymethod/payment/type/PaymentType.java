package study.week36.factorymethod.payment.type;

import lombok.Getter;

@Getter
public enum PaymentType {
    KAKAO("KAKAO"),
    PAYCO("PAYCO"),
    TOSS("TOSS");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }
}
