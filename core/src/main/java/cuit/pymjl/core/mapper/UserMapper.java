package cuit.pymjl.core.mapper;

import com.github.pagehelper.Page;
import cuit.pymjl.core.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 17:01
 **/
@Mapper
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
}
