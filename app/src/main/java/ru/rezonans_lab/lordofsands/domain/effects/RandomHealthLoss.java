package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.domain.model.Dice;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

@Setter
@AllArgsConstructor
public class RandomHealthLoss implements Effect{
    private int xtraFactor;
//    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void applyEffect(GameState state) {
        int healthLoss = Dice.rollTheDice()*xtraFactor;
        state.addEventLog("Вы потеряли " + healthLoss + " здоровья");

        if (state.getHero().getHealth() <healthLoss) {
            state.setGameOver(true);
            state.addEventLog("Раны оказались смертельными...");

        } else {
         state.getHero().changeHealth(-healthLoss);
        }
    }
}