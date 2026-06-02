package ru.rezonans_lab.lordofsands.domain.effects;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public class Curse implements Effect{
    @Override
    public void applyEffect(GameState state) {
        state.getHero().setCursed(true);
    }
}
