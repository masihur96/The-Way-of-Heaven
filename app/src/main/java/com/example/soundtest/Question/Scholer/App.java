package com.example.soundtest.Question.Scholer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String  CHANNEL_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationCChannel();
    }

    private void createNotificationCChannel() {

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

            NotificationChannel channel1 = new NotificationChannel(

                    CHANNEL_1_ID,"channel 1",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("This is cchannel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }

}
