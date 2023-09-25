package danekerscode.service.impl;

import danekerscode.dto.UserDTO;
import danekerscode.model.User;
import danekerscode.repository.UserRepository;
import danekerscode.service.Base64Service;
import danekerscode.service.KafkaProducer;
import danekerscode.service.MessageHelper;
import danekerscode.service.UserService;
import danekerscode.utils.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Base64Service base64;
    private final KafkaProducer kafkaProducer;
    private final MessageHelper messageHelper;

    public static final String EMAIL_TOPIC = "email_message";

    @Override
    public StatusResponse requestToRegistration(UserDTO userDTO) {
        try {
            var dataToSend = base64.encode(userDTO);

            kafkaProducer.produce(EMAIL_TOPIC, this.constructVerificationLink(dataToSend));

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

        var user = new User(
                userDTO.name(),
                userDTO.surname(),
                userDTO.email()
        );

        return this.userRepository.save(user);
    }

    private String constructVerificationLink(String dataToSend) {
        var prefix = messageHelper.getPrefix();
        return prefix + "verification?data=" + dataToSend;
    }
}
