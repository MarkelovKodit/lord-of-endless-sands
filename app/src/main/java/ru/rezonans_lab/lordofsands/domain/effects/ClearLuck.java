package ru.rezonans_lab.lordofsands.domain.effects;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public class ClearLuck implements Effect{
    @Override
    public void applyEffect(GameState state) {
        state.getHero().clearLuck();
    }
}
