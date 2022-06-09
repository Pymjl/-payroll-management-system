package cuit.pymjl.core.util;

import cn.hutool.db.nosql.redis.RedisDS;
import redis.clients.jedis.Jedis;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 22:57
 **/
public class JedisUtils {
    public static void set(String key, String value) {
        Jedis jedis = RedisDS.create().getJedis();
        try {
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    public static void set(String key, String value, long expire) {
        Jedis jedis = RedisDS.create().getJedis();
        try {
            jedis.setex(key, expire, value);
        } finally {
            jedis.close();
        }
    }

    public static Object get(String key) {
        Jedis jedis = RedisDS.create().getJedis();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public static void del(String key) {
        Jedis jedis = RedisDS.create().getJedis();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }
}
