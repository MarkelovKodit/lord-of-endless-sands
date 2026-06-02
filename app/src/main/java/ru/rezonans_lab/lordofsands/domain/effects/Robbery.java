package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

import java.util.Random;
public class Robbery implements Effect{

    @Override
    public void applyEffect(GameState state) {
        Random random = new Random();
        int i = random.nextInt(6);
        state.addEventLog("У вас пропал " + state.getHero().getBackpack().showItem(i));
        state.getHero().getBackpack().deleteItemFromBackpack(i);
    }
}