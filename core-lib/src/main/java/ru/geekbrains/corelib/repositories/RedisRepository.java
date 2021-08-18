package ru.geekbrains.corelib.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private RedisTemplate<String, Object> redisTemplate;

    public void saveToken(String token){
        redisTemplate.expire(token, Duration.ofHours(3));
    }

    public boolean checkToken(String token){
        return redisTemplate.hasKey(token);
    }
}
