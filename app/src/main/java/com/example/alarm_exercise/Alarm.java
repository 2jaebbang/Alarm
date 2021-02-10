package com.example.alarm_exercise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//정해진 시간에 실행
public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ArrayList<dayButton> dayButtons = (ArrayList<dayButton>) intent.getSerializableExtra("dayButtons");

        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        

        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        String time1 = format1.format(time);

        Intent intent2 = new Intent(context, ListViewActivity.class);
        intent2.putExtra("setDay", dayOfWeek);
        intent2.putExtra("setTime", time1.toString());

        context.startService(intent2);

        Toast.makeText(context, "알람~!!", Toast.LENGTH_SHORT).show();
        Log.e("TAG","알람입니다~!!"+"알람 요일"+dayOfWeek+" 알람 울린 시간  : "+time1);
    }
}
