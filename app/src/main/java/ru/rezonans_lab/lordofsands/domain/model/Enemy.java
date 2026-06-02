package ru.rezonans_lab.lordofsands.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy {

    private long enemyId;
    private final String name;
    private int health;
    private final int power;
    private final int dmg;
    private final boolean isItSword;// true если бой на мечах

    public Enemy(String name, int health, int power, int dmg, boolean isItSword) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.dmg = dmg;
        this.isItSword = isItSword;
    }

    public void changeEnemyHealth(int health) {
        this.health += health;
    }
}