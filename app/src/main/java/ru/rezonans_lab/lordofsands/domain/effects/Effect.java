package ru.rezonans_lab.lordofsands.domain.effects;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public interface Effect
{
    public void applyEffect(GameState state);

}