package com.projetosrafaelfemina.playmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private ImageView pause, play, stop;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ComponentsStart();

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseNow();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayNow();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StopNow();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player.isPlaying()) {
            player.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
        }
    }

    private void StopNow() {
        if (player.isPlaying()) {
            player.stop();
            player = MediaPlayer.create(getApplicationContext(), R.raw.global);
        }
    }

    private void PlayNow() {
        if (player != null) {
            player.start();
        }
    }

    private void PauseNow() {
        if (player.isPlaying()) {
            player.pause();
        }
    }

    private void ComponentsStart() {
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);

        player = MediaPlayer.create(getApplicationContext(), R.raw.global);
    }

}