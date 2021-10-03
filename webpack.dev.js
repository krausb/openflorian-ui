const path = require('path');
const webpack = require('webpack');
const { merge } = require('webpack-merge');

const common = require('./webpack.common.js');

const DIST_DIR = path.resolve(__dirname, 'client/dist');

module.exports = (env, argv) => {
  return merge(common(env, argv), {
    mode: 'development',
    plugins: [
      new webpack.HotModuleReplacementPlugin()
    ],
    devServer: {
      static: [DIST_DIR],
      hot: true
    }
  });
};