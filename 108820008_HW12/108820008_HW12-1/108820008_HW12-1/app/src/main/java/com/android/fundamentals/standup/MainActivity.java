/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.fundamentals.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String toastMessage;
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.AM_PM) == Calendar.PM ? calendar.get(Calendar.HOUR) + 12 : calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.HOUR);

                if (isPastElevenEleven(hour, minute)) calendar.add(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.AM_PM, Calendar.AM);
                calendar.set(Calendar.HOUR, 11);
                calendar.set(Calendar.MINUTE, 11);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if (alarmManager != null) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), notifyPendingIntent);
                }
                toastMessage = getString(R.string.alarm_on_toast);
            } else {
                mNotificationManager.cancelAll();

                if (alarmManager != null) {
                    alarmManager.cancel(notifyPendingIntent);
                }
                toastMessage = getString(R.string.alarm_off_toast);
            }

            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        });

        createNotificationChannel();
    }

    public void createNotificationChannel() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Make a Wish notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifies at the closest 11:11 AM");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private boolean isPastElevenEleven(int hour, int minute) {
        return (hour == 11) ? (minute >= 11) : (hour >= 11);
    }
}
