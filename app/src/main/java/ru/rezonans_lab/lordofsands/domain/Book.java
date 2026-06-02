package ru.rezonans_lab.lordofsands.domain;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.domain.model.Chapter;
import ru.rezonans_lab.lordofsands.domain.utils.BookParser;

@Getter
@Setter
public class Book {
    private static Book instance;
    private final Chapter[][] chapters;

    private Book(Chapter[][] chapters) {
        this.chapters = chapters;
    }

    public static Book getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Book must be initialized first");
        }
        return instance;
    }

    public static void initialize(InputStream jsonStream) {
        if (instance != null) {
            throw new IllegalStateException("Book already initialized");
        }
        Chapter [][] chapters = BookParser.parse(jsonStream);
        instance = new Book(chapters);
    }

    public Chapter getChapter(float id) {
        String idStr = String.valueOf(id);
        String[] parts = idStr.split("\\.");
        int mainId = Integer.parseInt(parts[0]);
        int subId = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;

        return chapters[mainId][subId];
    }
}