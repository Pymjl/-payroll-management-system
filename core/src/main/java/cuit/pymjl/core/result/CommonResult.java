package cuit.pymjl.core.result;

import java.io.Serializable;

/**
 * 共同结果
 *
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/24 20:50
 **/
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -10899714971680341L;

    /**
     * 数据
     */
    private T data;

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

    public CommonResult(T data, Boolean succeed, String message) {
        this.data = data;
        this.succeed = succeed;
        this.message = message;
    }

    public CommonResult(Boolean succeed, String message) {
        this.data = null;
        this.succeed = succeed;
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "data=" + data +
                ", succeed=" + succeed +
                ", message='" + message + '\'' +
                '}';
    }
}
