package cuit.pymjl.core.service;

import cn.hutool.captcha.CircleCaptcha;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.service.user.UserService;
import org.junit.jupiter.api.Test;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 17:04
 **/
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    void getImageCode() {
        CircleCaptcha code = userService.getImageVerifyCode("sd");
        System.out.println(code.getCode());
        userService.getImageVerifyCode("sd");
    }
}
