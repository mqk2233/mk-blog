<template>
  <div>
    <Card class="card">
      <i-form :label-width="80" inline>
        <FormItem label="文章标题" class="form-item">
          <label>
            <Input
                clearable
                placeholder="文章标题"
                v-model="searchArticleName"
            />
          </label>
        </FormItem>
        <FormItem class="form-item2">
          <Button
              icon="md-create"
              style="margin-right: 1em"
              type="success"
              @click="toAddArticle()"
          >新增
          </Button>
          <Button @click="getArticleList" icon="ios-search" type="info"
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
          no-data-text="没有数据呀 ( ๑ŏ ﹏ ŏ๑ )"
          stripe
          tooltip
      >
        <template slot="name" slot-scope="{ row }">
          <strong>{{ row.name }}</strong>
        </template>
        <template slot="action" slot-scope="{ row }">
          <Button
              @click="doReleaseArticle(row.id)"
              size="small"
              class="button"
              type="success"
              ghost
              v-show="row.status === 0 && row.isDeleted === 0"
          >发布
          </Button>
          <Button
              @click="$router.push({ name: 'addArticle', params: { id: row.id } })"
              size="small"
              class="button"
              type="info"
              ghost
              v-show="row.isDeleted === 0"
          >编辑
          </Button>
          <Button
              @click="doDelArticle(row.id)"
              size="small"
              type="error"
              ghost
              v-show="row.isDeleted === 0"
          >删除
          </Button>
        </template>
      </Table>
    </Card>
    <Page
        :current="currentPage"
        :page-size="pageSize"
        :total="total"
        @on-change="pageChange"
        @on-page-size-change="pageSizeChange"
        show-sizer
        show-total
        class="page"
        transfer
    />
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: [], //文章数组
      listId: "",
      pageSize: 10, //每页显示多少条
      total: 0, //总条数
      currentPage: 1, //当前页
      editArticleTitle: "编辑文章", //对话框标题
      searchArticleName: "", //搜索框文章名
      loading: true,
      isDeleted: 0,
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
          title: "文章标题",
          key: "title"
        },
        {
          align: "center",
          title: "文章状态",
          key: "status",
          render: (h, params) => {
            return h(
                "Tag",
                {
                  props: {
                    color: params.row.status === 0 ? "cyan" : "green"
                  }
                },
                params.row.status === 0 ? "已保存" : "已发布"
            );
          }
        },
        {
          align: "center",
          title: "分类",
          key: "categoryName",
          render: (h, params) => {
            return h(
                "Tag",
                {
                  props: {
                    color: "#FFA2D3"
                  }
                },
                params.row.categoryName
            );
          }
        },
        {
          align: "center",
          title: "标签",
          key: "labelNames",
          render: (h, params) => {
            return h('div', params.row.labelNames.map(item => {
                  return h("Tag",
                      {
                        props: {
                          color: "gold"
                        }
                      },
                      item)
                })
            );
          }
        },
        {
          align: "center",
          title: "写作时间",
          key: "createTime"
        },
        {
          title: "操作",
          slot: "action",
          width: 200,
          align: "center"
        }
      ],
      articleEdit: {
        //文章对象
        id: 0,
        title: "",
        status: 0,
        isDeleted: 0
      },
      imgHeader: this.$api.img.headers
    };
  },
  methods: {
    toAddArticle() {
      this.$router.replace({name: "addArticle"});
    },
    // 文章列表
    getArticleList() {
      this.$api.article
          .articleAdminList(
              this.searchArticleName,
              this.isDeleted,
              this.pageSize,
              this.currentPage
          )
          .then(res => {
            this.list = res.data.data;
            this.total = parseInt(res.data.total);
            this.loading = false;
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 删除文章
    doDelArticle(id) {
      this.$api.article
          .doDelArticle(id)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getArticleList();
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 改变页码
    pageChange(index) {
      this.currentPage = index;
      this.getArticleList();
    },
    // 改变页数
    pageSizeChange(size) {
      this.pageSize = size;
      this.getArticleList();
    },
    // 发布文章
    doReleaseArticle(id) {
      this.$api.article
          .doReleaseArticle(id)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getArticleList();
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    },
    // 恢复文章
    doRecoveryArticle(id) {
      this.$api.article
          .doReleaseArticle(id)
          .then(res => {
            this.$Notice.success({title: res.data.msg});
            this.getArticleList();
          })
          .catch(err => {
            this.$Notice.warning({title: err.data.msg});
          });
    }

  },
  mounted() {
    this.getArticleList();
  }
};
</script>
<style>
.card {
  margin-bottom: 0.5em;
}

.form-item {
  margin: auto;
}

.form-item2 {
  float: right;
}

.button {
  margin-right: 5px
}

.page {
  float: right;
  margin-top: 1em;
}
</style>