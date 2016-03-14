package com.Tirax.cryo;

import main.activity.MainActivity;
import main.activity.StopActivity;
import main.activity.lpg.StopLPG;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class ResetTask{
	

	public static Activity mainActivity;


	public static void resetSystem() {
		try{
			Intent mStartActivity = new Intent(mainActivity, MainActivity.class);
			Log.e("RESET", "in reset");
			SerialPort.turnOff();

			SharedPrefrences.setReseted(true);
			SharedPrefrences.setRegisters(DataProvider.getAllRegisters());
			android.os.SystemClock.sleep(700);

			int mPendingIntentId = 123456;
			PendingIntent mPendingIntent = PendingIntent.getActivity(mainActivity, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager mgr = (AlarmManager)mainActivity.getSystemService(Context.ALARM_SERVICE);
			mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);

			Log.e("TIRAX", "Log set" + SharedPrefrences.getReseted());
			System.exit(0);
		}
		catch(Exception ex){
			Log.e("TIRAX ERROR","i want reset");
		}

		
	}
}