package com.example.alarm_exercise;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {      //IBinder : 클라이언트가 서비스와 상호작용하는데 사용할 수 있는 프로그래밍 인터페이스 정의.
        return null;                            //onBind : 서비스에 바인딩 제공.
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Foreground 에서 실행되면 Notification을 보여줘야 함.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Oreo(26) 버전 이후 버전부터는 channel이 필요함.
            String channelId = createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
            Notification notification = builder.setOngoing(true).setSmallIcon(R.mipmap.ic_launcher).build();


            startForeground(1, notification);
        }

        //알림창 호출
        Intent intent1 = new Intent(this, AlarmActivity.class);
        //새로운 task를 생성해서 activity를 최상위로 올림
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);

        Log.d("AlarmService", "Alarm");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        }

        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    private String createNotificationChannel() {
        String channelId = "Alarm";
        String channelName = getString(R.string.app_name);
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE);
        channel.setSound(null, null);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        return channelId;
    }
}
