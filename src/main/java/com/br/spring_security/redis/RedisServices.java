package com.example.api_clima.redis;

import com.example.api_clima.convert_data.contract.DataMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class RedisServices <T>
{
    private final RedisTemplate<String,String> redis;
    private final DataMapper dataMapper;
    private final Duration durationCacheInRedis;

    public RedisServices(RedisTemplate<String, String> redis, DataMapper dataMapper, Duration durationCacheInRedis)
    {
        this.redis = redis;
        this.dataMapper = dataMapper;
        this.durationCacheInRedis = durationCacheInRedis;
    }

    public List<T> getAll(String key, Class<T> tClass)
    {
        String json = redis.opsForValue().get(key);
        return dataMapper.toObjectList(json,tClass);
    }

    public Optional<T> get(String key, Class<T> tClass)
    {
        String json = redis.opsForValue().get(key);
        return dataMapper.toObject(json, tClass);
    }

    public void save(String key, T object)
    {
        String json = dataMapper.toJson(object);
        redis.opsForValue().set(key,json,durationCacheInRedis);
    }

    public void save(String key, List<T> object)
    {
        String json = dataMapper.toJson(object);
        redis.opsForValue().set(key,json,durationCacheInRedis);
    }
}
