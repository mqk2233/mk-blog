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
   * @param pageNum 页码
   * @returns {Promise<AxiosResponse<T>>}
   */
  articleAdminList(title, pageSize, pageNum) {
    if (title !== null && title !== '') {
      title = "/" + title
    }
    return axios.get(`api/article/authorization/get-article-list${title}/${pageNum}/${pageSize}`);
  },

  /**
   * 通过id获取文章
   *
   * @param {*} id 文章id
   * @returns
   */
  getArticleById(id) {
    return axios.get(`api/article/getarticlebyid`, { params: id });
  },

  /**
   * 新增文章
   *
   * @param {*} param 文章实体
   * @returns
   */
  doAddArticle(param) {
    return axios.post(`api/article/doaddarticle`, qs.parse(param));
  },

  /**
   * 编辑文章
   *
   * @param {*} param 文章实体
   * @returns
   */
  doEditArticle(param) {
    return axios.post(`api/article/doeditarticle`, qs.parse(param));
  },

  /**
   * 删除文章
   *
   * @param {*} id 文章id
   * @returns
   */
  doDelArticle(id) {
    return axios.get(`api/article/dodelarticle`, { params: id });
  },

  /**
   * 发布文章
   * @param id
   * @returns {Promise<AxiosResponse<T>>}
   */
  doReleaseArticle(id) {
    return axios.get(`api/article/doreleasearticle`, { params: id });
  },

  /**
   * 获取时光轴数据
   * @returns {Promise<AxiosResponse<T>>}
   */
  getTimeAis() {
    return axios.get(`api/article/gettimeais`);
  }
};

export default article;
