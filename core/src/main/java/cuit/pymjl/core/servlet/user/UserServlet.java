package cuit.pymjl.core.servlet.user;
/**
 * @author Pymjl
 * @date 2022/5/23 0:26
 * @version 1.0
 **/

import cuit.pymjl.core.util.DruidUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserServlet", value = "/test")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = DruidUtil.getConn();
        response.getWriter().write(String.valueOf(conn));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
