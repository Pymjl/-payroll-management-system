package cuit.pymjl.core.servlet.employee;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.employee.Employee;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.PageResult;
import cuit.pymjl.core.service.employee.EmployeeService;
import cuit.pymjl.core.service.employee.impl.EmployeeServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Whisper
 */

@WebServlet(name = "QueryAttendanceListServlet", value = "/employee/attendanceList")
public class QueryAttendanceListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getAttribute("userId");
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        if (null == userId) {
            throw new AppException("发生未知错误，用户ID为空");
        }
        EmployeeService employeeService = SingletonFactory.getInstance(EmployeeServiceImpl.class);
        Page<Employee> employees = employeeService.queryPersonalAttendance(Long.parseLong(userId), Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        response.getWriter().println(JsonUtils.toString(new PageResult<>(employees)));
    }
}
