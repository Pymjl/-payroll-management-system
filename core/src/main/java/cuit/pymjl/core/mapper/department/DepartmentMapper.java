package cuit.pymjl.core.mapper.department;

import cuit.pymjl.core.entity.department.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    /**
     * 获取部门信息
     *
     * @param departmentId 部门编号
     * @return 部门信息列表
     */
    Department getDepartment(int departmentId);

    /**
     * 获取部门员工列表
     *
     * @param staffDepartmentId 员工所属部门编号
     * @return 部门员工列表
     */
    List<DepartmentStaff> getDepartmentStaff(int staffDepartmentId);

    /**
     * 获取每个部门信息
     *
     * @param departmentId 部门信息
     * @return 每个部门信息列表
     */
    Department getDepartmentInformation(int departmentId);
    /**
     *获取员工出勤情况
     */
    /**
     * 获取员工出勤情况
     *
     * @param staffDepartmentId 员工所属部门的编号
     * @return 员工出勤列表
     */
    List<StaffAttendance> getStaffAttendance(int staffDepartmentId);

    /**
     * 汇总员工出勤情况
     *
     * @param staffId       员工的编号
     * @param staffLeaveNum 员工早退次数
     * @param staffLateNum  员工迟到次数
     */
    void updateDepartmentStaff(@Param("staffId") int staffId, @Param("staffLeaveNum") int staffLeaveNum, @Param("staffLateNum") int staffLateNum);

    List<StaffAttendenceDTO> getAllStaffInformation();

    List<StaffAttendance> getStaffAttendanceById(int staffid);

    /**
     * 员工查询部门id和系id
     *
     * @param staffId
     * @return
     */
    Staff getStaff(int staffId);

    /**
     * 通过用户ID得到部门和系的信息
     *
     * @param userId 用户id
     * @return {@code DeptInfo}
     */
    DeptInfo getDeptAndFacultyInfoByUserId(@Param("userId") Long userId);


    /**
     * 查询部门的组长ID
     *
     * @param deptName 部门名称
     * @return {@code Long}
     */
    Long selectManager(@Param("deptName") String deptName);

    /**
     * 通过部门ID查询组长的ID
     *
     * @param deptId 部门id
     * @return {@code Long}
     */
    Long selectManagerById(@Param("deptId") Long deptId);
}
