package cuit.pymjl.core.servlet.department;

import cuit.pymjl.core.entity.department.Department;
import cuit.pymjl.core.entity.department.Staff;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.service.department.DepartmentService;
import cuit.pymjl.core.service.department.impl.DepartmentServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckStaffInformationServlet", value = "/CheckStaffInformationServlet")
public class CheckStaffInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader br=new BufferedReader(request.getReader());
        String json=null;
        String line=null;
        while ((line = br.readLine()) != null) {
            json=line;
        }
        Department department =new Department();
        department = JsonUtils.toBean(json,Department.class);
        if(department.getDepartmentBossId()==0||department.getDepartmentId()==0){
            throw new AppException("传参不能为空");
        }
        DepartmentService departmentService = SingletonFactory.getInstance(DepartmentServiceImpl.class);
        List<User> list= departmentService.getStaffInformation(department.getDepartmentBossId(),department.getDepartmentId());
        response.getWriter().println(JsonUtils.toString(list));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
