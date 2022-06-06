package cuit.pymjl.core.servlet.employee;

import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.employee.EmployeeService;
import cuit.pymjl.core.service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Whisper
 */

@WebServlet(name = "ClockOutServlet", value = "/employee/clockOut")
public class ClockOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getAttribute("userId");
        if (null == userId) {
            throw new AppException("发生未知错误，用户ID为空");
        }
        EmployeeService employeeService = SingletonFactory.getInstance(EmployeeServiceImpl.class);
        employeeService.employeeClockOut(Long.parseLong(userId));
        response.getWriter().write(R.success());
    }
}
