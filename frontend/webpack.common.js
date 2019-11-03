const path = require('path');
const webpack = require('webpack');

const SRC_DIR = path.resolve(__dirname, 'client/src');
const DIST_DIR = path.resolve(__dirname, 'client/dist');

module.exports = {
  entry: {
    javascript: `./index.js`
  },
  context: `${SRC_DIR}`,
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader']
      },
      {
        test: /\.s[ac]ss$/i,
        use: ['style-loader', 'css-loader', 'sass-loader',
        ],
      },
    ]
  },
  resolve: {
    extensions: ['*', '.js', '.jsx']
  },
  output: {
    path:  DIST_DIR,
    publicPath: '/',
    filename: 'bundle.js'
  }
};
