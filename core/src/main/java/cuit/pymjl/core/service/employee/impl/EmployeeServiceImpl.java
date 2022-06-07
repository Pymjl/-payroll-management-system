package cuit.pymjl.core.service.employee.impl;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.department.Staff;
import cuit.pymjl.core.entity.employee.Employee;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.mapper.employee.EmployeeMapper;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
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
            Employee employee = checkTodayAttendance(userId);
            if (null == employee) {
                //未签到，插入数据
                //调接口获取部门id，系id
                DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
                int id = Integer.parseInt(String.valueOf(userId));
                Staff staff = departmentService.getStaff(id);
                int result = employeeMapper.addClockIn(userId, Long.parseLong(String.valueOf(staff.getDepartmentId())), Long.parseLong(String.valueOf(staff.getFacultyId())));
                if (result != 1) {
                    throw new AppException("打卡失败");
                }
            }
            else {
                //已签到
                throw new AppException("今日已签到，请勿重复打卡");
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
            int result = 0;
            Employee employee = checkTodayAttendance(userId);
            if (null == employee) {
                //今天还未打卡，请假直接插入数据
                //调接口获取部门id，系id
                DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
                int id = Integer.parseInt(String.valueOf(userId));
                Staff staff = departmentService.getStaff(id);
                result = employeeMapper.addDayOff(userId, Long.parseLong(String.valueOf(staff.getDepartmentId())), Long.parseLong(String.valueOf(staff.getFacultyId())));
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
            if (null == employee) {
                //未签到
                throw new AppException("签退失败，请先签到");
            }
            else {
                //计算工作时长
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
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public Page<Employee> queryPersonalAttendance(Long userId, Integer pageNum, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            return employeeMapper.queryPersonalAttendance(userId, pageNum, pageSize);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
