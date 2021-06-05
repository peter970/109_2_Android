package com.example.a107360207_hw12_1;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 *
 * @ClassName: MainActivity
 * @Description: 主介面
 *
 */
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TimePicker mTimePicker;
    private Button mButton1;
    private Button mButton2;
    private Button mButtonCancel;

    private int mHour = -1;
    private int mMinute = -1;

    public static final long DAY = 1000L * 60 * 60 * 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 獲取當前時間
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        if(mHour == -1 && mMinute == -1) {
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
        }

        mTimePicker = (TimePicker)findViewById(R.id.timePicker);
        mTimePicker.setCurrentHour(mHour);
        mTimePicker.setCurrentMinute(mMinute);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                mHour = hourOfDay;
                mMinute = minute;
            }
        });


        mButton1 = (Button)findViewById(R.id.normal_button);
        mButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                // 過20s 執行這個鬧鈴
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                calendar.add(Calendar.SECOND, 20);

                // 進行鬧鈴註冊
                AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

                Toast.makeText(MainActivity.this, "設定簡單鬧鈴成功!", Toast.LENGTH_LONG).show();
            }
        });

        mButton2 = (Button)findViewById(R.id.repeating_button);
        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                long firstTime = SystemClock.elapsedRealtime();	// 開機之後到現在的執行時間(包括睡眠時間)
                long systemTime = System.currentTimeMillis();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 這裡時區需要設定一下，不然會有8個小時的時間差
                calendar.set(Calendar.MINUTE, mMinute);
                calendar.set(Calendar.HOUR_OF_DAY, mHour);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                // 選擇的每天定時時間
                long selectTime = calendar.getTimeInMillis();

                // 如果當前時間大於設定的時間，那麼就從第二天的設定時間開始
                if(systemTime > selectTime) {
                    Toast.makeText(MainActivity.this, "設定的時間小於當前時間", Toast.LENGTH_SHORT).show();
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    selectTime = calendar.getTimeInMillis();
                }

                // 計算現在時間到設定時間的時間差
                long time = selectTime - systemTime;
                firstTime += time;

                // 進行鬧鈴註冊
                AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
                manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        firstTime, 10*1000, sender);

                Log.i(TAG, "time ==== " + time + ", selectTime ===== "
                        + selectTime + ", systemTime ==== " + systemTime + ", firstTime === " + firstTime);

                Toast.makeText(MainActivity.this, "設定重複鬧鈴成功! ", Toast.LENGTH_LONG).show();
            }
        });

        mButtonCancel = (Button)findViewById(R.id.cancel_button);
        mButtonCancel.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this,
                        0, intent, 0);
                Toast.makeText(MainActivity.this, "取消鬧鈴! ", Toast.LENGTH_LONG).show();
                // 取消鬧鈴
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                am.cancel(sender);
            }
        });
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.main, menu);
    //    return true;
    //}

}