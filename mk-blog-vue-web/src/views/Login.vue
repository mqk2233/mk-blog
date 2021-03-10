<template>
  <div>
    <div id="form">
      <Card title="MK BLOG">
        <div @keypress.enter="doLogin">
          <Form :model="loginForm" :rules="loginRule" ref="loginForm">
            <FormItem prop="username">
              <label>
                <Input
                    clearable
                    placeholder="用户名"
                    type="text"
                    v-model="loginForm.username"
                >
                  <Icon slot="prepend" type="ios-person-outline"/>
                </Input>
              </label>
            </FormItem>
            <FormItem prop="password">
              <label>
                <Input
                    clearable
                    placeholder="密码"
                    type="password"
                    v-model="loginForm.password"
                >
                  <Icon slot="prepend" type="ios-lock-outline"/>
                </Input>
              </label>
            </FormItem>
            <FormItem prop="verifyCode">
              <Row>
                <i-col span="17">
                  <label>
                    <Input
                        :maxlength="4"
                        clearable
                        placeholder="验证码"
                        type="text"
                        v-model="loginForm.verifyCode"
                    />
                  </label>
                </i-col>
                <i-col span="7">
                  <img
                      :src="loginForm.verifyCodeImg"
                      @click="changeCodeImg()"
                      alt="更换验证码"
                      title="点击更换验证码"
                  />
                </i-col>
              </Row>
            </FormItem>
            <FormItem>
              <Button
                  @click="doLogin"
                  @keyup.enter.native="doLogin()"
                  long
                  type="primary"
              >登 录
              </Button
              >
            </FormItem>
          </Form>
        </div>
      </Card>
    </div>
    <app-footer/>
    <vue-canvas-nest
        :config="{ opacity: '1', count: 120 }"
        style="background-color: #e6e6e6"
    />
    <app-live/>
  </div>
</template>
<script>
import Footer from "../components/Footer.vue";
import Live from "../components/Live2d.vue";
import vueCanvasNest from "vue-canvas-nest";
import {LOGIN} from "@/mutation/mutation-types";
import {Notice} from "view-design";

export default {
  name: "login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        verifyCode: "",
        verifyCodeImg: "/api/user/get-verification-code"
      },
      loginRule: {
        username: [
          {required: true, message: "请输入用户名", trigger: "blur"},
          {type: "string", min: 2, max: 10, message: "用户名长度为2-10位"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"},
          {type: "string", min: 6, max: 18, message: "密码长度为6-18位"}
        ],
        verifyCode: [
          {required: true, message: "请输入验证码", trigger: "blur"}
        ]
      }
    };
  },
  methods: {
    doLogin() {
      const data = {
        userName: this.loginForm.username,
        passWord: this.loginForm.password,
        verificationCode: this.loginForm.verifyCode
      };
      this.$api.user
          .login(data)
          .then(res => {
            this.$store.commit(LOGIN, res.data.data);
            Notice.success({title: "登录成功"});
            this.$router.push({name: "main"});
          })
          .catch(err => {
            Notice.warning({title: err.data.msg});
          });
    },
    changeCodeImg() {
      this.loginForm.verifyCode = "";
      let num = Math.ceil(Math.random() * 10); //生成一个随机数（防止缓存）
      this.loginForm.verifyCodeImg = "/api/user/get-verification-code?" + num;
    }
  },
  components: {
    "app-footer": Footer,
    vueCanvasNest,
    "app-live": Live
  }
};
</script>
<style scoped>
#form {
  width: 380px;
  align-content: center !important;
  position: absolute !important;
  left: 40%;
  top: 20%;
  background-color: #cad0d7;
}

img {
  margin-left: 18px;
  cursor: pointer;
}
</style>
