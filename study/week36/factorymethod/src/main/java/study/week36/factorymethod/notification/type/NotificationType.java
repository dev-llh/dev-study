package study.week36.factorymethod.notification.type;

import lombok.Getter;

@Getter
public enum NotificationType {
    SMS("SMS"),
    PUSH("PUSH"),
    EMAIL("EMAIL");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }
}
