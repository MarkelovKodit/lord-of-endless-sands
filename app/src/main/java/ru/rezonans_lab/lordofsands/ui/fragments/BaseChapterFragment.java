package ru.rezonans_lab.lordofsands.ui.fragments;

import androidx.fragment.app.Fragment;

import ru.rezonans_lab.lordofsands.domain.model.GameState;

public abstract class BaseChapterFragment extends Fragment {
    public abstract void updateContent(GameState gameState);
}
