package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

@AllArgsConstructor
public class Achievement implements Effect{
    private String achieve;
    @Override
    public void applyEffect(GameState state) {
        state.getHero().addAchieve(achieve);
    }
}
