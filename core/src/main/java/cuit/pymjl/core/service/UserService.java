package cuit.pymjl.core.service;

import cuit.pymjl.core.entity.user.dto.UserDTO;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:47
 **/
public interface UserService {
    /**
     * 注册
     *
     * @param userDTO 用户dto
     * @return {@code Boolean}
     */
    Boolean register(UserDTO userDTO);
}
