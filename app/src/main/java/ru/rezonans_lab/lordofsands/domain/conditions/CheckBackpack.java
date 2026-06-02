package ru.rezonans_lab.lordofsands.domain.conditions;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.Hero;

@AllArgsConstructor
public class CheckBackpack implements Condition{
    private String item;
    private float[] choices;

    @Override
    public float getChapter(Hero hero) {
        return hero.getBackpack().lookForItem(item) ? choices [0] : choices [1];
    }
}
