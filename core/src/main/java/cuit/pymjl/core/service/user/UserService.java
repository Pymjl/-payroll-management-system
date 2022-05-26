package cuit.pymjl.core.service.user;

import cn.hutool.captcha.CircleCaptcha;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.entity.user.dto.UserInfoDTO;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:47
 **/
public interface UserService {

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
     * @param key      标识符
     * @param code     图片验证码
     */
    void getEmailCode(String username, String key, String code);

    /**
     * 注册
     *
     * @param userDTO 用户dto
     */
    void register(UserDTO userDTO);


    /**
     * 登录
     *
     * @param userInfoDTO 用户信息dto
     * @return {@code String}
     */
    String login(UserInfoDTO userInfoDTO);

    /**
     * 通过id查询用户
     *
     * @param userId 用户id
     * @return {@code User}
     */
    User queryUserById(Long userId);
}
