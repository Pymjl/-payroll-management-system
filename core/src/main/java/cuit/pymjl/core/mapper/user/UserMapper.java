package cuit.pymjl.core.mapper.user;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 17:01
 **/
public interface UserMapper {
    /**
     * 查询用户
     * 查询用户列表
     *
     * @param pageNum  页面num
     * @param pageSize 页面大小
     * @return {@code List<User>}
     */
    Page<User> queryUsers(@Param("pageNum") int pageNum,
                          @Param("pageSize") int pageSize);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return int
     */
    int addUser(User user);

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return {@code User}
     */
    User queryOne(@Param("username") String username,
                  @Param("password") String password);

    /**
     * 通过ID查询用户
     *
     * @param userId 用户id
     * @return {@code User}
     */
    User queryOneById(@Param("userId") Long userId);

    /**
     * 查询用户数量
     *
     * @return {@code Integer}
     */
    Integer selectUserNumbers();
}
