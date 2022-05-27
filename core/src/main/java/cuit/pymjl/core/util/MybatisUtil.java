package cuit.pymjl.core.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/23 0:22
 **/
public class MybatisUtil {

    private static SqlSessionFactory factory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2.创建SqlSessionFactoryBuilder对像
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3.创建SqlSessionFactory对像
            factory = builder.build(inputStream);
            //释放builder
            builder = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取session对像
     *
     * @return
     */
    public static SqlSession openSession() {
        return factory.openSession(true);
    }

    /**
     * 关闭
     *
     * @param session 会话
     */
    public static void close(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}

