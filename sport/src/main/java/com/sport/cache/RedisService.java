package com.sport.cache;

import java.util.function.Function;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ApplicationScoped
public class RedisService {
    private final JedisPool jedisPool;

    public RedisService() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(32);
        poolConfig.setMinIdle(4);
        poolConfig.setTestOnBorrow(true);
        
        this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    public void set(String key, String value, int ttl) {
        execute(jedis -> {
            jedis.setex(key, ttl, value);
            return null;
        });
    }

    public String get(String key) {
        return execute(jedis -> jedis.get(key));
    }

    public void delete(String key) {
        execute(jedis -> {
            jedis.del(key);
            return null;
        });
    }

    private <T> T execute(Function<Jedis, T> operation) {
        try (Jedis jedis = jedisPool.getResource()) {
            return operation.apply(jedis);
        }
    }

    @PreDestroy
    public void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }
}