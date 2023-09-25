package danekerscode.controller;

import danekerscode.dto.UserDTO;
import danekerscode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    ResponseEntity<?> requestToRegistration(
            @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity
                .ok(userService.requestToRegistration(userDTO));
    }

    @PostMapping("confirm-registration")
    ResponseEntity<?> confirm(
            @RequestParam String hash
    ) {
        return ResponseEntity
                .status(201)
                .body(userService.confirmRegistration(hash));
    }


}
