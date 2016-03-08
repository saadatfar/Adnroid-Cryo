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
	
	public Activity mainActivity;
	public ResetTask(Activity n) {
		mainActivity =n;
	}
	public void run() {
			if(MainActivity.rst){
				resetSystem();
			}
		
	}

	public void resetSystem() {
		try{
			Intent mStartActivity = new Intent(mainActivity, MainActivity.class);
			Log.e("RESET","in reset");
			StopLPG.finished=true;
			StopActivity.finished=true; 
			SerialPort.turnOff();
			android.os.SystemClock.sleep(600);
			int mPendingIntentId = 123456;
			PendingIntent mPendingIntent = PendingIntent.getActivity(mainActivity, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager mgr = (AlarmManager)mainActivity.getSystemService(Context.ALARM_SERVICE);
			mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
			MainActivity.rst=false;
			System.exit(0);
		}
		catch(Exception ex){
			Log.e("IAM HERE","i am here now");
		}

		
	}
}