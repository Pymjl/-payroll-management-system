package cuit.pymjl.core.service.faculty.impl;

import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.mapper.department.DepartmentMapper;
import cuit.pymjl.core.mapper.faculty.FacultyMapper;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
import cuit.pymjl.core.service.faculty.FacultyService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FacultyServiceImpl implements FacultyService {
    @Override
    public void deleteDepartment(int departmentId){
        SqlSession sqlSession = MybatisUtil.openSession();
        try{
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            int result = mapper.delDepartment(departmentId);
            if(result != 1){
                throw new AppException("删除失败");
            }
        }finally {
            /* 关闭数据库 */
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void addDepartment(Long facultyId, int departmentId) {
        SqlSession sqlSession = MybatisUtil.openSession();
        try{
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            mapper.addDepartment(facultyId, departmentId);
        }finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void addDepartment1(int departmentId, String departmentName, int departmentBossId){
        SqlSession sqlSession = MybatisUtil.openSession();
        try{
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            mapper.addDepartment1(departmentId, departmentName, departmentBossId);
        }finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public Department getDepartmentInformation(int departmentId){
        SqlSession sqlSession = MybatisUtil.openSession();
        //Department departmentsInformation;
        try{
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department departmentsInformation = mapper.getDepartment(departmentId);
            return departmentsInformation;
        }finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public List<Department> getDepartmentInformations(Long facultyId){
        SqlSession sqlSession = MybatisUtil.openSession();
        //声明list,将得到的部门id赋值给该list
        List<Map<String, Integer>> mapperDepartmentId;
        List<Department> list = new ArrayList<>();
        FacultyService facultyService = SingletonFactory.getInstance(FacultyServiceImpl.class);
        try {
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            //list<map<String,Object>>是定义了一个List的集合变量，是map的一个集合；map是那个list的其中一个值。
            //Map对象的键是String类型,值是Object类型
            mapperDepartmentId = mapper.getDepartmentId(facultyId);
            //遍历List<Map<String, Integer>>
            for(Map<String, Integer> m : mapperDepartmentId){
                //通过得到key键值，然后获取value
                for(String k : m.keySet()){
                    Integer departmentId = m.get(k);
                    Department department = facultyService.getDepartmentInformation(departmentId);
                    //将部门信息存入list中
                    list.add(department);
                }
            }
            return list;
        }finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
