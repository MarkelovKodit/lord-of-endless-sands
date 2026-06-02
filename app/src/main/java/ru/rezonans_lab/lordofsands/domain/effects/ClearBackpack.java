package ru.rezonans_lab.lordofsands.domain.effects;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public class ClearBackpack implements Effect{
    @Override
    public void applyEffect(GameState state) {
        state.getHero().getBackpack().clearBackPack();
    }
}
