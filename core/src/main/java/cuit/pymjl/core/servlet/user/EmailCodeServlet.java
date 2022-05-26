package cuit.pymjl.core.servlet.user;
/**
 * @author Pymjl
 * @date 2022/5/26 18:21
 * @version 1.0
 **/

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmailCodeServlet", value = "/code/email")
public class EmailCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

    }
}
