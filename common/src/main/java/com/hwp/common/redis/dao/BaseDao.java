package com.hwp.common.redis.dao;

import com.hwp.common.redis.StringRedisTemplate;
import org.springframework.data.redis.core.RedisCommand;


public interface BaseDao {
    StringRedisTemplate getStringRedisTemplate(RedisCommand command);
}
