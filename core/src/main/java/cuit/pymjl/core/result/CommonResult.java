package cuit.pymjl.core.result;

/**
 * 共同结果
 *
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 20:50
 **/
public class CommonResult {
    /**
     * 数据
     */
    private String data;

    /**
     * 状态
     */
    private Boolean succeed;

    /**
     * 信息
     */
    private String message;

    public CommonResult() {
    }

    public CommonResult(String data, Boolean succeed, String message) {
        this.data = data;
        this.succeed = succeed;
        this.message = message;
    }

    public CommonResult(Boolean succeed, String message) {
        this.data = null;
        this.succeed = succeed;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "data='" + data + '\'' +
                ", succeed=" + succeed +
                ", message='" + message + '\'' +
                '}';
    }
}
