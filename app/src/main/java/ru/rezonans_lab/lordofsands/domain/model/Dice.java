package ru.rezonans_lab.lordofsands.domain.model;

import java.util.Random;

public class Dice {
    public static int rollTheDice() {
        Random random = new Random();
        return (random.nextInt(5) + 1);
    }
}