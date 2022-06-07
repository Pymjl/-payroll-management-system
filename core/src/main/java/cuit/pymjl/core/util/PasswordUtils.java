package cuit.pymjl.core.util;

import cn.hutool.crypto.SmUtil;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/15 11:49
 **/
public class PasswordUtils {
    /**
     * 第二次加密的盐
     * 盐,给密码加盐，进行哈希散列加密，加密后的密码不可逆
     */
    private static final String SALT = "%$Pymjl*&123^CUIT.epoch.pymjl*&^%$$";

    /**
     * 默认盐
     * 第一次加密
     */
    private static final String DEFAULT_SALT = ")yXnWDA+@APdA)yH7i*4*R#I#w$r+bA^F3a%8TU8_&@WN*Xk_j!d(aQVZCWf~sCR";

    /**
     * 加密
     *
     * @param password 密码
     * @return {@code String}
     */
    public static String encrypt(String password) {
        return encrypt(encrypt(password, SALT), DEFAULT_SALT);
    }

    /**
     * 匹配
     *
     * @param password          密码
     * @param encryptedPassword 加密密码
     * @return {@code Boolean}
     */
    public static Boolean match(String password, String encryptedPassword) {
        return match(password, encryptedPassword, SALT, DEFAULT_SALT);
    }

    /**
     * 匹配
     *
     * @param password          密码
     * @param encryptedPassword 加密密码
     * @param salt              盐
     * @return {@code Boolean}
     */
    private static Boolean match(String password, String encryptedPassword, String salt, String defaultSalt) {
        //密码不能为空
        if (null == password || "".equals(password)) {
            throw new RuntimeException("密码为空");
        }
        return encryptedPassword.equals(encrypt(encrypt(password, salt), defaultSalt));
    }

    /**
     * 加密
     *
     * @param password 密码
     * @param salt     盐
     * @return {@code String}
     */
    private static String encrypt(String password, String salt) {
        return SmUtil.sm3(salt + password);
    }
}
