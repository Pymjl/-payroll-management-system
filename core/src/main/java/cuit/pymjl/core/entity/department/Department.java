package cuit.pymjl.core.entity.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Department {
    /*
    部门的编号
     */
    private int departmentId;
    /*
    部门的名字
     */
    private String departmentName;
    /*
    部门员工总数
     */
    private int departmentNum;
    /*
    部门老大的名字
     */
    private String departmentBossName;
    /*
    部门老大的编号
     */
    private int departmentBossId;
}
