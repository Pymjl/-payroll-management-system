package cuit.pymjl.core.filter;


import cn.hutool.core.util.StrUtil;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * TODO 有BUG，明天再修复
 *
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/27 0:00
 **/
@SuppressWarnings("all")
@Slf4j
@WebFilter(filterName = "GlobalFilter", urlPatterns = "/*")
public class GlobalFilter implements Filter {
    private static final String BEARER = "Bearer ";

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("全局过滤器初始化.......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (uri.equals("/login") || uri.equals("/register") || uri.equals("/") ||
                uri.contains("index.jsp") || uri.contains("druid") ||
                uri.equals("/code/image") || uri.equals("/code/email")) {
            log.info("请求路径为[{}],直接放行", uri);
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
                    //TODO 进行简单的鉴权
                    req.setAttribute("userId", userId);
                    chain.doFilter(req, response);
                } else {
                    throw new AppException("Token格式错误，应为Bearer Token");
                }
            } else {
                throw new AppException("请先登录");
            }
        }
    }
}
