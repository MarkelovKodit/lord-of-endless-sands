package ru.rezonans_lab.lordofsands.domain.model.events;

import lombok.Getter;

@Getter
public class GameMessageEvent {
    private final String message;

    public GameMessageEvent(String message) {
        this.message = message;
    }
}