const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const path=require("path");
module.exports={
    mode : "development",
    
    entry : {index: "./index.js",}, 
    
    output : {
        path:path.resolve(__dirname,"freedirectory"),
        filename: "[name].js",
    },
    
    module:{
        rules:[
            {
                test: /\.css$/i,
                use: [MiniCssExtractPlugin.loader,"css-loader"]
            }
        ]
    },
    plugins:[
        new HtmlWebpackPlugin({
            template:'./index.html',
            filename:'index.html',
            chunks:['index']
        }),
        new MiniCssExtractPlugin(),
    ]
}