package cuit.pymjl.core.service.department;

import cuit.pymjl.core.entity.department.DepartmentStaff;
import cuit.pymjl.core.entity.department.Staff;
import cuit.pymjl.core.entity.department.StaffAttendenceDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    /**
     * 部门老大查看自己部门员工列表（包括出勤情况）
     * @param bossName  部门老大名字
     * @param bossId    部门老大编号
     * @param departmentId  部门编号
     * @return
     */
    List<DepartmentStaff> getDepartmentStaff(String bossName, int bossId, int departmentId);
    /**
     * 部门老大查看自己部门员工详细信息
     * @param bossName  部门老大名字
     * @param bossId    部门老大编号
     * @param departmentId  部门编号
     * @return
     */
    List<Staff> getStaffInformation(String bossName, int bossId, int departmentId);
    /**
     * 部门老大汇总员工出勤情况
     * @param bossName  部门老大名字
     * @param bossId    部门老大编号
     * @param departmentId  部门编号
     */
    void collectStaffAttendance(String bossName, int bossId, int departmentId);

    /**
     * 提交部门员工出勤情况
     * @param bossName  部门老大名字
     * @param bossId    部门老大编号
     * @param departmentId  部门编号
     * @return
     */
    List<DepartmentStaff> submitStaffAttendance(String bossName, int bossId, int departmentId);

    /**
     * 汇总员工出勤情况
     * @param departmentId 部门id
     * @return
     */
    List<Map<Integer,Map<String,Integer>>> collectUserAttendance(int departmentId);

    /**
     * 员工计算工资所需要的信息(按部门分)
     * @param departmentId 部门id
     * @return 所需信息的列表
     */
    List<StaffAttendenceDTO> getUserInformation(int departmentId);

    /**
     * 获取所有员工计算工资所需要的信息
     * @return 所需信息的列表
     */
    List<StaffAttendenceDTO> getAllUserInformation();
}
