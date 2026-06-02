package ru.rezonans_lab.lordofsands.domain.effects;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public class MaxHealth implements Effect{
    @Override
    public void applyEffect(GameState state) {
        state.getHero().setHealth(24);//magic number, пока пофиг
    }
}
