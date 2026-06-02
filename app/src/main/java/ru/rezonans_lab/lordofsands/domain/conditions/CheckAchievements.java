package ru.rezonans_lab.lordofsands.domain.conditions;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.Hero;

@AllArgsConstructor
public class CheckAchievements implements Condition{
    private String achievement;
    private float[] choices;
    @Override
    public float getChapter(Hero hero) {
        return hero.getAchievements().contains(achievement) ? choices[0]: choices[1];
    }
}
