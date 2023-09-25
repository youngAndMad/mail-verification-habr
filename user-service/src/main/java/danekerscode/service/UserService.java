package danekerscode.service;


import danekerscode.dto.UserDTO;
import danekerscode.model.User;
import danekerscode.utils.StatusResponse;

public interface UserService {

    StatusResponse requestToRegistration(UserDTO userDTO);

    User confirmRegistration(String hash);
}
