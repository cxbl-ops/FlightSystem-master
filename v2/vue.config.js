const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    proxy: {
      '/dev-api': {
        target: 'http://localhost:9006',
        changeOrigin: true,
        pathRewrite: {
          '^/dev-api': '' // 以apiDev作为前缀
        }
      }
    }
  },
  transpileDependencies: true
})