package com.zdmedtech.wms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author: StefanChoo
 * Date: 2018/7/17 16:57
 */
@Service
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveValue(String key, String value) {
        log.info("Save data to redis, key:{}, value:{}", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    public void saveObject(String key, Object object) {
        log.info("Save data to redis, key:{}, value:{}", key, object);
        redisTemplate.opsForValue().set(key, object);
    }

    public Object readObject(String key) {
        log.info("Read data from redis, key:{}", key);
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteObject(String key) {
        log.info("Delete data from redis, key:{}", key);
        redisTemplate.delete(key);
    }

    public void deleteObjects(Collection<String> keys) {
        log.info("Delete data from redis, keys:", keys);
        redisTemplate.delete(keys);
    }

    public void expireKey(String key, Long times, TimeUnit unit) {
        log.info("expire key in redis, key:{}", key);
        redisTemplate.expire(key, times, unit);
    }

    public void saveList(String key, List<?> list) {
        log.info("save a list in redis, key:{}", key);
        redisTemplate.delete(key);
        for (Object l : list) {
            redisTemplate.opsForList().rightPush(key, l);
        }
    }

    public List<?> readList(String key) {
        log.info("read a list from redis, key:{}", key);
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // 自加１
    public void incr(String key) {
        log.debug("increase a key value : ", key);
        redisTemplate.opsForValue().increment(key, 1);
    }
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 放入队列的最后
     */
    public void put2End(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 取队列里面最后一个元素
     */
    public Object getEnd(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 放入队列的第一个位置
     */
    public void put2First(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 取队列里面的第一个元素
     */
    public Object getFirst(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

}
