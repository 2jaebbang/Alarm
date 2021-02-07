package com.example.alarm_exercise;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_close);

        //알림음 재생
        this.mediaPlayer = MediaPlayer.create(this, R.raw.chicken);
        this.mediaPlayer.start();

        findViewById(R.id.btn_stop).setOnClickListener(mClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    //알람 종료
    private void close() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }

        finish();
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_close:
                    close();
                    break;
            }
        }
    };





}
