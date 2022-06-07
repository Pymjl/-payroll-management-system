package cuit.pymjl.core.entity.department;


import lombok.Data;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/7 14:06
 **/
@Data
public class DeptInfo {
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
