package cuit.pymjl.core.filter;


import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.factory.SingletonFactory;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.service.user.impl.UserServiceImpl;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/27 0:00
 **/
@SuppressWarnings("all")
@Slf4j
public class GlobalFilter implements Filter {
    private static final String BEARER = "Bearer ";
    private static final String METHOD = "OPTIONS";

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("全局过滤器初始化.......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String method = req.getMethod();
        if (uri.equals("/login") || uri.equals("/register") || uri.equals("/") || uri.equals("/favicon.ico") ||
                uri.contains("index.jsp") || uri.contains("druid") || uri.equals("/reset/password") ||
                uri.equals("/code/image") || uri.equals("/code/email") || METHOD.equals(method)) {
            log.info("请求方法为[{}],请求路径为[{}],直接放行", method, uri);
            chain.doFilter(request, response);
        } else {
            log.info("请求路径为[{}],开始拦截鉴权......", uri);
            log.info("开始从Authorization获取token......");
            String header = req.getHeader("Authorization");
            if (header != null && !"".equals(header)) {
                if (header.startsWith(BEARER)) {
                    //获得token
                    String token = header.substring(7);
                    log.info("获取到的token==>[{}]", token);
                    Claims claims = JwtUtils.verifyJwt(token);
                    if (claims == null) {
                        throw new AppException("token异常");
                    }
                    String userId = (String) claims.get("userId");
                    String redisToken = (String) JedisUtils.get(userId);
                    if (StrUtil.isBlank(redisToken) || !redisToken.equals(token)) {
                        throw new AppException("token已过期,请重新登录");
                    }
                    User user = checkIdentity(uri, userId);
                    req.setAttribute("userId", userId);
                    req.setAttribute("userInfo", user);
                    log.info("token验证成功");
                    chain.doFilter(req, response);
                } else {
                    throw new AppException("Token格式错误，应为Bearer Token");
                }
            } else {
                log.error("请先登录");
                throw new AppException("请先登录");
            }
        }
    }

    /**
     * 检查身份
     *
     * @param uri    uri
     * @param userId 用户id
     */
    private User checkIdentity(String uri, String userId) {
        UserService userService = SingletonFactory.getInstance(UserServiceImpl.class);
        User user = userService.queryUserById(Long.parseLong(userId));
        if (uri.contains("admin")) {
            if (!user.getIdentity().equals(IdentityEnum.ADMIN.getIdentity())) {
                throw new AppException("权限不足,该接口需要管理员身份才能访问");
            }
        } else if (user.getIdentity().equals(IdentityEnum.BANNED_USER.getIdentity())) {
            throw new AppException("您的账号已被封禁，请联系管理员解封账户后才能继续访问");
        }
        return user;
    }
}
