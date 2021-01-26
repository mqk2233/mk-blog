/**
 * category模块接口列表
 */
import axios from "../http"; // 导入http中创建的axios实例
import qs from "qs";

const category = {
  /**
   * 分类列表
   *
   * @returns {Promise<AxiosResponse<T>>}
   */
  categoryList() {
    return axios.get(`api/category/getcategorylist`);
  },

  /**
   * 通过id获取分类
   *
   * @param {*} id 分类id
   * @returns
   */
  getCategoryById(id) {
    return axios.get(`api/category/getcategorybyid`, { params: id });
  },

  /**
   * 新增分类
   *
   * @param {*} param 分类实体
   * @returns
   */
  doAddCategory(param) {
    return axios.post(`api/category/doaddcategory`, qs.parse(param));
  },

  /**
   * 编辑分类
   *
   * @param {*} param 分类实体
   * @returns
   */
  doEditCategory(param) {
    return axios.post(`api/category/doeditcategory`, qs.parse(param));
  },

  /**
   * 删除分类
   *
   * @param {*} id 分类id
   * @returns
   */
  doDelCategory(id) {
    return axios.get(`api/category/dodelcategory`, { params: id });
  }
};

export default category;
