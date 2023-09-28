package danekerscode.controller;

import danekerscode.dto.UserDTO;
import danekerscode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
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
            @RequestParam String data
    ) {
        return ResponseEntity
                .status(201)
                .body(userService.confirmRegistration(data));
    }


}
