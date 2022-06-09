package cuit.pymjl.core.servlet.faculty;

import cuit.pymjl.core.entity.faculty.FacultyDepartment;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AddDepartmentServlet", value = "/AddDepartmentServlet")
public class AddDepartmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        //concat() 方法用于将指定的字符串参数连接到字符串上，语法public String concat(String s)
        //s -- 要连接的字符串。返回连接后的新字符串。
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }

        FacultyDepartment facultyDepartment = JsonUtils.toBean(json, FacultyDepartment.class);
        //Objects类的静态方法。主要的作用是提前判断对象是否为空，如果对象为空的话，提前抛出异常。
        if (Objects.requireNonNull(facultyDepartment).getFacultyId() == null || facultyDepartment.getDepartmentId() == 0 || facultyDepartment.getDepartmentBossId() == 0 || facultyDepartment.getDepartmentName() == null) {
            throw new AppException("参数为空");
        }
        FacultyService facultyService= SingletonFactory.getInstance(FacultyServiceImpl.class);
        //将新增部门插入到两张表中
        facultyService.addDepartment(facultyDepartment.getFacultyId(), facultyDepartment.getDepartmentId());
        facultyService.addDepartment1(facultyDepartment.getDepartmentId(), facultyDepartment.getDepartmentName(), facultyDepartment.getDepartmentBossId());
        response.getWriter().println(R.success());
    }
}
