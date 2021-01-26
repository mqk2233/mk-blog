<template>
  <div>
    <Card style="margin-bottom: 0.5em;">
      <i-form :label-width="60" inline>
        <FormItem label="标签名" style="margin: auto;">
          <label>
            <i-input
                clearable
                placeholder="标签名"
                v-model="searchLabelName"
            />
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
          <Button @click="getLabelList" icon="ios-search" type="info"
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
          <Button @click="doDelLabel(row.id)" size="small" type="error"
            >删除
          </Button>
        </template>
      </Table>
      <template>
        <Modal
          :loading="true"
          :title="editLabelTitle"
          @on-ok="doEditLabel('labelEdit')"
          true
          v-model="isEditLabel"
        >
          <Form :model="labelEdit" :rules="ruleCustom" ref="labelEdit">
            <FormItem label="标签名" prop="labelName">
              <label>
                <Input placeholder="标签名" v-model="labelEdit.labelName" />
              </label>
            </FormItem>
            <FormItem label="排序标识" prop="sort">
              <label>
                <InputNumber placeholder="排序标识" v-model="labelEdit.sort" />
              </label>
            </FormItem>
            <FormItem label="是否启用">
              <i-switch
                :false-value="0"
                size="large"
                :true-value="1"
                v-model="labelEdit.prohibit"
              >
                <span slot="open">启用</span>
                <span slot="close">禁用</span>
              </i-switch>
            </FormItem>
          </Form>
        </Modal>
      </template>
    </Card>
    <Page
      :current="currentPage"
      :page-size="pageSize"
      :total="total"
      @on-change="pagechange"
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
      list: [], //标签数组
      listId: "",
      pageSize: 10, //每页显示多少条
      total: 0, //总条数
      currentPage: 1, //当前页
      isEditLabel: false, //打开对话框标识
      editLabelFlag: false, //标识是否编辑标签，false新增标签
      editLabelTitle: "编辑标签", //对话框标题
      searchLabelName: "", //搜索框标签名
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
          title: "标签名",
          key: "labelName"
        },
        {
          align: "center",
          title: "排序标识",
          key: "sort"
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
          title: "操作",
          slot: "action",
          width: 150,
          align: "center"
        }
      ],
      labelEdit: {
        // 标签对象
        id: 0,
        labelName: "",
        sort: 0,
        prohibit: 1
      },
      ruleCustom: {
        labelName: [
          { required: true, message: "标签名不能为空", trigger: "blur" },
          {
            pattern: /.{2,6}/,
            message: "请输入2-6位字符",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    // 标签列表
    getLabelList: function() {
      this.$api.label
        .labelList(this.searchLabelName, this.pageSize, this.currentPage)
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
      this.isEditLabel = true;
      if (flag) {
        this.editLabelTitle = "编辑标签";
        this.editLabelFlag = true;
        this.$api.label
          .getLabelById({ id: id })
          .then(res => {
            this.labelEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({ title: err.data.msg });
          });
      } else {
        this.editLabelFlag = false;
        this.labelEdit.id = "";
        this.labelEdit.labelName = "";
        this.labelEdit.passWord = "";
        this.labelEdit.remarks = "";
        this.editLabelTitle = "新增标签";
      }
    },
    // 编辑标签
    doEditLabel(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          const param = this.labelEdit;
          if (this.editLabelFlag) {
            this.$api.label
              .doEditLabel(param)
              .then(res => {
                this.$Notice.success({ title: res.data.msg });
                this.getLabelList();
              })
              .catch(err => {
                this.$Notice.warning({ title: err.data.msg });
              });
          } else {
            this.$api.label
              .doAddLabel(param)
              .then(res => {
                this.$Notice.success({ title: res.data.msg });
                this.getLabelList();
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
    // 删除标签
    doDelLabel(id) {
      this.$api.label
        .doDelLabel({ id: id })
        .then(res => {
          this.$Notice.success({ title: res.data.msg });
          this.getLabelList();
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    //改变页码
    pagechange(index) {
      this.currentPage = index;
      this.getLabelList();
    },
    //改变页数
    pageSizeChange(size) {
      this.pageSize = size;
      this.getLabelList();
    }
  },
  created: function() {
    this.getLabelList();
  }
};
</script>
