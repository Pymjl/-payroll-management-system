package cuit.pymjl.core.service.employee.impl;

import cuit.pymjl.core.entity.employee.Employee;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.employee.EmployeeMapper;
import cuit.pymjl.core.service.employee.EmployeeService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(){

    }

    /**
     * 基础工作时长
     */
    private static final int WORK_HOURS = 8;

    /**
     * 工作状态. 正常
     */
    private static final int NORMAL = 1;

    /**
     * 工作状态. 早退
     */
    private static final int LEAVE_EARLY = -1;

    @Override
    public void employeeClockIn(Long userId, Long departmentId, Long facultyId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            int result = employeeMapper.addClockIn(userId, departmentId, facultyId);
            if (result != 1) {
                throw new AppException("打卡失败");
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public Employee checkTodayAttendance(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            return employeeMapper.queryTodayAttendance(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public int getDifferHour(Date startDate, Date endDate) {
        long dayM = 1000 * 24 * 60 * 60;
        long hourM = 1000 * 60 * 60;
        long differ = endDate.getTime() - startDate.getTime();
        long hour = differ % dayM / hourM + 24 * (differ / dayM);
        return Integer.parseInt(String.valueOf(hour));
    }

    @Override
    public void employeeClockOut(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = checkTodayAttendance(userId);
            Date time = new Date();
            Timestamp t = new Timestamp(time.getTime());
            int hours = getDifferHour(employee.getCreateTime(), t);
            if (hours >= WORK_HOURS) {
                employee.setStatus(NORMAL);
            }
            else {
                employee.setStatus(LEAVE_EARLY);
            }
            int result = employeeMapper.upClockOut(userId, employee.getCreateTime(), time, hours, employee.getStatus());
            if (result != 1) {
                throw new AppException("签退失败");
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }


}
