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
  labelList(labelName, pageSize, pageNum) {
    return axios.get(`api/label/getlabellist`, {
      params: {
        labelName: labelName,
        pageSize: pageSize,
        pageNum: pageNum
      }
    });
  },

  /**
   * 标签列表-无分页
   * @returns {Promise<AxiosResponse<T>>}
   */
  labelAllList() {
    return axios.get(`api/label/getlabelalllist`);
  },

  /**
   * 通过id获取标签
   *
   * @param {*} id 标签id
   * @returns
   */
  getLabelById(id) {
    return axios.get(`api/label/getlabelbyid`, { params: id });
  },

  /**
   * 新增标签
   *
   * @param {*} param 标签实体
   * @returns
   */
  doAddLabel(param) {
    return axios.post(`api/label/doaddlabel`, qs.parse(param));
  },

  /**
   * 编辑标签
   *
   * @param {*} param 标签实体
   * @returns
   */
  doEditLabel(param) {
    return axios.post(`api/label/doeditlabel`, qs.parse(param));
  },

  /**
   * 删除标签
   *
   * @param {*} id 标签id
   * @returns
   */
  doDelLabel(id) {
    return axios.get(`api/label/dodellabel`, { params: id });
  }
};

export default label;
