package cuit.pymjl.core.servlet.user.admin;


import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.constant.IdentityEnum;
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
 * @date 2022/6/2 18:11
 **/
@WebServlet(name = "DeleteUserServlet", value = "/del/user")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (StrUtil.isBlank(id)) {
            throw new AppException("参数异常");
        }
        User userInfo = (User) request.getAttribute("userInfo");
        Integer identity = userInfo.getIdentity();
        if (!IdentityEnum.ADMIN.getIdentity().equals(identity)) {
            throw new AppException("你没有该权限");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.deleteUser(Long.parseLong(id));
        response.getWriter().println(R.success());
    }
}
