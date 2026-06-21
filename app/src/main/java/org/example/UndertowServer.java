package org.example;

import java.util.Deque;
import java.util.Map;

import javax.inject.Inject;

import org.example.Command.Result;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;


final class UndertowServer {

    private final CommandRouter commandRouter;

    @Inject
    UndertowServer(CommandRouter cmdRouter) {
        this.commandRouter = cmdRouter;
    }

    Undertow build() {
        System.out.println("building server");
        Undertow server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(new HttpHandler() {
                @Override
                public void handleRequest(final HttpServerExchange exchange) throws Exception {
                    Map<String, Deque<String>> reqMap = exchange.getQueryParameters();
                    Deque<String> commandBody = reqMap.get("command");
                    if (commandBody == null || commandBody.isEmpty()) {
                        exchange.setStatusCode(400);
                        exchange.getResponseSender().send("missing 'command' parameter\n");
                        return;
                    }
                    String cmdInput = commandBody.getFirst();

                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    Result result = commandRouter.route(cmdInput);
                    String status = result.status() == Result.Status.HANDLED ? "Successful" : "Failed";
                    exchange.getResponseSender().send("Response: "+ status + " Result: " + result.output());
                    System.out.println("Received command: " + cmdInput + " Result: " + result.output());
                }
            }).build();
        return server;
    }

}

