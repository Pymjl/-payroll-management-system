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
 * @date 2022/5/26 18:21
 **/
@WebServlet(name = "EmailCodeServlet", value = "/code/email")
public class EmailCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String key = request.getParameter("key");
        String code = request.getParameter("code");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            throw new AppException("参数不能为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.getEmailCode(username, key, code);
        response.getWriter().println(R.success());
    }
}
