package cuit.pymjl.core.service.user.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import com.github.pagehelper.Page;
import cuit.pymjl.core.constant.Avatar;
import cuit.pymjl.core.constant.IdentityEnum;
import cuit.pymjl.core.constant.MailEnum;
import cuit.pymjl.core.entity.user.User;
import cuit.pymjl.core.entity.user.dto.UserDTO;
import cuit.pymjl.core.entity.user.dto.UserInfoDTO;
import cuit.pymjl.core.exception.AppException;
import cuit.pymjl.core.mapper.user.UserMapper;
import cuit.pymjl.core.service.user.UserService;
import cuit.pymjl.core.util.JedisUtils;
import cuit.pymjl.core.util.JwtUtils;
import cuit.pymjl.core.util.MybatisUtil;
import cuit.pymjl.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
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

    /**
     * 重置后的密码默认长度
     */
    private static final int PASSWORD_LENGTH = 10;

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
        log.info("开始生成邮箱验证码.......");
        String emailCode = getRandomString(VERIFY_CODE_LENGTH);
        log.info("邮箱验证码生成成功==>[{}]", emailCode);
        MailUtil.send(username, MailEnum.MAIL_SUBJECT_VERIFY_CODE.getMessage(),
                MailEnum.getVerifyMailMessage(emailCode), false);
        JedisUtils.set(username, emailCode, EXPIRATION);
    }

    @Override
    public void register(UserDTO userDTO) {
        SqlSession sqlSession = null;
        try {
            log.info("开始验证邮箱验证码......");
            String code = (String) JedisUtils.get(userDTO.getUsername());
            JedisUtils.del(code);
            if (StrUtil.isBlank(code) || !code.equals(userDTO.getCode())) {
                throw new AppException("邮箱验证码错误");
            }
            log.info("邮箱验证码验证成功，开始注册用户.......");
            User user = BeanUtil.copyProperties(userDTO, User.class);
            user.setIdentity(IdentityEnum.USER.getIdentity());
            user.setPassword(PasswordUtils.encrypt(user.getPassword()));
            user.setAvatar(Avatar.DEFAULT.getPath());
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.addUser(user);
            log.info("注册成功");
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public String login(UserInfoDTO userInfoDTO) {
        log.info("开始验证邮箱验证码......");
        String code = (String) JedisUtils.get(userInfoDTO.getUsername());
        JedisUtils.del(userInfoDTO.getUsername());
        if (StrUtil.isBlank(code) || !code.equals(userInfoDTO.getCode())) {
            throw new AppException("邮箱验证码错误");
        }
        log.info("邮箱验证码验证成功，开始验证用户信息.......");
        UserMapper userMapper = MybatisUtil.openSession().getMapper(UserMapper.class);
        User user = userMapper.queryOne(userInfoDTO.getUsername(),
                PasswordUtils.encrypt(userInfoDTO.getPassword()));
        if (ObjectUtil.isNull(user)) {
            throw new AppException("用户名或密码错误，请重新输入");
        }
        log.info("用户信息验证成功，开始生成token");
        String token = JwtUtils.generateToken(user.getId(), user.getNickname());
        JedisUtils.set(String.valueOf(user.getId()), token, JwtUtils.getTokenExpiredTime());
        log.info("token生成成功");
        return token;
    }

    @Override
    public User queryUserById(Long userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.queryOneById(userId);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void logout(Long userId) {
        log.info("开始注销......");
        String token = (String) JedisUtils.get(String.valueOf(userId));
        if (StrUtil.isNotBlank(token)) {
            JedisUtils.del(userId.toString());
            log.info("注销成功");
        } else {
            throw new AppException("发生未知错误,token不存在");
        }
    }

    @Override
    public Page<User> queryUsers(Integer pageNum, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.queryUsers(pageNum, pageSize);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void resetPassword(String username, String code) {
        //验证用户名
        if (!Validator.isEmail(username)) {
            throw new AppException("用户名格式错误，用户名应该为邮箱");
        }
        //验证code
        String verifyCode = (String) JedisUtils.get(username);
        JedisUtils.del(username);
        if (verifyCode == null || !verifyCode.equals(code)) {
            throw new AppException("验证码错误");
        }
        log.info("邮箱验证码验证成功");
        SqlSession sqlSession = null;
        try {
            //重置密码
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            String newPassword = getRandomString(PASSWORD_LENGTH);
            String encrypt = PasswordUtils.encrypt(newPassword);
            int result = userMapper.updatePassword(username, encrypt);
            if (result != 1) {
                throw new AppException("该用户不存在");
            }
            log.info("密码重置成功，开始发送邮箱");
            //将新密码通过邮箱发送给用户
            MailUtil.send(username, MailEnum.MAIL_SUBJECT_RESET_PASSWORD.getMessage(),
                    MailEnum.getRestPasswordMessage(newPassword), false);
        } finally {
            MybatisUtil.close(sqlSession);
        }

    }

    @Override
    public void updatePassword(String password, Long userId) {
        log.info("开始更新密码......");
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int result = userMapper.updatePasswordById(userId, PasswordUtils.encrypt(password));
            if (result != 1) {
                throw new AppException("发生未知错误，用户不存在");
            }
            log.info("更新成功");
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void banUser(Long userId) {
        log.info("开始封禁用户......");
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.queryOneById(userId);
            if (ObjectUtil.isNull(user)) {
                throw new AppException("不存在该用户，封禁失败");
            } else if (user.getIdentity().equals(IdentityEnum.ADMIN.getIdentity())) {
                throw new AppException("权限不足，你不能封禁一个管理员");
            } else if (user.getIdentity().equals(IdentityEnum.BANNED_USER.getIdentity())) {
                throw new AppException("该用户已经被封禁");
            }
            int result = userMapper.banUser(userId);
            if (result != 1) {
                throw new AppException("发生未知错误,封禁失败");
            }
            log.info("封禁成功");
        } finally {
            MybatisUtil.close(sqlSession);
        }

    }

    @Override
    public void updateUserInfo(UserDTO userDTO, Long userId) {
        log.info("开始更新用户信息......");
        userDTO.setUpdateTime(new Date());
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int result = userMapper.updateUserInfo(userDTO, userId);
            if (result != 1) {
                throw new AppException("更新失败");
            }
            log.info("更新成功");
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void recoverUserIdentity(Long userId) {
        log.info("开始恢复用户......");
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.queryOneById(userId);
            if (ObjectUtil.isNull(user)) {
                throw new AppException("不存在该用户，恢复失败");
            } else if (user.getIdentity().equals(IdentityEnum.USER.getIdentity())) {
                throw new AppException("该用户状态本来就是正常的");
            }
            int result = userMapper.recoverUserIdentity(userId);
            if (result != 1) {
                throw new AppException("发生未知错误,封禁失败");
            }
            log.info("恢复成功");
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("开始删除用户......");
        SqlSession sqlSession = MybatisUtil.openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.delById(userId);
            if (result != 1) {
                throw new AppException("要删除的用户不存在或该用户是管理员，删除失败");
            }
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    /**
     * 得到随机字符串
     *
     * @param length 长度
     * @return {@code String}
     */
    @SuppressWarnings("all")
    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345678901234567890123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
