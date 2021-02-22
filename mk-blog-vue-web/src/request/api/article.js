/**
 * article模块接口列表
 */
import axios from "../http"; // 导入http中创建的axios实例
import qs from "qs";

const article = {
    /**
     * 前台文章列表
     *
     * @param title 文章标题
     * @param pageSize 页长
     * @param pageNum 页码
     * @returns {Promise<AxiosResponse<T>>}
     */
    articleList(title, pageSize, pageNum) {
        return axios.get(`api/article/getarticlelist`, {
            params: {
                title: title,
                pageSize: pageSize,
                pageNum: pageNum
            }
        });
    },

    /**
     * 后台文章列表
     *
     * @param title 文章标题
     * @param pageSize 页长
     * @param isDeleted 是否删除
     * @param pageNum 页码
     * @returns {Promise<AxiosResponse<T>>}
     */
    articleAdminList(title, isDeleted, pageSize, pageNum) {
        if (title !== null && title !== '') {
            title = "/" + title
        }
        return axios.get(`api/article/get-article-list${title}/${isDeleted}/${pageNum}/${pageSize}`);
    },

    /**
     * 通过id获取文章
     *
     * @param {*} id 文章id
     * @returns
     */
    getArticleById(id) {
        return axios.get(`api/article/get-article-details/${id}`);
    },

    /**
     * 新增文章
     *
     * @param {*} param 文章实体
     * @returns
     */
    doAddArticle(param) {
        return axios.post(`api/article/authorization/do-add-article`, qs.parse(param));
    },

    /**
     * 编辑文章
     *
     * @param {*} param 文章实体
     * @returns
     */
    doEditArticle(param) {
        return axios.put(`api/article/authorization/do-edit-article`, qs.parse(param));
    },

    /**
     * 删除文章
     *
     * @param {*} id 文章id
     * @returns
     */
    doDelArticle(id) {
        return axios.delete(`api/article/authorization/do-del-article/${id}`);
    },

    /**
     * 发布文章
     * @param id
     * @returns {Promise<AxiosResponse<T>>}
     */
    doReleaseArticle(id) {
        return axios.put(`api/article/authorization/do-release-article/${id}`);
    },

    /**
     * 恢复
     * @param id
     * @returns {Promise<AxiosResponse<T>>}
     */
    doRecoveryArticle(id) {
        return axios.put(`api/article/authorization/do-recovery-article/${id}`);
    },

    /**
     * 获取时光轴数据
     * @returns {Promise<AxiosResponse<T>>}
     */
    getTimeAis() {
        return axios.get(`api/article/get-time-ais`);
    }
};

export default article;
