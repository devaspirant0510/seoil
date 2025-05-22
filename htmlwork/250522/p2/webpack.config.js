const path=require("path");
module.exports={
    mode : "development",
    entry : {main: "./app.js",}, 
    output : {
        filename: "[name].js",
    },
    module:{
        rules:[
            {
                test: /\.css$/,
                use: ["style-loader","css-loader"]
            }
        ]
    }
}