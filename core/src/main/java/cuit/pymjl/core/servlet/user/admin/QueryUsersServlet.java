package cuit.pymjl.core.servlet.user.admin;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.PageResult;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.util.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/28 0:05
 **/
@WebServlet(name = "QueryUsersServlet", value = "/admin/users")
public class QueryUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        if (StrUtil.isBlank(pageNum) || StrUtil.isBlank(pageSize)) {
            throw new AppException("参数异常，参数不能为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        Page<User> users = userService.queryUsers(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        response.getWriter().write(JsonUtils.toString(new PageResult<>(users)));
    }
}
