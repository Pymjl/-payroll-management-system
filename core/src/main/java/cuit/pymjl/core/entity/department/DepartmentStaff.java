package cuit.pymjl.core.entity.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DepartmentStaff {
    /*
    员工的编号
     */
    private int staffId;
    /*
    员工的名字
     */
    private String staffName;
    /*
    员工的职位
     */
    private int staffPosition;
    /*
    部门的编号
     */
    private int staffDepartmentId;
}
