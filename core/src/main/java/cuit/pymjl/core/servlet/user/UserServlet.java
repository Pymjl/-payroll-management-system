package cuit.pymjl.core.servlet.user;


import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.util.MybatisUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/23 0:26
 **/
@WebServlet(name = "UserServlet", value = "/test")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserMapper userMapper = MybatisUtil.openSession().getMapper(UserMapper.class);
        List<User> users = userMapper.queryUsers(1, 5);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        for (User user : users) {
            writer.write(user.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
