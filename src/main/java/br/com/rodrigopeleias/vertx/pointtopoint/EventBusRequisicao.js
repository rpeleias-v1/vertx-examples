var eventBus = vertx.eventBus();

vertx.setPeriodic(2000, function(response) { 
  eventBus.send("mensagem-request-response", "Mensagem enviada em JavaScript!", function(reply, reply_err) { 
    if (reply_err == null) {
	console.log("Recebida a resposta no código JavaScript = " + reply.body());
    }
    else {
	console.log("Sem resposta!");
    }
  });
});
