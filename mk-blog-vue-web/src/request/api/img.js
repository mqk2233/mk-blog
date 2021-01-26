import { AxiosInstance as axios } from "axios";

const img = {
  headers: {
    "Content-Type": "multipart/form-data",
    Authorization: "8aAqaMXADeo686IO4skAHAthmHkYVClL"
  },
  uploadImg(param) {
    return axios.post(`/imgApi`, param);
  }
};
export default img;
