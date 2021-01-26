<template>
  <div>
    <Tree :data="data5" :render="renderContent" />
    <Modal
      :loading="addCategoryFlag"
      @on-ok="append"
      title="新增分类"
      v-model="isEditCategory"
    >
      <i-form :model="categoryEdit" :rules="ruleCustom" ref="categoryEdit">
        <FormItem label="分类名" prop="title">
          <i-input placeholder="分类名" v-model="categoryEdit.title" />
        </FormItem>
        <FormItem label="排序标识" prop="sort">
          <i-input placeholder="排序标识" v-model="categoryEdit.sort" />
        </FormItem>
        <FormItem label="是否启用">
          <i-switch
            :false-value="0"
            size="large"
            :true-value="1"
            v-model="categoryEdit.prohibit"
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
        pid: "",
        title: "",
        sort: 0,
        prohibit: 1
      },
      isEditCategory: false,
      addCategoryFlag: true,
      currentBeen: {},
      ruleCustom: {
        title: [
          { required: true, message: "分类名不能为空", trigger: "blur" },
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
          render: (h, { data }) => {
            return h(
              "span",
              {
                style: {
                  display: "inline-block",
                  width: "50%"
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
                        width: "64px"
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
    renderContent(h, { root, node, data }) {
      return h(
        "span",
        {
          style: {
            display: "inline-block",
            width: "50%"
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
                  icon: "ios-add"
                }),
                style: {
                  marginRight: "8px"
                },
                on: {
                  click: () => {
                    this.openModel(data);
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
    // 新增节点
    append() {
      this.categoryEdit.pid = this.currentBeen.id || 0;
      const param = this.categoryEdit;
      this.doAddCategory(param);
      this.clearBeen();
      const children = this.currentBeen.children || [];
      children.push({
        title: this.categoryEdit.title,
        expand: true
      });
      this.$set(this.currentBeen, "children", children);
    },
    // 删除节点
    remove(root, node, data) {
      const parentKey = root.find(el => el === node).parent;
      const parent = root.find(el => el.nodeKey === parentKey).node;
      const index = parent.children.indexOf(data);
      parent.children.splice(index, 1);
      this.doDelCategory(data.id);
    },
    // 打开对话框
    openModel(data) {
      this.isEditCategory = true;
      this.currentBeen = data;
    },
    // 分类列表
    getCategoryList: function() {
      this.$api.category
        .categoryList()
        .then(res => {
          this.data5[0].children = res.data.data;
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    // 新增分类
    doAddCategory(param) {
      this.$api.category
        .doAddCategory(param)
        .then(res => {
          this.$Notice.success({ title: res.data.msg });
          this.getCategoryList();
          this.addCategoryFlag = false;
          this.isEditCategory = false;
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    // 删除分类
    doDelCategory(id) {
      this.$api.category
        .doDelCategory({ id: id })
        .then(res => {
          this.$Notice.success({ title: res.data.msg });
          this.getCategoryList();
        })
        .catch(err => {
          this.$Notice.warning({ title: err.data.msg });
        });
    },
    // 清空对象属性
    clearBeen() {
      for (let key in this.categoryEdit) {
        delete this.categoryEdit[key];
      }
    }
  },
  mounted: function() {
    this.getCategoryList();
  }
};
</script>
