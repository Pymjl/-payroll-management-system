package cuit.pymjl.core.servlet.faculty;

import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.faculty.FacultyService;
import cuit.pymjl.core.service.faculty.impl.FacultyServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryDepartmentInformationServlet", value = "/QueryDepartmentInformationServlet")
public class QueryDepartmentInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String facultyId = request.getParameter("facultyId");
        /*isBlank()方法,如果给定的字符串为空或仅包含空格代码点，则此方法返回 true ，否则返回 false 。*/
        if (StrUtil.isBlank(facultyId)) {
            throw new AppException("参数异常");
        }
        FacultyService facultyService = SingletonFactory.getInstance(FacultyServiceImpl.class);
        List<Department> list = facultyService.getDepartmentInformations(Long.valueOf(facultyId));
        System.out.println(list);
        response.getWriter().write(JsonUtils.toString(list));
    }
}
