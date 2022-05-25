package cuit.pymjl.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.service.UserService;
import cuit.pymjl.core.util.MybatisUtil;
import cuit.pymjl.core.util.PasswordUtils;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:47
 **/
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {
    private static final UserMapper userMapper = MybatisUtil.openSession().getMapper(UserMapper.class);

    @Override
    public Boolean register(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        user.setIdentity(IdentityEnum.USER.getIdentity());
        int i = userMapper.addUser(user);
        return i == 1;
    }
}
