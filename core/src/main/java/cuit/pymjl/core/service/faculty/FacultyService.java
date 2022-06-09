package cuit.pymjl.core.service.faculty;

import cuit.pymjl.core.entity.department.Department;

import java.util.List;
import java.util.Map;

public interface FacultyService {
    /**
     * 增加部门
     */
    void addDepartment(Long facultyId, int departmentId);
    void addDepartment1(int departmentId, String departmentName, int departmentBossId);

    /**
     * 删除部门
     */
    void deleteDepartment(int departmentId);

    /**
     * 通过部门id查询部门信息
     */
    Department getDepartmentInformation(int departmentId);

    /**
     * 查询部门部门id
     */
    List<Department> getDepartmentInformations(Long facultyId);
}
