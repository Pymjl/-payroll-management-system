package cuit.pymjl.core.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.mapper.UserMapper;
import cuit.pymjl.core.service.UserService;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.MybatisUtil;
import cuit.pymjl.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;

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
    public Boolean register(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        user.setPassword(PasswordUtils.encrypt(user.getPassword()));
        user.setIdentity(IdentityEnum.USER.getIdentity());
        int i = userMapper.addUser(user);
        return i == 1;
    }

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
}
