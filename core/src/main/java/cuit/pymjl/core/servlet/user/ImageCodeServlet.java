package cuit.pymjl.core.servlet.user;

import cn.hutool.captcha.CircleCaptcha;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.service.UserService;
import cuit.pymjl.core.service.impl.UserServiceImpl;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.MybatisUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 23:03
 **/

@WebServlet(name = "ImageCodeServlet", value = "/image/code")
public class ImageCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        String key = request.getParameter("key");
        CircleCaptcha verifyCode = userService.getImageVerifyCode(key);
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        verifyCode.write(outputStream);
        if (outputStream != null) {
            outputStream.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        throw new RuntimeException("请求方式不对");
    }
}
