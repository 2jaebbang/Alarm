package com.example.alarm_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {

    TextView alarmSetTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_time_view);

        alarmSetTime = (TextView) findViewById(R.id.timeView);

        Intent intent = getIntent();
        int setDay = intent.getIntExtra("setDay", 0);
        String day = "";
        switch (setDay){
            case 0:
                day="일요일";
                break;
            case 1:
                day="월요일";
                break;
            case 2:
                day="화요일";
                break;
            case 3:
                day="수요일";
                break;
            case 4:
                day="목요일";
                break;
            case 5:
                day="금요일";
                break;
            case 6:
                day="토요일";
                break;
            default:
                day="err";
                break;
        }
        String setTime = day + "알람 셋팅 시간 " + intent.getStringExtra("setTime");
        alarmSetTime.setText(setTime);
    }
}