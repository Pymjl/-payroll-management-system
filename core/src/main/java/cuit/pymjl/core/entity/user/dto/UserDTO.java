package cuit.pymjl.core.entity.user.dto;

import lombok.Data;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:48
 **/
@Data
public class UserDTO {
    /**
     * 用户名，邮箱账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别，0为女，1为男
     */
    private Integer gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 银行账号
     */
    private String account;
    /**
     * 电话
     */
    private String phone;
}
