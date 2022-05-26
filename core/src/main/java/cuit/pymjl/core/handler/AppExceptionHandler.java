package cuit.pymjl.core.handler;


import cuit.pymjl.core.result.R;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 19:02
 **/
@Slf4j
@WebServlet(name = "AppExceptionHandler", value = "/appException")
public class AppExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        throwable.printStackTrace();
        log.error(throwable.getMessage());
        response.getWriter().println(R.fail(throwable.getMessage()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
