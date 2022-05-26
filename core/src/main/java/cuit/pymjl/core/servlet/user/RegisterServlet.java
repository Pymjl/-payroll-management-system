package cuit.pymjl.core.servlet.user;


import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 23:12
 **/
@Slf4j
@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("开始从Body中读取Json字符串......");
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        UserDTO userDTO = JsonUtils.toBean(json, UserDTO.class);
        log.info("读取数据成功==>[{}]", userDTO);
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.register(userDTO);
        response.getWriter().println(R.success());
    }
}
