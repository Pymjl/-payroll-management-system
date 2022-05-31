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
 * @date 2022/5/31 12:47
 **/
@Slf4j
@WebServlet(name = "ResetPasswordServlet", value = "/reset/password")
public class ResetPasswordServlet extends HttpServlet {
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

        String username = map.get("username");
        String code = map.get("code");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(code)) {
            throw new AppException("参数不能为空");
        }
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.resetPassword(username, code);
        response.getWriter().write(R.success());
    }
}
