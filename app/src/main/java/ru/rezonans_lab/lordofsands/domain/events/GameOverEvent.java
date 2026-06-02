package ru.rezonans_lab.lordofsands.domain.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameOverEvent {
    private final Long chatId;
//    private final String reason;
}