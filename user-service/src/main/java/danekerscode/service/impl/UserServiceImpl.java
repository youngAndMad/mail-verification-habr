package danekerscode.service.impl;

import danekerscode.dto.KafkaEmailMessageDTO;
import danekerscode.dto.UserDTO;
import danekerscode.exception.EmailRegisteredException;
import danekerscode.exception.LinkExpiredException;
import danekerscode.model.User;
import danekerscode.repository.UserRepository;
import danekerscode.service.Base64Service;
import danekerscode.service.KafkaProducer;
import danekerscode.service.UserService;
import danekerscode.utils.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Base64Service base64;
    private final KafkaProducer kafkaProducer;

    public static final String EMAIL_TOPIC = "email_message";

    @Override
    public StatusResponse requestToRegistration(UserDTO userDTO) {
        try {
            var optionalUser = this.userRepository.findByEmail(userDTO.getEmail());

            if (optionalUser.isPresent()) {
                throw new EmailRegisteredException("email: %s registered yet".formatted(userDTO.getEmail()));
            }

            var dataToSend = base64.encode(userDTO);

            kafkaProducer.produce(EMAIL_TOPIC, new KafkaEmailMessageDTO(userDTO.getEmail(), dataToSend));

            return new StatusResponse(
                    true, null
            );
        } catch (Exception e) {
            return new StatusResponse(
                    false,
                    e.getMessage()
            );
        }
    }

    @Override
    public User confirmRegistration(String hash) {

        var userDTO = base64.decode(hash, UserDTO.class);

        if (userDTO.getTime().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new LinkExpiredException();
        }

        var user = new User(
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getEmail()
        );

        return this.userRepository.save(user);
    }


}
