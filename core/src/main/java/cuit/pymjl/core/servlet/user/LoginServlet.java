package cuit.pymjl.core.servlet.user;


import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.user.UserMapper;
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
@WebServlet(name = "UserServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
