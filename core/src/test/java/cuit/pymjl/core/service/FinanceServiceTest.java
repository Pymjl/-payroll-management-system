package cuit.pymjl.core.service;

import cuit.pymjl.core.entity.department.StaffAttendenceDTO;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FinanceServiceTest {
    @Test
    void ASTest() {
        DepartmentService departmentService = new DepartmentServiceImpl();
        List<StaffAttendenceDTO> staffAttendanceList = departmentService.getAllUserInformation();
        for (StaffAttendenceDTO staffAttendenceDTO : staffAttendanceList) {
            System.out.println(staffAttendenceDTO.getId());
            System.out.println(staffAttendenceDTO.getDepartmentName());
        }
    }
}
