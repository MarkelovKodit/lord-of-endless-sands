package ru.rezonans_lab.lordofsands.domain.model;

import lombok.*;

import java.util.ArrayList;


@Setter
@Getter
@Builder
public class GambleChapter extends Chapter{
    private final Gambler gambler;
    //этой сущности можно добавить имя, количество денег и тд
    //пока будет захардкожено?

    public GambleChapter(Gambler gambler) {
        this.gambler = gambler;
    }

    @Override
    public float nextChapter(GameState gameState, int userChoice) {
        if(gameState.getHero().getMoney() <2) {
//            publisher.publishEvent(new GameMessageEvent("\nК сожалению, у вас закончились деньги, поэтому вы вынуждены уйти"));
            return choices.get(choices.size()).getId();
        } else return gameState.isChapterEnded()? choices.get(0).getId() : choices.get(1).getId();
    }

    @Override
    public GameState process(GameState gameState) {
        gameState.setChapterEnded(false);
        int x = Dice.rollTheDice();
//        publisher.publishEvent(new GameMessageEvent("\nВы бросили кубик, выпало " + x));
        int y = Dice.rollTheDice();
//        publisher.publishEvent(new GameMessageEvent("\nГном бросает кубик, выпало " + y));
        if (x > y ) {
//            publisher.publishEvent(new GameMessageEvent("\nВы выиграли!"));
            gameState.setChapterEnded(true);
        } else {
//            publisher.publishEvent(new GameMessageEvent("\nВы проиграли..."));
            gameState.getHero().changeMoney(-2);

        }
        gameState.setChoices(null);
        //да, тут много создания GameMessage но так нагляднее оставлю так
        return gameState;
    }

    private ArrayList<String> choicesToOptions() {
        ArrayList<String> options = new ArrayList<>(12);
        for (Choice c: choices) {
            options.add(c.getText());
        }
        return options;
    }
}
