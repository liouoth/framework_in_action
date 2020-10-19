package com.leo.shiro_thymeleaf.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;


public class RedisCache<K, V> implements Cache<K, V> {
    private String cacheName;

    private long expireTime = 120;

    private RedisTemplate redisTemplate;


    RedisCache() {
    }

    RedisCache(String s, RedisTemplate redisTemplate) {
        this.cacheName = s;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public V get(K k) throws CacheException {
        return (V) redisTemplate.opsForHash().get(cacheName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForHash().put(cacheName, k.toString(), v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisTemplate.opsForValue().get(k.toString());
        redisTemplate.opsForValue().getOperations().delete(k.toString());
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.opsForHash().delete(this.cacheName);
    }

    @Override
    public int size() {
        return redisTemplate.opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return redisTemplate.opsForHash().values(this.cacheName);
    }
}
