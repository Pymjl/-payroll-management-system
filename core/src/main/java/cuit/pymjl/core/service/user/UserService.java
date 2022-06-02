package cuit.pymjl.core.service.user;

import cn.hutool.captcha.CircleCaptcha;
import com.github.pagehelper.Page;
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

    /**
     * 注销
     *
     * @param userId 用户id
     */
    void logout(Long userId);

    /**
     * 查询用户列表
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@code Page<User>}
     */
    Page<User> queryUsers(Integer pageNum, Integer pageSize);

    /**
     * 重置密码
     *
     * @param username 用户名
     * @param code     代码
     */
    void resetPassword(String username, String code);

    /**
     * 更新密码
     *
     * @param password 新密码
     * @param userId   用户id
     */
    void updatePassword(String password, Long userId);

    /**
     * 封禁用户
     *
     * @param userId 用户id
     */
    void banUser(Long userId);

    /**
     * 更新用户信息
     *
     * @param userDTO 用户dto
     * @param userId  用户id
     */
    void updateUserInfo(UserDTO userDTO, Long userId);

    /**
     * 恢复用户身份
     *
     * @param userId 用户id
     */
    void recoverUserIdentity(Long userId);

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    void deleteUser(Long userId);
}
