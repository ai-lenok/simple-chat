package org.training.chat.handler;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.ServerWebSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.chat.data.TextMessage;
import org.training.chat.util.Answerer;

/**
 * Handler, который отправляет в WebSocket сообщение от сервера -> клиенту
 */
public class SendMessageHandler implements Handler<Message<TextMessage>> {

    private final Logger logger = LogManager.getLogger(SendMessageHandler.class);

    private ServerWebSocket webSocket;

    public SendMessageHandler(ServerWebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public void handle(Message<TextMessage> data) {
        TextMessage message = data.body();
        logger.info("WebSocket message: " + message);

        String response = Answerer.createResponse("text", message);
        webSocket.writeFinalTextFrame(response);

        data.reply("ok");
    }
}
