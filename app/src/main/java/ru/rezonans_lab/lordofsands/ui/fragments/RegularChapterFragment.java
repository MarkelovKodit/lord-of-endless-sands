package ru.rezonans_lab.lordofsands.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.R;
import ru.rezonans_lab.lordofsands.domain.model.Choice;
import ru.rezonans_lab.lordofsands.domain.model.GameState;
import ru.rezonans_lab.lordofsands.ui.MainActivity;
import ru.rezonans_lab.lordofsands.ui.MainViewModel;
import ru.rezonans_lab.lordofsands.ui.ResourceMapper;

@Getter
@Setter
public class RegularChapterFragment extends BaseChapterFragment {
    private ImageView illustration;
    private TextView chapterText;
    private LinearLayout choicesContainer;
    private MainViewModel viewModel;

    GameState gameState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regular_chapter, container, false);
        illustration = view.findViewById(R.id.illustration);
        chapterText = view.findViewById(R.id.chapterText);
        choicesContainer = view.findViewById(R.id.choicesContainer);

        // Получаем ViewModel через Activity
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        return view;
    }

    @Override
    public void updateContent(GameState gameState) {
        this.gameState = gameState;
        int resId = ResourceMapper.getInstance(requireContext())
                .getImageIdForChapter(gameState.getCurrentChapter().getId());
        illustration.setImageResource(resId);
        chapterText.setText(gameState.getCurrentText());
        buildChoices(gameState.getCurrentChoices());
    }

    private void buildChoices(ArrayList<Choice> choices) {
        choicesContainer.removeAllViews();

        if (choices == null || choices.isEmpty()) {
            return;
        }

        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            Button button = new Button(requireContext());
            button.setText(choice.getText());
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            final int index = i;
            button.setOnClickListener(v -> {
                // Передаём выбор в ViewModel
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).getViewModel().handleChoice(index);
                }
            });

            choicesContainer.addView(button);
        }
    }
}