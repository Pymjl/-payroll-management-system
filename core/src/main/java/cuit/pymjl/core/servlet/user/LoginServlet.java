package cuit.pymjl.core.servlet.user;


import cuit.pymjl.core.entity.user.dto.UserInfoDTO;
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

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/23 0:26
 **/
@Slf4j
@WebServlet(name = "UserServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("开始从Body中读取Json字符串......");
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        UserInfoDTO userInfoDTO = JsonUtils.toBean(json, UserInfoDTO.class);
        log.info("读取Json字符串成功==>[{}]", userInfoDTO);
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        String token = userService.login(userInfoDTO);
        response.getWriter().println(R.success(token));
    }
}
