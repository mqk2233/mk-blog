<template>
  <div>
    <Tree :data="data5" :render="renderContent" :key="treeKey"/>
    <Modal
        :loading="addCategoryFlag"
        @on-ok="append(modalTitle)"
        :title="modalTitle"
        v-model="isEditCategory"
    >
      <i-form :model="categoryEdit" :rules="ruleCustom" ref="categoryEdit">
        <FormItem label="分类名">
          <label>
            <Input placeholder="分类名" v-model="categoryEdit.title"/>
          </label>
        </FormItem>
        <FormItem label="排序标识" prop="sort">
          <label>
            <Input placeholder="排序标识" v-model="categoryEdit.sort"/>
          </label>
        </FormItem>
        <FormItem label="是否启用">
          <i-switch
              :false-value="1"
              size="large"
              :true-value="0"
              v-model="categoryEdit.isDeleted"
          >
            <span slot="open">启用</span>
            <span slot="close">禁用</span>
          </i-switch>
        </FormItem>
      </i-form>
    </Modal>
  </div>
</template>
<script>
export default {
  data() {
    return {
      categoryEdit: {
        // 分类对象
        id: 0,
        pid: 0,
        title: "",
        categoryName: "",
        sort: 0,
        isDeleted: 0
      },
      categoryEditInit: {
        // 分类对象
        id: 0,
        pid: 0,
        title: "",
        categoryName: "",
        sort: 0,
        isDeleted: 0
      },
      treeKey: 0,
      modalTitle: "",
      isEditCategory: false,
      addCategoryFlag: true,
      currentBeen: {},
      ruleCustom: {
        title: [
          {required: true, message: "分类名不能为空", trigger: "blur"},
          {
            pattern: /.{2,6}/,
            message: "请输入2-6位字符",
            trigger: "blur"
          }
        ]
      },
      data5: [
        {
          title: "分类树",
          expand: true,
          render: (h, {data}) => {
            return h(
                "span",
                {
                  style: {
                    display: "inline-block",
                    width: "300px",
                  }
                },
                [
                  h("span", [
                    h("Icon", {
                      props: {
                        type: "ios-folder-outline"
                      },
                      style: {
                        marginRight: "8px"
                      }
                    }),
                    h("span", data.title)
                  ]),
                  h(
                      "span",
                      {
                        style: {
                          display: "inline-block",
                          float: "right",
                          marginRight: "32px"
                        }
                      },
                      [
                        h("Button", {
                          props: Object.assign({}, this.buttonProps, {
                            icon: "ios-add",
                            type: "primary"
                          }),
                          style: {
                            width: "64px",
                            float: "right"
                          },
                          on: {
                            click: () => {
                              this.openModel(data);
                            }
                          }
                        })
                      ]
                  )
                ]
            );
          },
          children: []
        }
      ],
      buttonProps: {
        type: "default",
        size: "small"
      }
    };
  },
  methods: {
    renderContent(h, {root, node, data}) {
      return h(
          "span",
          {
            style: {
              display: "inline-block",
              width: "300px"
            }
          },
          [
            h("span", [
              h("Icon", {
                props: {
                  type: "ios-paper-outline"
                },
                style: {
                  marginRight: "8px"
                }
              }),
              h("span", data.title + "-" + data.sort)
            ]),
            h(
                "span",
                {
                  style: {
                    width: "100px",
                    display: "inline-block",
                    float: "right",
                  }
                },
                [
                  h("Button", {
                    props: Object.assign({}, this.buttonProps, {
                      icon: "ios-create-outline"
                    }),
                    style: {
                      marginRight: "8px"
                    },
                    on: {
                      click: () => {
                        this.openModel(data, "edit");
                      }
                    }
                  }),
                  h("Button", {
                    props: Object.assign({}, this.buttonProps, {
                      icon: "ios-add"
                    }),
                    style: {
                      marginRight: "8px"
                    },
                    on: {
                      click: () => {
                        this.openModel(data, "add");
                      }
                    }
                  }),
                  h("Button", {
                    props: Object.assign({}, this.buttonProps, {
                      icon: "ios-remove"
                    }),
                    on: {
                      click: () => {
                        this.remove(root, node, data);
                      }
                    }
                  })
                ]
            )
          ]
      );
    },
    // 新增或编辑节点
    append(flag) {
      if ("新增分类" === flag) {
        this.categoryEdit.pid = this.currentBeen.value || 0;
        this.categoryEdit.id = null;
        this.categoryEdit.categoryName = this.categoryEdit.title;
        const param = this.categoryEdit;
        this.doAddCategory(param);
      } else {
        this.categoryEdit.categoryName = this.categoryEdit.title;
        this.doEditCategory(this.categoryEdit);
      }
    },
    // 删除节点
    remove(root, node, data) {
      this.doDelCategory(data.value);
    },
    // 打开对话框
    openModel(data, flag) {
      if (flag === 'add') {
        this.isEditCategory = true;
        this.currentBeen = data;
        this.modalTitle = "新增分类";
        this.categoryEdit = this.categoryEditInit;
      } else {
        this.isEditCategory = true;
        this.categoryEdit = data;
        this.categoryEdit.id = data.value;
        this.modalTitle = "编辑分类";
      }
    },
    // 分类列表
    getCategoryList: function () {
      this.$api.category
          .categoryList()
          .then(res => {
            this.data5[0].children = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 通过id查找分类
    getCategoryById(id) {
      this.$api.category
          .getCategoryById(id)
          .then(res => {
            this.categoryEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 新增分类
    doAddCategory(param) {
      this.$api.category
          .doAddCategory(param)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getCategoryList();
            this.addCategoryFlag = false;
            this.isEditCategory = false;
            this.treeKey += 1;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    doEditCategory(param) {
      this.$api.category
          .doEditCategory(param)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getCategoryList();
            this.isEditCategory = false;
            this.treeKey += 1;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 删除分类
    doDelCategory(id) {
      this.$api.category
          .doDelCategory(id)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getCategoryList();
            this.treeKey += 1;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    }
  },
  mounted: function () {
    this.getCategoryList();
  }
};
</script>
