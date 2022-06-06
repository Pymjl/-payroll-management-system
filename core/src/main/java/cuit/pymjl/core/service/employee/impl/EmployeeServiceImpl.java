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

    /**
     * 工作状态. 缺勤（请假）
     */
    private static final int ABSENCE = 0;

    @Override
    public void employeeClockIn(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //调接口获取部门id，系id
            int result = employeeMapper.addClockIn(userId, Long.parseLong(String.valueOf(1)), Long.parseLong(String.valueOf(1)));
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
    public void employeeDayOff(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            //调用接口获取id,暂时使用固定数据1，1
            int result = 0;
            Employee employee = checkTodayAttendance(userId);
            System.out.println(employee);
            if (null == employee) {
                //今天还未打卡，请假直接插入数据
                result = employeeMapper.addDayOff(userId, Long.parseLong(String.valueOf(1)), Long.parseLong(String.valueOf(1)));
            }
            else {
                //今天已打卡修改状态
                Date nowTime = new Date();
                Timestamp t = new Timestamp(nowTime.getTime());
                int hours = getDifferHour(employee.getCreateTime(), t);
                if (hours >= WORK_HOURS) {
                    employee.setStatus(NORMAL);
                }
                else {
                    employee.setStatus(ABSENCE);
                }
                result = employeeMapper.upClockOut(userId, employee.getCreateTime(), nowTime, hours, employee.getStatus());
            }
            if (result != 1) {
                throw new AppException("请假失败");
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public int getDayOffNumber(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            return employeeMapper.queryDayOffNumber(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public int getLeaveNumber(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            return employeeMapper.queryLeaveNumber(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
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
