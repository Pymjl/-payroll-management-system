package cuit.pymjl.core.handler;

import cuit.pymjl.core.result.R;
import cuit.pymjl.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 18:37
 **/
@Slf4j
@WebServlet(name = "ExceptionHandler", value = "/exception")
public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        throwable.printStackTrace();
        log.error(throwable.getMessage());
        PrintWriter writer = response.getWriter();
        writer.println(R.fail("发生未知错误，请联系管理员或稍后重试"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
