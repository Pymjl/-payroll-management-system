package cuit.pymjl.core.mapper.faculty;

import cuit.pymjl.core.entity.faculty.FacultyDetails;
import org.apache.ibatis.annotations.Param;

//import java.util.List;

public interface FacultyMapper {
    /**
     * 获取系主任负责的部门id
     * @param是用来在DAO层中声明参数的注解方式.
     * @param facultyHead 系主任
     * return {@code List<Departments>}
     */
    //List<FacultyDetails> queryDepartmentID(@Param("facultyHead") Long facultyHead);

    /**
     *通过员工id修改员工职位
     * 系主任取消部门老大资格
     * @param staffID 职员id
     */
    void updateStaffPosition(@Param("staffID") int staffID);

    /**
     * 系主任指定部门员工担任部门老大
     * @param staffID 职员id
     */
    void updateStaffPositions(@Param("staffID") int staffID);

    /**
     * 新增部门
     */
    void addDepartment(FacultyDetails FacultyDetails);

    /**
     * 通过部门id删除部门
     *
     * @param departmentId 部门id
     * @param facultyId 系id
     */
    int delDepartment(@Param("departmentId") int departmentId,
                      @Param("facultyId") Long facultyId);
}
