package cuit.pymjl.core.servlet.user;


import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/31 13:18
 **/
@Slf4j
@WebServlet(name = "UpdatePasswordServlet", value = "/update/password")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("开始从Body中读取Json字符串......");
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        Map<String, String> map = JsonUtils.toMap(json, String.class, String.class);
        if (map == null) {
            throw new AppException("参数异常");
        }
        log.info("数据读取成功");
        String password = map.get("password");
        if (StrUtil.isBlank(password) || password.length() < 6) {
            throw new AppException("密码格式错误，密码长度至少为6");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            throw new AppException("发生未知错误，request域中userId为空");
        }
        userService.updatePassword(password, Long.parseLong(userId));
        response.getWriter().println(R.success());
    }
}
