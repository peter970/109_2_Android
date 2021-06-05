package com.android.example.notifyme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobService extends android.app.job.JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        try {
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(JobService.this)
                                    .setAutoCancel(true)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("我的app")
                                    .setContentText("又有新的内容上线了，快来我们app看看吧!");
                    Intent resultIntent = new Intent(JobService.this, MainActivity.class);

                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(JobService.this);
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());
        } catch (Exception ex) {
            //Log.e("Exception in NotificationService onStartJob");
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        //Log.d("NotificationService onStopJob");
        return true;
    }
}