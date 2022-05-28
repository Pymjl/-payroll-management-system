package cuit.pymjl.core.mapper.department;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.department.DepartmentStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    /*
    获取部门信息
     */
    List<Department> getDepartment(int departmentId);
    /*
    部门老大获取员工列表
     */
    List<DepartmentStaff> getDepartmentStaff(int staffDepartmentId);
    /*
    获取每个部门信息
     */
    Department getDepartmentInformation(int departmentId);
}
