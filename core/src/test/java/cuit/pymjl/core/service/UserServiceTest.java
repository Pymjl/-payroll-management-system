package cuit.pymjl.core.service;

import cn.hutool.captcha.CircleCaptcha;
import cuit.pymjl.core.constant.Avatar;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.mapper.user.UserMapper;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.util.JsonUtils;
import cuit.pymjl.core.util.MybatisUtil;
import cuit.pymjl.core.util.PasswordUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;

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

    @Test
    void getUserInfo() {
        System.out.println(JsonUtils.toString(userService.queryUserById(1L)));
    }

    @Test
    void addUsers() {
        SqlSession sqlSession = MybatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        for (int i = 0; i < 30; i++) {
            user.setAvatar(Avatar.DEFAULT.getPath());
            user.setUsername("example" + i + "@qq.com");
            user.setPassword(PasswordUtils.encrypt("123456"));
            user.setIdentity(IdentityEnum.USER.getIdentity());
            user.setGender(1);
            user.setNickname("test" + i);
            user.setAge(i);
            user.setAccount("1231231321321312312");
            user.setPhone("11111111111");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            mapper.addUser(user);
        }
    }

    @Test
    void testPassword() {
        String encrypt = PasswordUtils.encrypt("dasdsad2q4wqdsadsa");
        System.out.println(encrypt);
        System.out.println(PasswordUtils.match("123456", encrypt));
    }
}
