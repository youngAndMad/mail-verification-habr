package danekerscode.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record UserDTO(
        String name,
        String surname,
        String email,
        LocalDateTime time
) {
    public UserDTO(String name, String surname, String email) {
        this(
                name,
                surname,
                email,
                LocalDateTime.now()
        );
    }
}
