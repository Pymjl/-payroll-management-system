package cuit.pymjl.core.service.user.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.constant.MailEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.user.UserMapper;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.MybatisUtil;
import cuit.pymjl.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/5/25 16:47
 **/
@SuppressWarnings("all")
@Slf4j
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    /**
     * 用户映射器
     */
    private static final UserMapper userMapper = MybatisUtil.openSession().getMapper(UserMapper.class);

    /**
     * 验证码过期时间,单位秒
     */
    private static final long EXPIRATION = 300;

    /**
     * 验证码长度
     */
    private static final int VERIFY_CODE_LENGTH = 6;

    /**
     * 图片验证码复杂度，如果太大图片验证码会很多干扰元素
     */
    private static final int VERIFY_CODE_COMPLEXITY = 30;

    /**
     * 图片验证码宽度
     */
    private static final int VERIFY_CODE_WIDTH = 200;

    /**
     * 图片验证码高度
     */
    private static final int VERIFY_CODE_HEIGHT = 100;

    @Override
    public CircleCaptcha getImageVerifyCode(String key) {
        log.info("开始生成验证码.........");
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(VERIFY_CODE_WIDTH, VERIFY_CODE_HEIGHT,
                VERIFY_CODE_LENGTH, VERIFY_CODE_COMPLEXITY);
        //获取图片验证码的文本串
        String code = captcha.getCode();
        //将文本串放入redis,uid作为Key,code作为value
        JedisUtils.set(key, code, EXPIRATION);
        log.info("验证码生成成功");
        return captcha;
    }

    @Override
    public void getEmailCode(String username, String key, String code) {
        if (!Validator.isEmail(username)) {
            throw new AppException("用户名格式错误，用户名应为邮箱");
        }
        String imageCode = (String) JedisUtils.get(key);
        JedisUtils.del(key);
        if (StrUtil.isBlank(imageCode) || !imageCode.equals(code)) {
            throw new AppException("图片验证码错误,请稍后重试");
        }
        log.info("开始生成验证码.......");
        String emailCode = getRandomString(VERIFY_CODE_LENGTH);
        log.info("验证码生成成功==>[{}]", code);
        MailUtil.send(username, MailEnum.MAIL_SUBJECT_VERIFY_CODE.getMessage(),
                MailEnum.getVerifyMailMessage(emailCode), false);
        JedisUtils.set(username, code, EXPIRATION);
    }

    /**
     * 得到随机字符串
     *
     * @param length 长度
     * @return {@code String}
     */
    @SuppressWarnings("all")
    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}