/**
 * user模块接口列表
 */
import axios from "../http"; // 导入http中创建的axios实例
import qs from "qs"; // 根据需求是否导入qs模块

const user = {
    /**
     * 用户列表
     *
     * @param {*} userName 用户名
     * @param {*} pageSize 页长
     * @param {*} pageNum 当前页
     * @returns
     */
    userList(userName, pageSize, pageNum) {
        return axios.get(`api/user/getuserlist`, {
            params: {
                userName: userName,
                pageSize: pageSize,
                pageNum: pageNum
            }
        });
    },

    /**
     * 通过id获取用户
     *
     * @param {*} id 用户id
     * @returns
     */
    getUserById(id) {
        return axios.get(`api/user/getuserbyid`, {params: id});
    },

    /**
     * 新增用户
     *
     * @param {*} param 用户实体
     * @returns
     */
    doAddUser(param) {
        return axios.post(`api/user/doadduser`, qs.parse(param));
    },

    /**
     * 编辑用户
     *
     * @param {*} param 用户实体
     * @returns
     */
    doEditUser(param) {
        return axios.post(`api/user/doedituser`, qs.parse(param));
    },

    /**
     * 删除用户
     *
     * @param {*} id 用户id
     * @returns
     */
    doDelUser(id) {
        return axios.get(`api/user/dodeluser`, {params: id});
    },

    /**
     * 获取当前用户
     *
     * @returns
     */
    getCurrentUser() {
        return axios.get(`api/user/authorization/get-current-user`);
    },

    /**
     * 登录
     *
     * @param {*} params 用户对象
     * @returns
     */
    login(params) {
        return axios.post(`api/user/do-login`, qs.parse(params));
    }
};

export default user;
