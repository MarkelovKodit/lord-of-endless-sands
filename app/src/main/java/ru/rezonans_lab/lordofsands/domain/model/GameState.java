package ru.rezonans_lab.lordofsands.domain.model;


import lombok.*;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class GameState {
    private Hero hero;
    private Chapter currentChapter;
    private boolean gameOver;

    private StringBuilder eventLog = new StringBuilder();

    public GameState() {
        hero = new Hero();
    }

    public String getCurrentText() {
        return currentChapter != null ? currentChapter.getText() : "";
    }

    public ArrayList<Choice> getCurrentChoices() {
        return currentChapter != null ? currentChapter.getChoices() : new ArrayList<>();
    }

    public void setChapter(Chapter chapter){
        currentChapter = chapter;
    }

    public void addEventLog(String message) {
        eventLog.append(message).append("\n");
    }

    public String getEventLog() { return eventLog.toString(); }
    public void clearEventLog() { eventLog.setLength(0); }
}

