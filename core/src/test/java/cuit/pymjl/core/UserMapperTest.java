package cuit.pymjl.core;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.result.PageResult;
import cuit.pymjl.core.util.JsonUtils;
import cuit.pymjl.core.util.MybatisUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 22:37
 **/
public class UserMapperTest {
    private static final UserMapper mapper = MybatisUtil.openSession().getMapper(UserMapper.class);

    @Test
    void testInsert() {
        for (int i = 7; i < 27; i++) {
            User user = new User();
            user.setPassword("123456");
            user.setAge(20);
            user.setNickname("test" + i);
            user.setUsername("test" + i);
            user.setGender(1);
            mapper.addUser(user);
        }
    }

    @Test
    void testPage() {
        Page<User> users = mapper.queryUsers(1, 5);
        System.out.println(JsonUtils.toString(new PageResult<>(users)));
    }
}
