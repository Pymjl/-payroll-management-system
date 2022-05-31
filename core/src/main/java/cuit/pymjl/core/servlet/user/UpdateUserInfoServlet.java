package cuit.pymjl.core.servlet.user;


import cn.hutool.core.util.ObjectUtil;
import cuit.pymjl.core.entity.user.dto.UserDTO;
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

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/31 20:52
 **/
@Slf4j
@WebServlet(name = "UpdateUserInfoServlet", value = "/update/userInfo")
public class UpdateUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("开始从Body中读取Json字符串......");
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json = json.concat(line);
        }
        UserDTO userDTO = JsonUtils.toBean(json, UserDTO.class);
        if (ObjectUtil.isNull(userDTO) ||
                (userDTO.getAccount() == null && userDTO.getAge() == null
                        && userDTO.getNickname() == null && userDTO.getPhone() == null)) {
            throw new AppException("所有参数全为空");
        }
        log.info("读取数据成功==>[{}]", userDTO);
        String userId = (String) request.getAttribute("userId");
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        userService.updateUserInfo(userDTO, Long.parseLong(userId));
        response.getWriter().println(R.success());
    }
}
