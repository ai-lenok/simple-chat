package org.training.chat.data;

import io.vertx.core.json.Json;

import java.util.List;

/**
 * DTO для работы с сущностью чата переписки
 */
public record Chat(String id, List<User> users) {
    @Override
    public String toString() {
        return Json.encode(this);
    }
}
