package cuit.pymjl.core.servlet.user.admin;
/**
 * @author Pymjl
 * @date 2022/5/31 20:16
 * @version 1.0
 **/

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

@WebServlet(name = "BanUserServlet", value = "/admin/banUser")
public class BanUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        if (StrUtil.isBlank(userId)) {
            throw new AppException("用户ID不能为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.banUser(Long.parseLong(userId));
        response.getWriter().println(R.success());
    }
}
