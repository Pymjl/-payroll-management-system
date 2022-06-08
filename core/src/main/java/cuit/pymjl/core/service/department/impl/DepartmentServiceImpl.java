package cuit.pymjl.core.service.department.impl;

import cuit.pymjl.core.entity.department.*;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.mapper.department.DepartmentMapper;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentServiceImpl implements DepartmentService {
    SqlSession sqlSession = MybatisUtil.openSession();
    DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

    @Override
    public List<DepartmentStaff> getDepartmentStaff(int bossId, int departmentId) {
        Department department = departmentMapper.getDepartmentInformation(departmentId);
        List<DepartmentStaff> departmentStaffs = new ArrayList<>();
        if (department.getDepartmentBossId() == bossId) {
            departmentStaffs = departmentMapper.getDepartmentStaff(departmentId);
        } else {
            throw new AppException("编号为" + bossId + "不是该部门老大");
        }
        return departmentStaffs;
    }

    @Override
    public List<User> getStaffInformation(int bossId, int departmentId) {
        List<User> userList = new ArrayList<>();
        DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        List<DepartmentStaff> list = departmentService.getDepartmentStaff(bossId, departmentId);
        for (int i = 0; i < list.size(); i++) {
            User user = userService.queryUserById(list.get(i).getStaffId().longValue());
            userList.add(user);
        }
        return userList;
    }

    //    @Override
//    public void collectStaffAttendance(String bossName, int bossId, int departmentId) {
//        Department department= departmentMapper.getDepartmentInformation(departmentId);
//        List<DepartmentStaff> departmentStaffs=new ArrayList<>();
//        if (department.getDepartmentBossId()==bossId){
//            departmentStaffs=departmentMapper.getDepartmentStaff(departmentId);
//            List<StaffAttendance> staffAttendanceList=departmentMapper.getStaffAttendance(departmentId);
//            for(int i=0;i<departmentStaffs.size();i++){
//                for(int j=0;j<staffAttendanceList.size();j++){
//                    if(departmentStaffs.get(i).getStaffId().equals(staffAttendanceList.get(j).getUserId())){
//                        if(staffAttendanceList.get(j).status==-1){
//                            departmentStaffs.get(i).setStaffLeaveNum(departmentStaffs.get(i).getStaffLeaveNum()+1);
//                        }else if(staffAttendanceList.get(j).status==0){
//                            departmentStaffs.get(i).setStaffLateNum(departmentStaffs.get(i).getStaffLateNum()+1);
//                        }
//                    }
//                }
//                departmentMapper.updateDepartmentStaff(departmentStaffs.get(i).getStaffId(),departmentStaffs.get(i).getStaffLeaveNum(),departmentStaffs.get(i).getStaffLateNum());
//            }
//        }else {
//            throw new AppException(bossName+"不是该部门老大");
//        }
//    }
//    @Override
//    public List<DepartmentStaff> submitStaffAttendance(String bossName, int bossId,int departmentId){
//        Department department= departmentMapper.getDepartmentInformation(departmentId);
//        List<DepartmentStaff> staffs=new ArrayList<>();
//        if ( department.getDepartmentBossName().equals(bossName) && department.getDepartmentBossId()==bossId){
//            collectStaffAttendance("小明",1,1);
//            staffs=departmentMapper.getDepartmentStaff(departmentId);
//        }else {
//            throw new AppException(bossName+"不是该部门老大");
//        }
//        return staffs;
//    }
    @Override
    public List<Map<Integer, Map<String, Integer>>> collectUserAttendance(int departmentId) {
        List<StaffAttendance> list = departmentMapper.getStaffAttendance(departmentId);
        List<DepartmentStaff> list2 = departmentMapper.getDepartmentStaff(departmentId);
        Map<Integer, Map<String, Integer>> map = new HashMap<Integer, Map<String, Integer>>();
        int[][] time = new int[list2.size()][2];
        for (int i = 0; i < list2.size(); i++) {
            time[i][0] = 0;
            time[i][1] = 0;
        }
        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list2.get(i).getStaffId().equals(list.get(j).getUserId())) {
                    if (list.get(j).getStatus() == -1) {
                        time[i][0]++;
                    } else if (list.get(j).getStatus() == 0) {
                        time[i][1]++;
                    }
                }
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("leaveNum", time[i][0]);
            map1.put("lateNum", time[i][1]);
            map.put(list2.get(i).getStaffId(), map1);
        }
        List<Map<Integer, Map<String, Integer>>> list1 = new ArrayList<>();
        list1.add(map);
        return list1;
    }

    @Override
    public List<StaffAttendenceDTO> getUserInformation(int departmentId) {
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
        List<DepartmentStaff> list = departmentMapper.getDepartmentStaff(departmentId);
        List<Map<Integer, Map<String, Integer>>> mapList = departmentService.collectUserAttendance(departmentId);
        List<StaffAttendenceDTO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StaffAttendenceDTO staffAttendenceDTO = new StaffAttendenceDTO();
            staffAttendenceDTO.setAvatar(userService.queryUserById(list.get(i).getStaffId().longValue()).getAvatar());
            staffAttendenceDTO.setId(list.get(i).getStaffId().longValue());
            staffAttendenceDTO.setUsername(list.get(i).getStaffName());
            staffAttendenceDTO.setDepartmentName(list.get(i).getStaffDepartmentName());
            staffAttendenceDTO.setLateNum(mapList.get(0).get(list.get(i).getStaffId()).get("lateNum"));
            staffAttendenceDTO.setLeaveNum(mapList.get(0).get(list.get(i).getStaffId()).get("leaveNum"));
            staffAttendenceDTO.setAccount(userService.queryUserById(list.get(i).getStaffId().longValue()).getAccount());
            list1.add(staffAttendenceDTO);
        }
        return list1;
    }


    @Override
    public List<StaffAttendenceDTO> getAllUserInformation() {
        List<StaffAttendenceDTO> staffAttendenceDTOList = departmentMapper.getAllStaffInformation();
        for (int i = 0; i < staffAttendenceDTOList.size(); i++) {
            List<StaffAttendance> staffAttendanceList = departmentMapper.getStaffAttendanceById(staffAttendenceDTOList.get(i).getId().intValue());
            int leaveNum = 0, lateNum = 0;
            for (int j = 0; j < staffAttendanceList.size(); j++) {
                if (staffAttendanceList.get(j).getStatus() == -1) {
                    leaveNum++;
                } else if (staffAttendanceList.get(j).getStatus() == 0) {
                    lateNum++;
                }
            }
            staffAttendenceDTOList.get(i).setLateNum(lateNum);
            staffAttendenceDTOList.get(i).setLeaveNum(leaveNum);
        }
        return staffAttendenceDTOList;
    }

    @Override
    public Staff getStaff(int staffId) {
        Staff staff = departmentMapper.getStaff(staffId);
        return staff;
    }

    @Override
    public DeptInfo getDeptInfoByUserId(Long userId) {
        SqlSession sqlSession = MybatisUtil.openSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            return departmentMapper.getDeptAndFacultyInfoByUserId(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public Boolean isManager(Long userId, String deptName) {
        SqlSession sqlSession = MybatisUtil.openSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            Long managerId = departmentMapper.selectManager(deptName);
            return managerId.equals(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
