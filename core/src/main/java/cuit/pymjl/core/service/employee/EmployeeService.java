package cuit.pymjl.core.service.employee;

import cuit.pymjl.core.entity.employee.Employee;

import java.util.Date;

public interface EmployeeService {
    /**
     * 员工打卡
     * @param userId 用户ID
     */
    void employeeClockIn(Long userId);

    /**
     *员工签退
     * @param userId 用户ID
     */
    void employeeClockOut(Long userId);

    /**
     * 查询员工今日打卡
     * @param userId 用户ID
     * @return Employee
     */
    Employee checkTodayAttendance(Long userId);

    /**
     * 计算工作时长
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return int 工作时长
     */
    int getDifferHour(Date startDate, Date endDate);

    /**
     * 员工请假
     * @param userId 用户ID
     */
    void employeeDayOff(Long userId);

    /**
     * 查询员工缺勤天数
     * @param userId 用户ID
     * @return int 缺勤天数
     */
    int getDayOffNumber(Long userId);

    /**
     * 查询员工早退天数
     * @param userId 用户ID
     * @return int 早退天数
     */
    int getLeaveNumber(Long userId);
}
