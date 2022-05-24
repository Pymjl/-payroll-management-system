package cuit.pymjl.core.mapper;

import cuit.pymjl.core.entity.user.User;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 17:01
 **/
public interface UserMapper {
    /**
     * 查询用户列表
     *
     * @return {@code List<User>}
     */
    List<User> queryUsers();
}
