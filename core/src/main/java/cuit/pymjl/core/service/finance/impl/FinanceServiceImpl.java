package cuit.pymjl.core.service.finance.impl;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.department.StaffAttendenceDTO;
import cuit.pymjl.core.entity.finance.BasicWage;
import cuit.pymjl.core.entity.finance.WageDetails;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.finance.FinanceMapper;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
import cuit.pymjl.core.service.finance.FinanceService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;


/**
 * @author Riou
 */
public class FinanceServiceImpl implements FinanceService {

    public FinanceServiceImpl() {

    }

    @Override
    public BasicWage queryBasicWage() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            return financeMapper.queryBasicWage();
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void updateBasicWage(BasicWage basicWage){
        basicWage.setUpdateTime(new Date());
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            int result = financeMapper.updateBasicWage(basicWage);
            if(result != 1) {
                throw new AppException("更新工资失败!");
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public int summarySheet(){
        int basicWage; // 基本工资
        int bonus; // 奖金
        int fine; // 罚款
        int tax; // 税收
        int sumWage; // 总工资

        // 获取所有员工的出勤情况
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<StaffAttendenceDTO> staffAttendanceList = departmentService.getAllUserInformation();

        SqlSession sqlSession = null;
        int result = 1;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            int basicManager = financeMapper.queryBasicWage().getManagerWage();
            int basicCommon = financeMapper.queryBasicWage().getCommonWage();
            // 将工资详情写入工资表
            for (StaffAttendenceDTO staffAttendenceDTO : staffAttendanceList) {
                // 定义工资条空对象
                WageDetails wageDetails = new WageDetails();
                // 基本工资
                if (departmentService.isManager(staffAttendenceDTO.getId(), staffAttendenceDTO.getDepartmentName())) {
                    basicWage = basicManager;
                } else {
                    basicWage = basicCommon;
                }
                // 奖金与罚款
                if (staffAttendenceDTO.getLateNum() == 0 && staffAttendenceDTO.getLeaveNum() == 0) {
                    bonus = 300;
                    fine = 0;
                } else {
                    bonus = 0;
                    fine = 50 * staffAttendenceDTO.getLateNum() + 200 * staffAttendenceDTO.getLeaveNum();
                }
                // 税收
                if (basicWage + bonus - fine > 5000) {
                    tax = (int) Math.round((basicWage + bonus - fine) * 0.03);
                } else {
                    tax = 0;
                }
                sumWage = basicWage + bonus - fine - tax;
                wageDetails.setEmployeeId(staffAttendenceDTO.getId());
                wageDetails.setName(staffAttendenceDTO.getUsername());
                wageDetails.setBasicWage(basicWage);
                wageDetails.setBonus(bonus);
                wageDetails.setFine(fine);
                wageDetails.setTax(tax);
                wageDetails.setSumWage(sumWage);
                wageDetails.setAccountNumber(staffAttendenceDTO.getAccount());
                wageDetails.setCreateTime(new Date());
                wageDetails.setStatus(1);
                wageDetails.setDepartmentName(staffAttendenceDTO.getDepartmentName());
                result *= financeMapper.addWageSheet(wageDetails);
            }

        } finally {
            MybatisUtil.close(sqlSession);
        }
        return result;
    }

    @Override
    public Page<WageDetails> getAllWageSheet(int pageNum, int pageSize){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            return financeMapper.queryAllWageSheet(pageNum, pageSize);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public WageDetails getWageSheetById(Long id){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            FinanceMapper financeMapper = sqlSession.getMapper(FinanceMapper.class);
            return financeMapper.queryWageSheetById(id);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
