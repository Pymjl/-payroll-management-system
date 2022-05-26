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
    public static <T> String success(T data, String message) {
        return JsonUtils.toString(new CommonResult(data, true, message));
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return {@code CommonResult}
     */
    public static <T> String success(T data) {
        return JsonUtils.toString(new CommonResult(data, true, "成功"));
    }

    /**
     * 成功
     *
     * @return {@code CommonResult}
     */
    public static String success() {
        return JsonUtils.toString(new CommonResult(true, "成功"));
    }

    /**
     * 失败
     *
     * @param message 消息
     * @return {@code CommonResult}
     */
    public static String fail(String message) {
        return JsonUtils.toString(new CommonResult(false, message));
    }

    /**
     * 失败
     *
     * @return {@code CommonResult}
     */
    public static String fail() {
        return JsonUtils.toString(new CommonResult(false, "失败"));
    }
}
