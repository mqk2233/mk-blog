/**
 * label模块接口列表
 */
import axios from "../http"; // 导入http中创建的axios实例
import qs from "qs";

const label = {
    /**
     * 标签列表
     *
     * @param labelName 标签名
     * @param pageSize 页长
     * @param pageNum 页码
     * @returns {Promise<AxiosResponse<T>>}
     */
    labelPageList(labelName, pageSize, pageNum) {
        if (labelName !== null && labelName !== '') {
            labelName = "/" + labelName
        }
        return axios.get(`api/label/authorization/get-label-page-list${labelName}/${pageNum}/${pageSize}`);
    },

    /**
     * 标签列表-无分页
     * @returns {Promise<AxiosResponse<T>>}
     */
    labelAllList() {
        return axios.get(`api/label/get-label-list`);
    },

    /**
     * 新增标签
     *
     * @param {*} param 标签实体
     * @returns
     */
    doAddLabel(param) {
        return axios.post(`api/label/authorization/do-add-label`, qs.parse(param));
    },

    /**
     * 编辑标签
     *
     * @param {*} param 标签实体
     * @returns
     */
    doEditLabel(param) {
        return axios.put(`api/label/authorization/do-edit-label`, qs.parse(param));
    },

    /**
     * 删除标签
     *
     * @param {*} id 标签id
     * @returns
     */
    doDelLabel(id) {
        return axios.delete(`api/label/authorization/do-del-label/${id}`);
    }
};

export default label;
