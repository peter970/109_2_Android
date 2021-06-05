package com.example.a107360207_hw12_1;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "鬧鈴響了", Toast.LENGTH_LONG).show();

		NotificationHelper notificationHelper = new NotificationHelper(context);
		NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
		nb.setContentIntent(contentIntent);
		notificationHelper.getManager().notify(1, nb.build());

    }
	//NotificationHelper.class;
	public class NotificationHelper extends ContextWrapper {
		public static final String channelID = "channelID";
		public static final String channelName = "Channel Name";
		private  NotificationCompat.Builder mBuilder;
		private NotificationManager mManager;

		public NotificationHelper(Context base) {
			super(base);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				createChannel();
			}
		}

		@TargetApi(Build.VERSION_CODES.O)
		private void createChannel() {
			NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

			getManager().createNotificationChannel(channel);
		}

		public NotificationManager getManager() {
			if (mManager == null) {
				mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			}

			return mManager;
		}

		public NotificationCompat.Builder getChannelNotification() {
			return  mBuilder = new NotificationCompat.Builder(getApplicationContext(), channelID)
					.setContentTitle("時間到!")
					.setContentText("Times up!")
					.setSmallIcon(R.drawable.ic_launcher_background)
					.setOngoing(true);
		}

	}
}
