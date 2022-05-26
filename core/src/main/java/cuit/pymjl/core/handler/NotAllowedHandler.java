package cuit.pymjl.core.handler;
/**
 * @author Pymjl
 * @date 2022/5/26 23:23
 * @version 1.0
 **/

import cuit.pymjl.core.result.R;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NotAllowedHandler", value = "/notAllowedHandler")
public class NotAllowedHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println(R.fail("你请求的资源不允许以该方式请求，请稍后重试"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
