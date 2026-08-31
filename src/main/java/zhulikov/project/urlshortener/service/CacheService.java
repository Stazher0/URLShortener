package zhulikov.project.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveToCache(String shortKey, String originalUrl) {
        redisTemplate.opsForValue().set(shortKey, originalUrl, Duration.ofHours(24));
    }

    public Optional<String> getFromCache(String shortKey) {
        String returnedUrl = redisTemplate.opsForValue().get(shortKey);
        return Optional.ofNullable(returnedUrl);
    }
}
