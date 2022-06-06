package cuit.pymjl.core.servlet.employee;

import cuit.pymjl.core.entity.employee.Employee;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.employee.EmployeeService;
import cuit.pymjl.core.service.employee.impl.EmployeeServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Whisper
 */

@WebServlet(name = "ClockInServlet", value = "/employee/clockIn")
public class ClockInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        Employee employee = JsonUtils.toBean(json, Employee.class);
        if (employee.getUserId() == 0 || employee.getDepartmentId() == 0 || employee.getFacultyId() == 0) {
            throw new AppException("参数为空");
        }
        EmployeeService employeeService = SingletonFactory.getInstance(EmployeeServiceImpl.class);
        System.out.print(employee);
        employeeService.employeeClockIn(employee.getUserId(), employee.getDepartmentId(), employee.getFacultyId());
        response.getWriter().write(R.success());
    }
}
