package com.spring.chatty.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisController {
    private final RedisService redisService;

    @DeleteMapping("/keys")
    public ResponseEntity<?> deleteAllKeys() {
        redisService.deleteAllKeys();
        return ResponseEntity.ok("All keys deleted");
    }
}

