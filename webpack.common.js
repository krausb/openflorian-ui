const path = require('path');
const dotenv = require('dotenv');
const fs = require('fs');
const webpack = require('webpack');

const SRC_DIR = path.resolve(__dirname, 'client/src');
const DIST_DIR = path.resolve(__dirname, 'client/dist');

module.exports = (env) => {
    const currentPath = path.join(__dirname);
    const basePath = currentPath + '/.env';
    const envPath = basePath + '.' + env.ENVIRONMENT;
    console.log("environment: " + env.ENVIRONMENT);
    const finalPath = fs.existsSync(envPath) ? envPath : basePath;
    const fileEnv = dotenv.config({ path: finalPath }).parsed;
    const envKeys = Object.keys(fileEnv).reduce((prev, next) => {
        prev[`process.env.${next}`] = JSON.stringify(fileEnv[next]);
        return prev;
    }, {});

    console.log("Environment variables: ", envKeys);

    return {
        entry: './index.js',
        context: SRC_DIR,
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
        plugins: [
            new webpack.DefinePlugin(envKeys)
        ],
        output: {
            path: DIST_DIR,
            publicPath: '/',
            filename: 'bundle.js'
        }
    };
};
