package cuit.pymjl.core;

import cn.hutool.db.nosql.redis.RedisDS;
import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.mapper.user.UserMapper;
import cuit.pymjl.core.result.PageResult;
import cuit.pymjl.core.util.*;
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
        UserMapper userMapper = MybatisUtil.openSession().getMapper(UserMapper.class);
        Page<User> users = userMapper.queryUsers(1, 5);
        System.out.println(JsonUtils.toString(new PageResult<>(users)));
    }

    @Test
    void deleteKey() {
        JedisUtils.del("1");
    }
}
