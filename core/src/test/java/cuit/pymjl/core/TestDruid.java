package cuit.pymjl.core;

import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.util.MybatisUtil;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 16:44
 **/
public class TestDruid {
    public static void main(String[] args) {
        UserMapper mapper = MybatisUtil.openSession().getMapper(UserMapper.class);
        List<User> users = mapper.queryUsers(1, 5);
    }
}
