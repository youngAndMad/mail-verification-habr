package danekerscode.mailservice.listener;

import danekerscode.mailservice.service.MailService;
import danekerscode.mailservice.utils.KafkaMailMessage;
import danekerscode.mailservice.utils.MessageMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailListener {

    private final MailService mailService;

    @KafkaListener(
            topics = "email_message", groupId = "some"
    )
    void listen(
            KafkaMailMessage kafkaMailMessage
    ) {
        log.info("email message: {} ", kafkaMailMessage);
        mailService.send(kafkaMailMessage, MessageMode.EMAIL_VERIFICATION);
    }
}
