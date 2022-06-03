package cuit.pymjl.core.service.department.impl;

import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.department.DepartmentStaff;
import cuit.pymjl.core.entity.department.Staff;
import cuit.pymjl.core.entity.department.StaffAttendance;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.department.DepartmentMapper;
import cuit.pymjl.core.service.department.DepartmentService;
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
    @Override
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

    @Override
    public void collectStaffAttendance(String bossName, int bossId, int departmentId) {
        Department department= departmentMapper.getDepartmentInformation(departmentId);
        List<DepartmentStaff> departmentStaffs=new ArrayList<>();
        if ( department.getDepartmentBossName().equals(bossName) && department.getDepartmentBossId()==bossId){
            departmentStaffs=departmentMapper.getDepartmentStaff(departmentId);
            List<StaffAttendance> staffAttendanceList=departmentMapper.getStaffAttendance(departmentId);
            for(int i=0;i<departmentStaffs.size();i++){
                for(int j=0;j<staffAttendanceList.size();j++){
                    if(departmentStaffs.get(i).getStaffId()==staffAttendanceList.get(j).getUserId()){
                        if(staffAttendanceList.get(j).status==-1){
                            departmentStaffs.get(i).setStaffLeaveNum(departmentStaffs.get(i).getStaffLeaveNum()+1);
                        }else if(staffAttendanceList.get(j).status==0){
                            departmentStaffs.get(i).setStaffLateNum(departmentStaffs.get(i).getStaffLateNum()+1);
                        }
                    }
                }
                departmentMapper.updateDepartmentStaff(departmentStaffs.get(i).getStaffId(),departmentStaffs.get(i).getStaffLeaveNum(),departmentStaffs.get(i).getStaffLateNum());
            }
        }else {
            throw new AppException(bossName+"不是该部门老大");
        }
    }
    @Override
    public List<Staff> submitStaffAttendance(String bossName, int bossId,int departmentId){
        Department department= departmentMapper.getDepartmentInformation(departmentId);
        List<Staff> staffs=new ArrayList<>();
        if ( department.getDepartmentBossName().equals(bossName) && department.getDepartmentBossId()==bossId){
            collectStaffAttendance("小明",1,1);
            staffs=departmentMapper.getStaffInformation(departmentId);
        }else {
            throw new AppException(bossName+"不是该部门老大");
        }
        return staffs;
    }
}
