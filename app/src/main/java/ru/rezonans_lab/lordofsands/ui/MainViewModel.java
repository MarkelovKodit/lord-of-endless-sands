package ru.rezonans_lab.lordofsands.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.domain.MainGameService;
import ru.rezonans_lab.lordofsands.domain.model.ChapterType;
import ru.rezonans_lab.lordofsands.domain.model.GameState;

@Getter
@Setter
public class MainViewModel extends ViewModel {

    private final MutableLiveData<ChapterType> currentFragment = new MutableLiveData<>();
    private MainGameService mainGameService;
    private GameState gameState;

    public MainViewModel() {
        mainGameService = new MainGameService();
        gameState = new GameState();
    }

    public LiveData<ChapterType> getCurrentFragment() {
        return currentFragment;
    }

    private void updateCurrentFragment() {
        ChapterType type = gameState.getCurrentChapter().getChapterType();
        currentFragment.setValue(type);
    }

    public void startGame() {
        mainGameService.setStartChapter(gameState);
        updateCurrentFragment();
    }

    public void handleChoice(int choice) {
        gameState = mainGameService.handle(gameState, choice);
        updateCurrentFragment();
    }
}