package org.training.chat.handler;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.ServerWebSocket;

/**
 * Handler, который отправляет в WebSocket сообщение от сервера -> клиенту
 */
public class SendMessageHandler implements Handler<Message<String>> {

    private ServerWebSocket webSocket;

    public SendMessageHandler(ServerWebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void handle(Message<String> event) {
        String message = event.body();
        System.out.println("WebSocket message: " + message);
        webSocket.writeFinalTextFrame("Echo message: " + message);
    }
}