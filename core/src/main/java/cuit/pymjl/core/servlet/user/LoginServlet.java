package cuit.pymjl.core.servlet.user;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
