package cuit.pymjl.core.mapper.faculty;

import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.faculty.FacultyDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

import java.util.List;
import java.util.Map;

public interface FacultyMapper {
    /**
     * 新增部门
     * @param facultyId 系id
     * @param departmentId 部门id
     */
    void addDepartment(@Param("facultyId") Long facultyId,
                       @Param("departmentId") int departmentId);

    /**
     * 将增加的部门插入部门表中
     * @param departmentId 部门id
     * @param departmentName 部门名
     * @param departmentBossId 部门老大id
     */
    void addDepartment1(@Param("departmentId") int departmentId,
                        @Param("departmentName") String departmentName,
                        @Param("departmentBossId") int departmentBossId);

    /**
     * 通过部门id删除部门
     *
     * @param departmentId 部门id
     */
    int delDepartment(@Param("departmentId") int departmentId);

    /**
     * 通过系id查询部门id
     * @param facultyId 系id
     */
    List<Map<String, Integer>> getDepartmentId(@Param("facultyId") Long facultyId);

}
