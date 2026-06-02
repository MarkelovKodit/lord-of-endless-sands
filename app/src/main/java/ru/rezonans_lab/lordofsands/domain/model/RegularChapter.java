package ru.rezonans_lab.lordofsands.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

@Log4j2
@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class RegularChapter extends Chapter {
    public float nextChapter(GameState gameState, int userChoice) {
        System.out.println("Мы в regular главе" + id +
                " определяем следующую главу, выбор игрока - " + userChoice);
        if (condition == null) {
            try {
//                log.info("выбор игрока соответствует главе {}", choices.get(userChoice).id());
                return choices.get(userChoice).getId();

            } catch (IndexOutOfBoundsException e) {
//                log.error("в request пришло некорректное userChoice");
                return 1;//временная заглушка
            }
        } else {
            return condition.getChapter(gameState.getHero());
        }
    }

    public GameState process(GameState gameState) {
        Hero hero = gameState.getHero();

        if (effects != null) {
            for (int i = 0; i < effects.size(); i++) {
                effects.get(i).applyEffect(gameState);
            }
        }
        return gameState;
    }

    private ArrayList<String> choicesToOptions() {//TODO переименовать за пределами модуля?
        ArrayList<String> options = new ArrayList<>(12);
        if (choices == null) return null;
        for (Choice choice : choices) {
            options.add(choice.getText());
        }
        return options;
    }
}