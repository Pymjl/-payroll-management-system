package cuit.pymjl.core.filter;

import cuit.pymjl.core.mapper.user.UserMapper;
import cuit.pymjl.core.util.MybatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 22:23
 **/
@Slf4j
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("编码器过滤器初始化......");
        log.info("开始初始化Druid连接池......");
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.selectUserNumbers();
        } finally {
            MybatisUtil.close(sqlSession);
        }
        log.info("Druid连接池初始化成功");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        chain.doFilter(req, resp);
    }
}
