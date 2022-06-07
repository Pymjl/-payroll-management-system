package cuit.pymjl.core.entity.user.vo;


import cuit.pymjl.core.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/7 13:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserVO extends User {
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 系id
     */
    private Long facultyId;
    /**
     * 系名字
     */
    private String facultyName;

}
