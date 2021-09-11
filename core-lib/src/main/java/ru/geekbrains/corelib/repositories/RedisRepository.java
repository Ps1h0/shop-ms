package ru.geekbrains.corelib.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveToken(String token){
        redisTemplate.opsForValue().set("token:" + token, 1, Duration.ofHours(1));
    }

    public boolean checkToken(String token){
        return redisTemplate.opsForValue().get("token:" + token) != null;
    }
}
