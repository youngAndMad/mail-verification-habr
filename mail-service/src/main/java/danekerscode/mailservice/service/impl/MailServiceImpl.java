package danekerscode.mailservice.service.impl;

import danekerscode.mailservice.service.MailService;
import danekerscode.mailservice.utils.KafkaMailMessage;
import danekerscode.mailservice.utils.MessageMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${server.frontend-url}")
    private String frontEndURL;

    @Override
    public void send(KafkaMailMessage kafkaMailMessage, MessageMode mode) {
        var msg = new SimpleMailMessage();

        if (mode == MessageMode.EMAIL_VERIFICATION) {
            msg.setText(frontEndURL + "/verification?data=" + kafkaMailMessage.message());
        } else {
            msg.setText(kafkaMailMessage.message());
        }

        msg.setTo(kafkaMailMessage.email());
        msg.setFrom("habrexample@gmail.com");

        try {
            mailSender.send(msg);
            log.info("email send, msg: {}, mode: {}", kafkaMailMessage, mode);
        } catch (Exception e) {
            log.error("send mail error : {}", e.getMessage());
        }


    }
}
