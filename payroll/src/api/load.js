import { ARFactory } from "../utils/axios";

/**
 * @description: 请求邮箱验证码
 * @param {String} username
 * @param {String} key 与图片验证码的key一致
 * @param {String} code
 * @returns
 */
export function getEmailCode(username, key, code) {
  return ARFactory({
    url: "/code/email",
    method: "get",
    params: { username, key, code },
  });
}

/**
 * @description: 注册用户
 * @param {Object} data 注册信息
 * @returns
 */
export function register(data) {
  return ARFactory({
    url: "/register",
    method: "post",
    data,
  });
}

/**
 * @description: 登录用户
 * @param {String} username
 * @param {String} password
 * @param {String} code
 * @returns
 */
export function login(username, password, code) {
  return ARFactory({
    url: "/login",
    method: "post",
    data: { username, password, code },
  });
}

/**
 * @description 根据token获取当前的用户信息
 * @returns
 */
export function getUserInfo() {
  return ARFactory({
    url: "/userInfo",
    method: "get",
  });
}

export function logout() {
  return ARFactory({
    url: "/logout",
    method: "post",
  });
}
