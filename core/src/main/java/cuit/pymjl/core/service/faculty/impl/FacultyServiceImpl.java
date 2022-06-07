package cuit.pymjl.core.service.faculty.impl;

import cuit.pymjl.core.entity.faculty.FacultyDetails;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.faculty.FacultyMapper;
import cuit.pymjl.core.service.faculty.FacultyService;
import cuit.pymjl.core.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


public class FacultyServiceImpl implements FacultyService {
    @Override
    public void deleteDepartment(int departmentId, Long facultyId){
        SqlSession sqlSession = MybatisUtil.openSession();
        try{
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            int result = mapper.delDepartment(departmentId, facultyId);
            if(result != 1){
                throw new AppException("删除失败");
            }
        }finally {
            /* 关闭数据库 */
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void addDepartment(FacultyDetails facultyDetails) {
        SqlSession sqlSession = MybatisUtil.openSession();
        try{
            FacultyMapper mapper = sqlSession.getMapper(FacultyMapper.class);
            mapper.addDepartment(facultyDetails);
        }finally {
            MybatisUtil.close(sqlSession);
        }
    }
}
