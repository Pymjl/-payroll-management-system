package cuit.pymjl.core.service;

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
    void register() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount("123435345435435341231");
        userDTO.setUsername("123@qq.com");
        userDTO.setAge(20);
        userDTO.setGender(1);
        userDTO.setNickname("pymjl");
        userDTO.setPassword("123456");
        userDTO.setPhone("19136310161");
        System.out.println(userService.register(userDTO));
    }
}
