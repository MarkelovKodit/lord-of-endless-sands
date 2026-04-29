package ru.rezonans_lab.lordofsands.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import ru.rezonans_lab.lordofsands.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2500; // 2.5 секунды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Передаём данные в MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                intent.putExtra("player_name", "Странник");
//                intent.putExtra("player_level", 1);//TODO добавить меню загрузки игры
                startActivity(intent);
                finish(); // закрываем SplashActivity
            }
        }, SPLASH_DELAY);
    }
}
