package cuit.pymjl.core.servlet.user;

import cn.hutool.core.util.StrUtil;
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
 * @date 2022/5/27 23:47
 **/
@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getAttribute("userId");
        if (StrUtil.isBlank(userId)) {
            throw new AppException("发生未知错误，request中的userId为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.logout(Long.parseLong(userId));
        response.getWriter().write(R.success());
    }
}
