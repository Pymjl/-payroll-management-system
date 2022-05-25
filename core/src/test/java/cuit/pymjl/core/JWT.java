package cuit.pymjl.core;

import cn.hutool.db.nosql.redis.RedisDS;
import cuit.pymjl.core.util.JwtUtils;
import cuit.pymjl.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 19:32
 **/
@Slf4j
public class JWT {
    @Test
    void testJwt() {
        System.out.println(JwtUtils.generateToken(1L, "pymjl"));
        log.info("成功");
    }

    @Test
    void testPassword() {
        System.out.println(PasswordUtils.encrypt("123456"));
    }

    @Test
    void testJedis() {
        Jedis jedis = RedisDS.create().getJedis();
        jedis.setex("k1", 60, "v1");
        System.out.println(jedis.get("k1"));
    }
}
