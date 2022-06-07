package cuit.pymjl.core.servlet.faculty;

import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.faculty.FacultyService;
import cuit.pymjl.core.service.faculty.impl.FacultyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteDepartmentServlet", value = "/DeleteDepartmentServlet")
public class DeleteDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String departmentId = request.getParameter("departmentId");
        String facultyId = request.getParameter("facultyId");
        /*isBlank()方法,如果给定的字符串为空或仅包含空格代码点，则此方法返回 true ，否则返回 false 。*/
        if ((StrUtil.isBlank(departmentId))||(StrUtil.isBlank(facultyId))) {
            throw new AppException("参数异常");
        }
        FacultyService facultyService = SingletonFactory.getInstance(FacultyServiceImpl.class);
        facultyService.deleteDepartment(Integer.parseInt(departmentId),Long.parseLong(facultyId));
        response.getWriter().println(R.success());
    }
}
