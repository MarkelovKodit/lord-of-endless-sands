package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.rezonans_lab.lordofsands.domain.effects.Effect;
import ru.rezonans_lab.lordofsands.domain.model.GameState;
import ru.rezonans_lab.lordofsands.domain.model.Hero;
import ru.rezonans_lab.lordofsands.domain.model.Stat;

@Log4j2
@RequiredArgsConstructor
public class ChangeStat implements Effect {
    private Stat stat;
    private int amount;

    public ChangeStat(Stat stat, int amount) {
        this.stat = stat;
        this.amount = amount;
    }

    @Override
    public void applyEffect(GameState state) {
        Hero hero = state.getHero();
        hero.changeStat(stat, amount);
    }
}
