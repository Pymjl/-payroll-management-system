package cuit.pymjl.core.util;

import cn.hutool.db.nosql.redis.RedisDS;
import redis.clients.jedis.Jedis;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 22:57
 **/
public class JedisUtils {
    private static final Jedis CLIENT = RedisDS.create().getJedis();

    public static void set(String key, String value) {
        CLIENT.set(key, value);
    }

    public static void set(String key, String value, long expire) {
        CLIENT.setex(key, expire, value);
    }
}
