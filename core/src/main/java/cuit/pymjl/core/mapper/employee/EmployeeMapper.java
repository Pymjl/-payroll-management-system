package cuit.pymjl.core.mapper.employee;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.employee.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Whisper
 */

public interface EmployeeMapper {
    /**
     * 员工签到
     *
     * @param userId 用户id
     * @param departmentId 部门id
     * @param facultyId 系id
     * @return int
     */
    int addClockIn(@Param("userId") Long userId,
                   @Param("departmentId") Long departmentId,
                   @Param("facultyId") Long facultyId);

    /**
     * 员工签退
     * @param userId 用户id
     * @param createTime 签到时间
     * @param updateTime 签退时间
     * @param workHours 工作时长
     * @param status 工作状态
     * @return int
     */
    int upClockOut(@Param("userId") Long userId,
                   @Param("createTime") Date createTime,
                   @Param("updateTime") Date updateTime,
                   @Param("workHours") Integer workHours,
                   @Param("status") Integer status);

    /**
     * 员工请假（缺勤）
     *@param userId 用户id
     * @param departmentId 部门id
     * @param facultyId 系id
     * @return int
     */
    int addDayOff(@Param("userId") Long userId,
                  @Param("departmentId") Long departmentId,
                  @Param("facultyId") Long facultyId);

    /**
     * 通过用户id查询个人今日考勤
     * @param userId 用户ID
     * @return 考勤信息
     */
    Employee queryTodayAttendance(@Param("userId") Long userId);

    /**
     * 通过用户id查询个人打卡列表
     * @param userId 用户id
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return {@code List<Employee>}
     */
    Page<Employee> queryPersonalAttendance(@Param("userId") Long userId,
                                           @Param("pageNum") int pageNum,
                                           @Param("pageSize") int pageSize);

    /**
     * 统计员工缺勤次数
     * @param userId 用户id
     * @return integer
     */
    Integer queryDayOffNumber(@Param("userId") Long userId);

    /**
     * 统计员工早退次数
     * @param userId 用户id
     * @return integer
     */
    Integer queryLeaveNumber(@Param("userId") Long userId);

}
