package cuit.pymjl.core.service;

import cuit.pymjl.core.mapper.department.DepartmentMapper;
import cuit.pymjl.core.service.faculty.FacultyService;
import cuit.pymjl.core.service.faculty.impl.FacultyServiceImpl;
import cuit.pymjl.core.util.MybatisUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/9 11:09
 **/
public class FactultTest {
    FacultyService facultyService = new FacultyServiceImpl();

    @Test
    void test1() {
        DepartmentMapper mapper = MybatisUtil.openSession().getMapper(DepartmentMapper.class);
        System.out.println(mapper.getDepartmentInformation(1));
    }
}
