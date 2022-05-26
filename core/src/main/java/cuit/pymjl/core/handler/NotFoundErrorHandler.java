package cuit.pymjl.core.handler;
/**
 * @author Pymjl
 * @date 2022/5/26 18:51
 * @version 1.0
 **/

import cuit.pymjl.core.result.R;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NotFoundErrorHandler", value = "/notFound")
public class NotFoundErrorHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println(R.fail("你请求的页面不存在,请检查你的url或者请求方式"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
