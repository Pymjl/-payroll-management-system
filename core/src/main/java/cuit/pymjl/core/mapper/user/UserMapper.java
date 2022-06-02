package cuit.pymjl.core.mapper.user;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
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

    /**
     * 更新密码
     *
     * @param username 用户名
     * @param password 新密码
     * @return int
     */
    int updatePassword(@Param("username") String username,
                       @Param("password") String password);

    /**
     * 更新密码
     *
     * @param userId   用户id
     * @param password 密码
     * @return int
     */
    int updatePasswordById(@Param("userId") Long userId,
                           @Param("password") String password);

    /**
     * 冻结用户
     * FIXME 恢复和冻结应该是一个接口的，设计缺陷，但是我不想改了，害
     *
     * @param userId 用户id
     * @return int
     */
    int banUser(@Param("userId") Long userId);

    /**
     * 恢复用户
     * FIXME 恢复和冻结应该是一个接口的，设计缺陷，但是我不想改了，害
     *
     * @param userId 用户id
     * @return int
     */
    int recoverUserIdentity(@Param("userId") Long userId);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @param id       id
     * @return int
     */
    int updateUserInfo(@Param("userInfo") UserDTO userInfo, @Param("id") Long id);

    /**
     * 通过id删除用户
     *
     * @param id id
     * @return int
     */
    int delById(@Param("id") Long id);

}
