package cuit.pymjl.core;

import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.result.R;
import cuit.pymjl.core.util.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 20:43
 **/
public class JsonTest {
    @Test
    void testJson() {
        User user = new User();
        user.setAge(20);
        user.setNickname("pymjl");
        user.setPassword("2345");
        System.out.println(JsonUtils.toString(user));
    }

    @Test
    void testResult() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("pymhl", new ArrayList<>());
        map.put("pym", 21312312);
        map.put("phl", 21312312);
        map.put("mhl", 21312312);
        map.put("pmhl", 21312312);
        list.add(map);
        System.out.println(R.success(list));
    }
}
