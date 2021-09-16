package ru.geekbrains.routing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.routing.dtos.AuthRequestDto;
import ru.geekbrains.routing.dtos.AuthResponseDto;
import ru.geekbrains.routing.dtos.SignUpRequestDto;

@FeignClient("ms-auth")
public interface AuthClient {

    @PostMapping("/signup")
    String signUp(@RequestBody SignUpRequestDto signUpRequestDto);

    @PostMapping("/login")
    AuthResponseDto login(@RequestBody AuthRequestDto request);
}
