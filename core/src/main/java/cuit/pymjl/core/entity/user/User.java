package cuit.pymjl.core.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/23 12:31
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -10899714971680448L;
    /**
     * id.主键自增
     */
    private Long id;
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 银行账号
     */
    private String account;
    /**
     * 电话
     */
    private String phone;
    /**
     * 身份，0为普通用户，1为管理员，-1为封禁用户
     */
    private Integer identity;

}

