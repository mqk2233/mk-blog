<template>
  <div id="main">
    <Card>
      <i-form :label-width="60" inline>
        <Row>
          <i-col span="5">
            <FormItem label="分类" prop="articleEdit.categoryId">
              <Cascader
                  @on-change="getCategoryId"
                  :data="categoryList"
                  v-model="articleEdit.categoryIds"
                  clearable
                  class="form"
              />
            </FormItem>
          </i-col>
          <i-col span="5">
            <FormItem label="标签" prop="labelList">
              <i-select
                  multiple
                  clearable
                  :max-tag-count="2"
                  v-model="articleEdit.labelIds"
                  class="form"
              >
                <Option
                    v-for="item in labelList"
                    :value="item.id"
                    :key="item.id"
                    @on-change="changLabelId"
                >{{ item.labelName }}
                </Option>
              </i-select>
            </FormItem>
          </i-col>
        </Row>
      </i-form>
      <mavon-editor
          ref="md"
          @imgAdd="imgAdd"
          @change="changeData"
          v-model="articleEdit.contentMd"
          :ishljs="true"
          class="editor"
      />
    </Card>
    <i-form inline>
      <Row type="flex" justify="center" class="row">
        <FormItem>
          <Button @click="back">取消</Button>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="doAddArticle">提交</Button>
        </FormItem>
      </Row>
    </i-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      categoryList: [],
      labelList: [],
      articleEdit: {
        //文章对象
        id: 0,
        title: "",
        contentMd: "",
        contentHtml: "",
        status: 0,
        isDeleted: 0,
        categoryId: 0,
        labelIds: [],
        categoryIds: []
      }
    };
  },
  methods: {
    // 返回文章列表
    back() {
      this.$router.push({name: "articleAdmin"});
    },
    // 分类列表
    getCategoryList() {
      this.$api.category
          .categoryList()
          .then(res => {
            this.categoryList = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 标签列表
    getLabelList() {
      this.$api.label
          .labelAllList()
          .then(res => {
            this.labelList = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 改变标签id
    changLabelId(ids) {
      this.articleEdit.labelIds = ids;
    },
    // 编辑文章
    doAddArticle() {
      const param = this.articleEdit;
      if (param.id !== 0 && param.id !== null) {
        this.$api.article
            .doEditArticle(param)
            .then(res => {
              this.$Notice.success({title: res.data.msg});
              this.$router.push({name: "articleAdmin"});
            })
            .catch(err => {
              this.$Notice.warning({title: err.data.msg});
            });
      } else {
        param.id = null;
        this.$api.article
            .doAddArticle(param)
            .then(res => {
              this.$Notice.success({title: res.data.msg});
              this.$router.push({name: "articleAdmin"});
            })
            .catch(err => {
              this.$Notice.warning({title: err.data.msg});
            });
      }
    },
    // 绑定@imgAdd event
    imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      let formdata = new FormData();
      formdata.append("smfile", $file);
      this.$api.img.uploadImg(formdata).then(res => {
        this.$refs.md.$img2Url(pos, res.data.url);
      });
    },
    changeData(value, render) {
      this.articleEdit.contentHtml = render;
    },
    getArticleById() {
      this.$api.article
          .getArticleById(this.$router.currentRoute.params.id)
          .then(res => {
            this.articleEdit = res.data.data;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    getCategoryId(value) {
      this.articleEdit.categoryId = value[value.length - 1];
    }
  },
  mounted() {
    this.getCategoryList();
    this.getLabelList();
    if (
        this.$router.currentRoute.params.id != null &&
        this.$router.currentRoute.params.id !== ""
    ) {
      this.getArticleById();
      this.$router.currentRoute.meta.title = "编辑文章";
    }
  }
};
</script>
<style>
.form {
  width: 13em
}

.editor {
  z-index: 0 !important;
}

.row {
  margin-top: 20px
}
</style>
