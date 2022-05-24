package cuit.pymjl.core.result;

import cuit.pymjl.core.util.JsonUtils;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 20:53
 **/
@SuppressWarnings("all")
public class R {
    /**
     * 成功
     *
     * @param data    数据
     * @param message 消息
     * @return {@code CommonResult}
     */
    public static <T> CommonResult success(T data, String message) {
        return new CommonResult(JsonUtils.toString(data), true, message);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return {@code CommonResult}
     */
    public static <T> CommonResult success(T data) {
        return new CommonResult(JsonUtils.toString(data), true, "成功");
    }

    /**
     * 成功
     *
     * @return {@code CommonResult}
     */
    public static CommonResult success() {
        return new CommonResult(true, "成功");
    }

    /**
     * 失败
     *
     * @param message 消息
     * @return {@code CommonResult}
     */
    public static CommonResult fail(String message) {
        return new CommonResult(false, message);
    }

    /**
     * 失败
     *
     * @return {@code CommonResult}
     */
    public static CommonResult fail() {
        return new CommonResult(false, "失败");
    }
}
