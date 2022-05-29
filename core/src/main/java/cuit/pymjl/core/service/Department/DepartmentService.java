package cuit.pymjl.core.service.Department;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.department.DepartmentStaff;
import cuit.pymjl.core.entity.department.Staff;

import java.util.List;

public interface DepartmentService {
    /*
    部门老大查看自己部门员工列表（包括出勤情况）
     */
    List<DepartmentStaff> getDepartmentStaff(String bossName, int bossId, int departmentId);
    /*
    部门老大查看自己部门员工详细信息
     */
    List<Staff> getStaffInformation(String bossName, int bossId, int departmentId);
}
