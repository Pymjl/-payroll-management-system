package cuit.pymjl.core.constant;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/27 19:48
 **/
public enum Avatar {
    /**
     * 默认头像
     */
    DEFAULT("https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg");
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    Avatar() {
    }

    Avatar(String path) {
        this.path = path;
    }
}
