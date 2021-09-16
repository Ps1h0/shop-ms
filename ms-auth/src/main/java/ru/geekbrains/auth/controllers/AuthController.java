package ru.geekbrains.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.auth.entities.User;
import ru.geekbrains.auth.repositories.JdbcRepository;
import ru.geekbrains.auth.services.UserService;
import ru.geekbrains.corelib.interfaces.ITokenService;
import ru.geekbrains.corelib.models.UserInfo;
import ru.geekbrains.corelib.repositories.RedisRepository;
import ru.geekbrains.routing.dtos.AuthRequestDto;
import ru.geekbrains.routing.dtos.AuthResponseDto;
import ru.geekbrains.routing.dtos.SignUpRequestDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final ITokenService iTokenService;

    private final RedisRepository redisRepository;

    private final JdbcRepository jdbcRepository;

    @GetMapping("/jdbc")
    public User registerUser(@RequestParam String email){
        return jdbcRepository.getByEmail(email).get();
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getEmail(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getName()));
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getEmail())
                .role(roles)
                .build();
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping("/logout")
    public Boolean logout(@RequestHeader("Authorization") String token){
        redisRepository.saveToken(token);
        return true;
    }
}
