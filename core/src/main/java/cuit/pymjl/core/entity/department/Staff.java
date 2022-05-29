package cuit.pymjl.core.entity.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Staff {
    /*
    员工编号
     */
    private int staffId;
    /*
    员工姓名
     */
    private String staffName;
    /*
    员工性别
     */
    private String staffSex;
    /*
    员工年龄
     */
    private int staffAge;
    /*
    员工所属部门编号
     */
    private int staffDepartmentId;
    /*
    员工电话号码
     */
    private String staffPhoneNumber;
}
