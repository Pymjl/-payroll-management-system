package cuit.pymjl.core.servlet.user;

import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/27 0:35
 **/
@WebServlet(name = "QueryUserServlet", value = "/userInfo")
public class QueryUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getAttribute("userId");
        if (null == userId) {
            throw new AppException("发生未知错误，用户ID为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        User user = userService.queryUserById(Long.parseLong(userId));
        response.getWriter().println(R.success(user));
    }
}
