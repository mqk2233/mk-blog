/**
 * api接口的统一出口
 */
import user from "./api/user";
import label from "./api/label";
import img from "./api/img";
import category from "./api/category";
import article from "./api/article";

export default {
  user,
  label,
  img,
  category,
  article
};
