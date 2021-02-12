const proxy = require('http-proxy-middleware')

module.exports = function(app){
	app.use(
		proxy('/api',{ //A request with the prefix /api1 will trigger the proxy configuration
			target:'http://localhost:8000', //Request forwarding url
			changeOrigin:true,//Control the value of Host in the request header received by the server
			pathRewrite:{'^/api':''} //Rewrite request path
		})
	)
}