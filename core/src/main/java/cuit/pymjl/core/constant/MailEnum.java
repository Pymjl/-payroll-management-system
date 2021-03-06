package cuit.pymjl.core.constant;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/26 20:04
 **/
public enum MailEnum {
    /**
     * 主题：重置密码，用于重置密码时的邮件主题
     */
    MAIL_SUBJECT_RESET_PASSWORD("重置 密码"),
    MAIL_SUBJECT_VERIFY_CODE("邮箱验证码"),
    MAIL_MESSAGE_VERIFY_CODE_PREFIX("亲爱的用户您好:\n\t您的邮箱验证码是："),
    MAIL_MESSAGE_VERIFY_CODE_SUFFIX("有效期为五分钟,请及时使用!"),
    MAIL_MESSAGE_RESET_PASSWORD_PREFIX("亲爱的用户您好:\n\t您的新密码是："),
    MAIL_MESSAGE_RESET_PASSWORD_SUFFIX("请牢记您的密码，稍后您可以在用户界面更改您的密码。"),
    ;
    private String message;

    MailEnum() {
    }

    MailEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 得到验证邮件消息
     *
     * @param verifyCode 验证代码
     * @return {@code String}
     */
    public static String getVerifyMailMessage(String verifyCode) {
        return MAIL_MESSAGE_VERIFY_CODE_PREFIX.getMessage()
                + "[" + verifyCode + "] "
                + MAIL_MESSAGE_VERIFY_CODE_SUFFIX.getMessage();
    }

    /**
     * 得到重置密码的邮件信息
     *
     * @param password 密码
     * @return {@code String}
     */
    public static String getRestPasswordMessage(String password) {
        return MAIL_MESSAGE_RESET_PASSWORD_PREFIX.getMessage()
                + "[" + password + "] "
                + MAIL_MESSAGE_RESET_PASSWORD_SUFFIX.getMessage();

    }
}
