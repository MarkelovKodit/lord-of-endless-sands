package ru.rezonans_lab.lordofsands.domain.model;

import lombok.Getter;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.domain.conditions.Condition;
import ru.rezonans_lab.lordofsands.domain.effects.Effect;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Chapter {
    protected float id;
    protected String text;
    protected ArrayList <Effect> effects;
    protected ArrayList<Choice> choices;
    protected Condition condition;
    protected ChapterType chapterType;
    protected int xtraFactor;

    public abstract float nextChapter(GameState gameState, int userChoice);
    public abstract GameState process(GameState gameState);
}
