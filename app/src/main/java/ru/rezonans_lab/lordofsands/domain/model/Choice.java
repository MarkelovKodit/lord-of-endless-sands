package ru.rezonans_lab.lordofsands.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Choice {
    private float id;
    private String text;

    public boolean hasId() {
        return id != -1;
    }
    public boolean hasText() {
        return text != null;
    }
}
