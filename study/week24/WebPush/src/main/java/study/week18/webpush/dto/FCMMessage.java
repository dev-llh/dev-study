package study.week18.webpush.dto;

public class FCMMessage {
    private boolean validate_only;
    private Message message;

    public boolean getValidate_only() {
        return validate_only;
    }
    public void setValidate_only(boolean validate_only) {
        this.validate_only = validate_only;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
}
