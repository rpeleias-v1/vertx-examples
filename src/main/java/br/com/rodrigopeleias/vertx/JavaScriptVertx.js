vertx.createHttpServer()
  .requestHandler(function (req) {
    req.response()
	.putHeader("content-type", "text/html")
	.end("<h1>Desenvolvimento em Vert.x com JavaScript</h1>");
}).listen(8080);
