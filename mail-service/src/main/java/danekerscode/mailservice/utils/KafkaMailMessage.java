package danekerscode.mailservice.utils;

public record KafkaMailMessage(
        String email,
        String message
) {
}
