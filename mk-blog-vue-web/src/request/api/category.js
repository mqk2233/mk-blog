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
        return axios.get(`api/category/get-category-list`);
    },

    /**
     * 通过id获取文章
     *
     * @param {*} id 文章id
     * @returns
     */
    getCategoryById(id) {
        return axios.get(`api/article/authorization/get-category-details/${id}`);
    },

    /**
     * 新增分类
     *
     * @param {*} param 分类实体
     * @returns
     */
    doAddCategory(param) {
        return axios.post(`api/category/authorization/do-add-category`, qs.parse(param));
    },

    /**
     * 编辑分类
     *
     * @param {*} param 分类实体
     * @returns
     */
    doEditCategory(param) {
        return axios.put(`api/category/authorization/do-edit-category`, qs.parse(param));
    },

    /**
     * 删除分类
     *
     * @param {*} id 分类id
     * @returns
     */
    doDelCategory(id) {
        return axios.delete(`api/category/authorization/do-del-category/${id}`);
    }
};

export default category;
