package org.training.chat;

import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.training.chat.codec.CommonMessageCodec;
import org.training.chat.data.Chat;
import org.training.chat.data.TextMessage;
import org.training.chat.verticle.*;

public class MainApp {

    private final static Logger logger = LogManager.getLogger(MainApp.class);

    public static void main(String args[]) {
        logger.info("Start App");
        Vertx vertx = Vertx.vertx();

        registerCodec(vertx);

        deploy(vertx);
    }

    private static void registerCodec(Vertx vertx) {
        vertx.eventBus().registerDefaultCodec(TextMessage.class, new CommonMessageCodec<>(TextMessage.class));
        vertx.eventBus().registerDefaultCodec(Chat.class, new CommonMessageCodec<>(Chat.class));
    }

    private static void deploy(Vertx vertx) {
        vertx.deployVerticle(new WsServerVerticle());
        vertx.deployVerticle(new MongoDbVerticle());
        vertx.deployVerticle(new MetadataVerticle());
        vertx.deployVerticle(new RouterVerticle());
        vertx.deployVerticle(new RestStaticVerticle());
        vertx.deployVerticle(new ValidateTokenVerticle());
    }
}
