package com.gatewayflow.fleettrack.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPublisher {

    private final RedisTemplate<String, Object>
            redisTemplate;

    public void publish(
            String channel,
            Object message
    ) {

        redisTemplate.convertAndSend(
                channel,
                message
        );
    }
}