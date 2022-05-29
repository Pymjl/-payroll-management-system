package cuit.pymjl.core.service.Department.impl;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.department.DepartmentStaff;
import cuit.pymjl.core.entity.department.Staff;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.department.DepartmentMapper;
import cuit.pymjl.core.service.Department.DepartmentService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    SqlSession sqlSession=MybatisUtil.openSession();
    DepartmentMapper departmentMapper=sqlSession.getMapper(DepartmentMapper.class);

    @Override
    public List<DepartmentStaff> getDepartmentStaff(String bossName, int bossId,int departmentId) {
        Department department= departmentMapper.getDepartmentInformation(departmentId);
        List<DepartmentStaff> departmentStaffs=new ArrayList<>();
        if ( department.getDepartmentBossName().equals(bossName) && department.getDepartmentBossId()==bossId){
            departmentStaffs=departmentMapper.getDepartmentStaff(departmentId);
        }else {
            throw new AppException(bossName+"不是该部门老大");
        }
        return departmentStaffs;
    }
    public List<Staff> getStaffInformation(String bossName, int bossId,int departmentId){
        Department department= departmentMapper.getDepartmentInformation(departmentId);
        List<Staff> staffs=new ArrayList<>();
        if ( department.getDepartmentBossName().equals(bossName) && department.getDepartmentBossId()==bossId){
            staffs=departmentMapper.getStaffInformation(departmentId);
        }else {
            throw new AppException(bossName+"不是该部门老大");
        }
        return staffs;
    }
}
