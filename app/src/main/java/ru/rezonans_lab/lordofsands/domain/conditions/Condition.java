package ru.rezonans_lab.lordofsands.domain.conditions;


import ru.rezonans_lab.lordofsands.domain.model.Hero;

public interface Condition {//надо сделать абстрактным классом??
    float getChapter(Hero hero);
}
