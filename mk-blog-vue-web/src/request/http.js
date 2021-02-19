/**
 * axios封装
 * 请求拦截、响应拦截、错误统一处理
 */
import axios from "axios";
import router from "../router";
import { Notice } from "view-design";

/**
 * 提示函数
 * 禁止点击蒙层、显示一秒后关闭
 */
const tip = msg => {
  Notice.error({ title: msg });
};

/**
 * 跳转登录页
 * 携带当前页面路由，以期在登录页面完成登录后返回当前页面
 */
const toLogin = () => {
  router
    .replace({
      path: "/login",
      query: {
        redirect: router.currentRoute.fullPath
      }
    })
    .catch(err => this.$Notice.warning({ title: err.toString() }));
};
/**
 * 错误响应
 * @param res
 * @param flag
 * @returns {Promise<never>}
 */
const responseError = (res, flag) => {
  switch (flag ? res.data.code : res.response.status) {
    case 401:
      tip("未登录，请先登录");
      toLogin();
      break;
    case 403:
      tip("非法请求");
      toLogin();
      break;
    default:
      break;
  }
  return Promise.reject(res);
};

/**
 * 创建axios实例
 * @type {AxiosInstance}
 */
const instance = axios.create({
  timeout: 1000 * 12,
  headers: { "Content-Type": "application/json;charset=UTF-8" }
});

/**
 * 请求拦截器
 * 每次请求前，如果存在token则在请求头中携带toke
 */
instance.interceptors.request.use(
  config => {
    const token = localStorage.getItem("token");
    if (token !== "" && token != null && config.url.indexOf("dologin") === -1) {
      config.headers.Authorization = token;
    }
    return config;
  },
  error => {
    tip("服务器出现错误，请联系管理员");
    Promise.reject(error);
  }
);

/**
 * 响应拦截器
 */
instance.interceptors.response.use(
  // 请求成功
  res => {
    const token = res.data.token;
    if (token !== "" && token != null) {
      localStorage.setItem("token", token);
    }
    return res.data.code === 200 ? res : responseError(res, true);
  },
  // 请求失败
  error => {
    if (
      error.response.status === 500 &&
      error.response.data.message === "登录过期，请重新登录"
    ) {
      localStorage.removeItem("token");
    } else {
      tip("服务器出现错误，请联系管理员");
      return Promise.reject(error);
    }
  }
);
export default instance;
