package ru.rezonans_lab.lordofsands.domain;

import ru.rezonans_lab.lordofsands.domain.model.Chapter;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

public class MainGameService {
    private Book book;

    public MainGameService() {
        this.book = Book.getInstance();  // вызовите здесь
    }

    public void setStartChapter(GameState gameState) {
        gameState.setChapter(book.getChapter(0));
    }

    public GameState handle(GameState gameState, int usersChoice) {

        float nextChapterId = gameState.getCurrentChapter()
                .nextChapter(gameState, usersChoice);
        Chapter nextChapter = book.getChapter(nextChapterId);

        nextChapter.process(gameState);
        gameState.setCurrentChapter(nextChapter);

        return gameState;
    }
}
