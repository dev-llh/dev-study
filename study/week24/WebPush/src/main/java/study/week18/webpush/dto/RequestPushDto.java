package study.week18.webpush.dto;


public record RequestPushDto(
        String title,
        String body,
        String token
) { }
