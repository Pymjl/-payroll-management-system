package cuit.pymjl.core.service;


import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/8 18:27
 **/
public class DepartmentServiceTest {
    DepartmentService departmentService = new DepartmentServiceImpl();

    @Test
    void isManager() {
        System.out.println(departmentService.isManager(1L, "办公室"));
    }
}
