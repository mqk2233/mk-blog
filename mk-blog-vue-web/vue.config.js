module.exports = {
    publicPath: "/", //根路径
    outputDir: "dist", //构建输出路径
    lintOnSave: true, //是否开启eslink语法检查
    devServer: {
        port: 8081,
        open: true, //打开浏览器
        proxy: {
            "/api": {
                target: "http://localhost:8080/blog", //host
                ws: true, //是否代理
                changeOrigin: true, //是否跨域
                pathRewrite: {
                    "^/api": "" //重写路径
                }
            }
        }
    }
};
