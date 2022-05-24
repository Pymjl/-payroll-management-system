package cuit.pymjl.core;

import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.util.MybatisUtil;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 16:44
 **/
public class TestDruid {
    public static void main(String[] args) {
        UserMapper mapper = MybatisUtil.openSession().getMapper(UserMapper.class);
        mapper.queryUsers().forEach(System.out::println);
    }
}
