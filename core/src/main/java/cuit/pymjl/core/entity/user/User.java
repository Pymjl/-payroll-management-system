package cuit.pymjl.core.entity.user;

import lombok.*;

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
public class User {

    /**
     * 主键id，自增
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
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
}
