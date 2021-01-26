<template>
  <div>
    <Card style="margin-bottom: 0.5em;">
      <i-form :label-width="60" inline>
        <FormItem label="用户名" style="margin: auto;">
          <label>
            <i-input clearable placeholder="用户名" v-model="searchUserName" />
          </label>
        </FormItem>
        <FormItem style="float: right;">
          <Button
            @click="openModel('', false)"
            icon="md-create"
            style="margin-right: 1em"
            type="success"
            >新增
          </Button>
          <Button @click="getUserList" icon="ios-search" type="info"
            >搜索
          </Button>
        </FormItem>
      </i-form>
    </Card>
    <Card>
      <Table
        :columns="column"
        :data="list"
        :loading="loading"
        border
        no-data-text="没有数据呀 ( ๑ŏ ﹏ ŏ๑ )"
        stripe
        tooltip
      >
        <template slot="name" slot-scope="{ row }">
          <strong>{{ row.name }}</strong>
        </template>
        <template slot="action" slot-scope="{ row }">
          <Button
            @click="openModel(row.id, true)"
            size="small"
            style="margin-right: 5px"
            type="info"
            >编辑
          </Button>
          <Button @click="doDelUser(row.id)" size="small" type="error"
            >删除
          </Button>
        </template>
      </Table>
      <template>
        <Modal
          :loading:="true"
          :title="editUserTitle"
          @on-ok="doEditUser('userEdit')"
          v-model="isEditUser"
        >
          <i-form :model="userEdit" :rules="ruleCustom" ref="userEdit">
            <FormItem label="用户名" prop="userName">
              <label>
                <i-input placeholder="用户名" v-model="userEdit.userName" />
              </label>
            </FormItem>
            <FormItem label="密码" prop="passWord">
              <label>
                <i-input
                  placeholder="密码"
                  type="password"
                  v-model="userEdit.passWord"
                />
              </label>
            </FormItem>
            <FormItem label="头像" prop="imgHead">
              <Row>
                <i-col span="3">
                  <Avatar :src="userEdit.imgHead" />
                </i-col>
                <i-col span="2">
                  <Upload
                    :format="['jpg', 'jpeg', 'png']"
                    :on-success="modifyImgHead"
                    :show-upload-list="false"
                    :headers="imgHeader"
                    action="/imgApi"
                    name="smfile"
                  >
                    <Button icon="ios-cloud-upload-outline">上传</Button>
                  </Upload>
                </i-col>
              </Row>
            </FormItem>
            <FormItem label="是否启用">
              <i-switch
                :false-value="0"
                size="large"
                :true-value="1"
                v-model="userEdit.prohibit"
              >
                <span slot="open">启用</span>
                <span slot="close">禁用</span>
              </i-switch>
            </FormItem>
            <FormItem label="备注">
              <label>
                <i-input
                  :autosize="{ minRows: 2, maxRows: 5 }"
                  placeholder="备注"
                  type="textarea"
                  v-model="userEdit.remarks"
                />
              </label>
            </FormItem>
          </i-form>
        </Modal>
      </template>
    </Card>
    <Page
      :current="currentPage"
      :page-size="pageSize"
      :total="total"
      @on-change="pageChange"
      @on-page-size-change="pageSizeChange"
      show-sizer
      show-total
      style="float: right; margin-top: 1em;"
      transfer
    />
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: [], //用户数组
      listId: "",
      pageSize: 10, //每页显示多少条
      total: 0, //总条数
      currentPage: 1, //当前页
      isEditUser: false, //打开对话框标识
      editUserFlag: false, //标识是否编辑用户，false新增用户
      editUserTitle: "编辑用户", //对话框标题
      searchUserName: "", //搜索框用户名
      loading: true,
      column: [
        {
          type: "index",
          title: "序号",
          width: 90,
          align: "center",
          // 用来修改序号的iView内置方法
          indexMethod: row => {
            // row._index打印出了0到9，通过数学计算可以算出每一个对应的序号，再返回出对应的序号
            return row._index + 1;
          }
        },
        {
          align: "center",
          title: "用户名",
          key: "userName"
        },
        {
          align: "center",
          title: "头像",
          key: "imgHead",
          render: (h, params) => {
            return h("img", {
              domProps: {
                src: params.row.imgHead
              },
              style: {
                display: "block",
                width: "30px",
                height: "30px",
                borderRadius: "3px",
                margin: "auto "
              }
            });
          }
        },
        {
          align: "center",
          title: "是否启用",
          key: "prohibit",
          render: (h, params) => {
            return h(
              "Tag",
              {
                props: {
                  color: params.row.prohibit === 1 ? "success" : "error"
                }
              },
              params.row.prohibit === 1 ? "是" : "否"
            );
          }
        },
        {
          align: "center",
          title: "备注",
          key: "remarks"
        },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center"
        }
      ],
      userEdit: {
        //用户对象
        id: 0,
        userName: "",
        passWord: "",
        remarks: "",
        imgHead: "https://i.loli.net/2019/12/08/RYdDPGyZnEq7k8m.png",
        prohibit: 1
      },
      ruleCustom: {
        userName: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          {
            pattern: /\w{2,10}/,
            message: "请输入2-10位字母或数字",
            trigger: "blur"
          }
        ],
        passWord: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          {
            pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/,
            message: "请输入6-16位字母和数字混合的密码",
            trigger: "blur"
          }
        ],
        remarks: [{ max: 250, trigger: "blur", message: "最大长度是250个字符" }]
      },
      imgHeader: this.$api.img.headers
    };
  },
  methods: {
    // 用户列表
    getUserList: function() {
      this.$api.user
        .userList(this.searchUserName, this.pageSize, this.currentPage)
        .then(res => {
          this.list = res.data.data;
          this.total = res.data.total;
          this.loading = false;
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    // 打开对话框
    openModel(id, flag) {
      this.isEditUser = true;
      if (flag) {
        this.editUserTitle = "编辑用户";
        this.editUserFlag = true;
        this.$api.user
          .getUserById({ id: id })
          .then(res => {
            this.userEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({ title: err.data.msg });
          });
      } else {
        this.editUserFlag = false;
        this.userEdit.id = "";
        this.userEdit.userName = "";
        this.userEdit.passWord = "";
        this.userEdit.remarks = "";
        this.userEdit.prohibit = 1;
        this.userEdit.imgHead =
          "https://i.loli.net/2019/12/08/RYdDPGyZnEq7k8m.png";
        this.editUserTitle = "新增用户";
      }
    },
    // 编辑用户
    doEditUser(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          const param = this.userEdit;
          if (this.editUserFlag) {
            this.$api.user
              .doEditUser(param)
              .then(res => {
                this.$Notice.success({ title: res.data.msg });
                this.getUserList();
              })
              .catch(err => {
                this.$Notice.warning({ title: err.data.msg });
              });
          } else {
            this.$api.user
              .doAddUser(param)
              .then(res => {
                this.$Notice.success({ title: res.data.msg });
                this.getUserList();
              })
              .catch(err => {
                this.$Notice.warning({ title: err.data.msg });
              });
          }
        } else {
          this.$Notice.warning({ title: "操作失败" });
        }
      });
    },
    // 删除用户
    doDelUser(id) {
      this.$api.user
        .doDelUser({ id: id })
        .then(res => {
          this.$Notice.success({ title: res.data.msg });
          this.getUserList();
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    //改变页码
    pageChange(index) {
      this.currentPage = index;
      this.getUserList();
    },
    //改变页数
    pageSizeChange(size) {
      this.pageSize = size;
      this.getUserList();
    },
    //头像
    modifyImgHead(response) {
      this.userEdit.imgHead = response.data.url;
    }
  },
  created: function() {
    this.getUserList();
  }
};
</script>
