package ru.rezonans_lab.lordofsands.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@Setter
@Getter
//@Builder
public class FightChapter extends Chapter {
    private StringBuilder textToPrint = new StringBuilder();//initial capacity??
    private Enemy enemy;

    @Override
    public float nextChapter(GameState gameState, int userChoice) {
        //если бой еще не закончен повторить действие из этой главы
//        if (gameState.isChapterEnded()) {
//        log.info("точка выхода из главы туть - {}", choices.getFirst().id());
//        if (gameState.getEnemyHealth() < 1) {
            return choices.get(0).getId();
//        } else return this.id;
        //или идти дальше
    }

    @Override
    public GameState process(GameState gameState) {
        Hero hero = gameState.getHero();
//        int enemyHealth = gameState.getEnemyHealth() == 0 ? enemy.getHealth() : gameState.getEnemyHealth();
        //если это новый бой, в старом gamestate здоровье врага равно нулю
        if (gameState.getEnemyHealth() != 0 )  enemy.setHealth(gameState.getEnemyHealth());
        int heroPower;
        int enemyPower = enemy.getPower();
        textToPrint.append("Герой. Здоровье - ").append(hero.getHealth());
        textToPrint.append(" || " + "Соперник - ").append(enemy.getName());
        textToPrint.append(". Здоровье - ").append(enemy.getHealth());
        if (enemy.isItSword()) {
            heroPower = hero.getDex();
        } else {
            heroPower = hero.getMindPower();
        }
//        textToPrint.append("""
//                \s
//                Определяем броском кубиков силу удара\s
//                """);
        heroPower += rollTheDice() + rollTheDice();
        enemyPower += rollTheDice() + rollTheDice();
        textToPrint.append("Сила удара героя - ").append(heroPower).append(";");
        textToPrint.append("Сила удара противника - ").append(enemyPower).append("\n");
        if (heroPower == enemyPower) {
            textToPrint.append("Удары равны по силе");
        } else if (heroPower > enemyPower) {
            textToPrint.append("Вы наносите противнику урон ").append(hero.getDmg()).append("\n");
            enemy.changeEnemyHealth(-hero.getDmg());
        } else {
            textToPrint.append("Противник наносит вам урон ").append(enemy.getDmg()).append("\n");
            hero.changeHealth(-enemy.getDmg());
        }
        if (enemy.getHealth() < 1) {
            textToPrint.append("\n" + "Победа за вами! Враг повержен");
            gameState.setChapterEnded(true);//в эту главу более не возвращаемся
            hero.addAchieve("Убит" + enemy.getName());
            if (!enemy.isItSword()) {
                if (hero.getMindPower() == 10) {
                    textToPrint.append("Вы уже достигли предела возможностей во владении Силой Мысли");
                } else {
                    hero.changeMindPower(1);
                    textToPrint.append("\n Ваша Сила Мысли увеличилась на 1!");
                }
            }
        }
        gameState.setHero(hero);
        gameState.setTextToPrint(textToPrint.toString());
        gameState.setEnemyHealth(enemy.getHealth());
        gameState.setActualChapterId(id);
        textToPrint.setLength(0);
        return gameState;
    }

    private int rollTheDice() {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }
}