package danekerscode.mailservice.service;

import danekerscode.mailservice.utils.KafkaMailMessage;
import danekerscode.mailservice.utils.MessageMode;

public interface MailService {

    void send(KafkaMailMessage kafkaMailMessage, MessageMode mode);
}
