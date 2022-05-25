package cuit.pymjl.core.constant;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:53
 **/
public enum IdentityEnum {
    /**
     * 用户
     */
    USER(0),
    /**
     * 管理
     */
    ADMIN(1),
    /**
     * 封禁的用户
     */
    BANNED_USER(-1);
    private Integer identity;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    IdentityEnum(Integer identity) {
        this.identity = identity;
    }
}
