package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

@AllArgsConstructor
public class GameOver implements Effect{

    @Override
    public void applyEffect(GameState gameState) {
        gameState.setGameOver(true);
    }
}
