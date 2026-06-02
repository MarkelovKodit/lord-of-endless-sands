package ru.rezonans_lab.lordofsands.domain.conditions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.rezonans_lab.lordofsands.domain.model.Dice;
import ru.rezonans_lab.lordofsands.domain.model.Hero;

@AllArgsConstructor
@Builder
public class CheckStat implements Condition {
    private String name;
    private int valueToCompare;
    private float[] choices;


    @Override
    public float getChapter(Hero hero) {
        switch (name) {
            case "dex":
                valueToCompare = Dice.rollTheDice() + Dice.rollTheDice();
                break;
            case "charm":
                valueToCompare = Dice.rollTheDice();
                break;
            default:
                valueToCompare = 0;
                break;
        }

        int actualValue;
        switch (name) {
            case "health":
                actualValue = hero.getHealth();
                break;
            case "mind":
                actualValue = hero.getMindPower();
                break;
            case "money":
                actualValue = hero.getMoney();
                break;
            case "dex":
                actualValue = hero.getDex();
                break;
            case "charm":
                actualValue = hero.getCharm();
                break;
            case "luck":
                actualValue = hero.testLuck() ? 1 : 0;
                break;
            default:
                throw new IllegalArgumentException("Unknown stat: " + name);
        }

        return actualValue >= valueToCompare ? choices[0] : choices[1];
    }
}