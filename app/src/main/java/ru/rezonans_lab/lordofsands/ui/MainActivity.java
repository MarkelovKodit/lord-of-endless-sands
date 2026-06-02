package ru.rezonans_lab.lordofsands.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.Getter;
import lombok.Setter;
import ru.rezonans_lab.lordofsands.R;
import ru.rezonans_lab.lordofsands.domain.Book;
import ru.rezonans_lab.lordofsands.ui.fragments.BaseChapterFragment;
import ru.rezonans_lab.lordofsands.ui.fragments.FightFragment;
import ru.rezonans_lab.lordofsands.domain.model.ChapterType;
import ru.rezonans_lab.lordofsands.ui.fragments.GambleFragment;
import ru.rezonans_lab.lordofsands.ui.fragments.RegularChapterFragment;
import ru.rezonans_lab.lordofsands.ui.fragments.TradeFragment;

@Setter
@Getter
public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ChapterType lastChapterType = null;
    private BaseChapterFragment currentFragment = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Book.initialize(getResources().openRawResource(R.raw.lord_of_desert));

        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCurrentFragment().observe(this, chapterType -> {
            updateFragment(chapterType);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        // Обработка нажатий на нижнее меню
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_stats) {
                showStatsDialog();
                return true;
            } else if (id == R.id.nav_inventory) {
                showInventoryDialog();
                return true;
            } else if (id == R.id.nav_settings) {
                showSettingsDialog();
                return true;
            }
            return false;
        });

        viewModel.startGame();
    }

    private void showSettingsDialog() {
    }

    private void showInventoryDialog() {
    }

    private void showStatsDialog() {
    }

    private void updateFragment(ChapterType newType) {
        BaseChapterFragment fragment;
        if (lastChapterType != newType) {
            switch (newType) {
                case REGULAR:
                    fragment = new RegularChapterFragment();
                    break;
                case FIGHT:
                    fragment = new FightFragment();
                    break;
                case TRADE:
                    fragment = new TradeFragment();
                    break;
                case GAMBLE:
                    fragment = new GambleFragment();
                    break;
                default:
                    throw new RuntimeException("UNKNOWN TYPE OF FRAGMENT");
            }
            currentFragment = fragment;
            lastChapterType = newType;

            currentFragment.updateContent(viewModel.getGameState());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        } else {
            currentFragment.updateContent(viewModel.getGameState());
        }
    }
}