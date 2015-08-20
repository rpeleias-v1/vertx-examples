var eventBus = vertx.eventBus();

vertx.setPeriodic(2000, function(response) { 
  eventBus.publish("mensagem-request-response", "Mensagem enviada em JavaScript pelo modelo Publish-Subscribe!");
});
