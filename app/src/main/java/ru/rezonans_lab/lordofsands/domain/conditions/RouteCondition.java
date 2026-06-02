package ru.rezonans_lab.lordofsands.domain.conditions;

import lombok.AllArgsConstructor;
import ru.rezonans_lab.lordofsands.domain.model.Hero;

@AllArgsConstructor
public class RouteCondition implements Condition{
    private float[] choices;
    private String name;

    @Override
    public float getChapter(Hero hero) {
        int actualValue;
        switch (name) {
            case "mind":
                actualValue = hero.getMindPower();
                break;
            case "dex":
                actualValue = hero.getDex();
                break;
            case "charm":
                actualValue = hero.getCharm();
                break;
            default:
                throw new IllegalArgumentException("Unknown stat: " + name);
        }
        return choices[actualValue];
    }
}
