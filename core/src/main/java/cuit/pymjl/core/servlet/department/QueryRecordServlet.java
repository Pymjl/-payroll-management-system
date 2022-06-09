package cuit.pymjl.core.servlet.department;
/**
 * @author Pymjl
 * @date 2022/6/9 10:24
 * @version 1.0
 **/

import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.entity.department.DeptInfo;
import cuit.pymjl.core.entity.department.StaffAttendenceDTO;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryRecordServlet", value = "/department/record")
public class QueryRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptId = request.getParameter("deptId");
        String userId = (String) request.getAttribute("userId");
        if (StrUtil.isBlank(deptId)) {
            throw new AppException("参数异常，部门ID不能为空");
        }
        DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
        Boolean isManager = departmentService.isManager(Long.parseLong(userId), Long.parseLong(deptId));
        if (!isManager) {
            throw new AppException("该接口需要组长才能查看，你没有该权限");
        }
        List<StaffAttendenceDTO> information = departmentService.getUserInformation(Integer.parseInt(deptId));
        response.getWriter().println(R.success(information));
    }
}
