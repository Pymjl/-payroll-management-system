package cuit.pymjl.core.service.user;

import cn.hutool.captcha.CircleCaptcha;
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

    /**
     * 得到图像验证代码
     *
     * @param key 关键
     * @return {@code CircleCaptcha}
     */
    CircleCaptcha getImageVerifyCode(String key);

    /**
     * 获得电子邮件代码
     *
     * @param username 用户名
     * @return {@code Boolean}
     */
    Boolean getEmailCode(String username);
}
