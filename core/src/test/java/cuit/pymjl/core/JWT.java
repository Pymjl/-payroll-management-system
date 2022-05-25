package cuit.pymjl.core;

import cuit.pymjl.core.util.JwtUtils;
import cuit.pymjl.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
}
