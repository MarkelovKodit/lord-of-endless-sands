package ru.rezonans_lab.lordofsands.domain.effects;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.rezonans_lab.lordofsands.domain.model.Backpack;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

@AllArgsConstructor
@Log4j2
public class AddToBackpack implements Effect{
    private String stuff;
    @Override
    public void applyEffect(GameState state) {
        Backpack backpack =  state.getHero().getBackpack();
        if (backpack.getBackpackSize() < backpack.getBackpackCapacity()) {
            backpack.addToBackPack(stuff);
        } else {

            System.out.println("Доделай уже уведомления о том что рюкзак полон");
        }
    }
}
