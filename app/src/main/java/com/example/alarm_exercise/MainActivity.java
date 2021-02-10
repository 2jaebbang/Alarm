package com.example.alarm_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private AlarmManager alarmManager;
    private int hour, minute;


    ArrayList<dayButton> dayButtons = new ArrayList<>();
    int[] dayID = {R.id.sun, R.id.mon, R.id.tue, R.id.wed, R.id.thu, R.id.fri, R.id.sat};
    Button[] day = new Button[7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timePicker);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        for(int i=0; i<7; i++){
            dayButtons.add(new dayButton(dayID[i], false));
        }
    }

    //저장
    public void regist(View view) {

        //요일이 선택됐는지 판별
        boolean button_state = false;
        for(int i=0; i<dayButtons.size(); i++){
            if(dayButtons.get(i).state == true) {
                button_state = true;
                break;
            }
        }

        if(button_state == false){
                Toast.makeText(this, "요일을 선택하세요", Toast.LENGTH_SHORT).show();
                return;
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }

        Intent intent = new Intent(this, Alarm.class);
        intent.putExtra("dayButtons",dayButtons);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        Date today = new Date();
        long intervalDay = 24*60*60*1000;    //24시간
        long selectTime = calendar.getTimeInMillis();
        long currentTime = System.currentTimeMillis();

        if(selectTime < currentTime) {
            selectTime += intervalDay;
        }

        Log.e("MainActivity","등록 버튼을 누른 시간 : "+today+"  설정한 시간 : "+calendar.getTime());

        //지정한 시간에 매일 알림
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectTime, AlarmManager.INTERVAL_DAY, pIntent);

        Toast.makeText(this, hour+":"+minute+"으로 알람 설정됨.", Toast.LENGTH_SHORT).show();
    }


    //취소
    public void unregist(View view) {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pIntent);
        Toast.makeText(this, "알람 취소", Toast.LENGTH_SHORT).show();
    }


    //요일 버튼 클릭
    public void onClick(View view) {
        for(int i=0; i<dayButtons.size(); i++){
            if(dayButtons.get(i).id == view.getId()){
                if(dayButtons.get(i).state == false){
                    dayButtons.get(i).state = true;
                } else {
                    dayButtons.get(i).state = false;
                }
                Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
            }

        }

    }
}