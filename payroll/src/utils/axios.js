import axios from "axios";

/**
 * @type {import('axios').AxiosInstance}
 */

const Service = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000,
  // headers: {
  //   "Content-Type": "application/json",
  // },
});

// 当前所有请求
const currentReq = new Map();

/**
 * 添加当前请求
 * @param {import('axios').AxiosRequestConfig} config
 */
function addReq(config) {
  const reqToken = [
    config.method,
    config.url,
    JSON.stringify(config.params),
    JSON.stringify(config.data),
  ].join("&");
  config.cancelToken =
    config.cancelToken ||
    new axios.CancelToken((cancel) => {
      if (!currentReq.has(reqToken)) currentReq.set(reqToken, cancel);
    });
}

/**
 * 删除当前重复的请求
 * @param {import('axios').AxiosRequestConfig} config
 */
function delReq(config) {
  const reqToken = [
    config.method,
    config.url,
    JSON.stringify(config.params),
    JSON.stringify(config.data),
  ].join("&");
  if (currentReq.has(reqToken)) {
    const cancel = currentReq.get(reqToken);
    cancel(reqToken);
    currentReq.delete(reqToken);
  }
}
// 请求拦截器
Service.interceptors.request.use(
  (config) => {
    delReq(config);
    addReq(config);
    config.headers.Authorization = `Bearer ${localStorage.getItem("token")}`;
    return config;
  },
  (err) => Promise.reject(err)
);

Service.interceptors.response.use(
  (response) => {
    delReq(response);
    return response;
  },
  (err) => Promise.reject(err)
);

/**
 * 请求构造工厂
 * @param {import('axios').AxiosRequestConfig} config 请求配置参数
 * @returns {Promise<{succeed: boolean, res: any}>}
 */
export const ARFactory = async (config) => {
  const {
    data: { succeed = false, ...res },
  } = await Service(config);
  return { succeed, res };
};
