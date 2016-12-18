package main.activity;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.Tirax.RF.Dialogs.ErrorDialog;
import com.Tirax.RF.LogCatEnabler;
import com.Tirax.RF.LogCatSaver;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Manager;
import com.Tirax.RF.ResetTask;
import com.Tirax.RF.SerialPortsHardware.SerialPort;
import com.Tirax.RF.SharedPrefrences;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.R;

public class MainActivity  extends Activity    implements OnClickListener{

	 
	 int backpressed=0;
	private Handler DisplayHandler=new Handler();
	public static Activity nowActivity;


	@SuppressLint("NewApi")
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.auto_manual);
			nowActivity=this;

	}

	 @Override
	 protected void onResume(){
		 super.onResume();
		 try {
			 killUI();
			 assignKeys();


			 //set initial activity
			 ResetTask.mainActivity = this;
			 SharedPrefrences.mainActivity =this;


			 //run serial port
			 SerialPort.turnOn();
			 new DataProvider().execute();

			 goToCorrectPage();
			 DisplayHandler.postDelayed(ErrorRunnable, 200);

			 //logFileWarning();

		 }catch(Exception ex){
			 Log.e("TIRAX ERROR","error accured"+ex);
		 }
	 }

	private void logFileWarning() {
		if(LogCatEnabler.filesize)
			Log.e("TIRAX","log file size: "+LogCatSaver.getVolume());
		long max_volum = 4*1000*1000*1000;
		if(LogCatSaver.getVolume()>1000000){
			ErrorDialog dlgAlertError = new ErrorDialog(MainActivity.this);
			dlgAlertError.show();
			dlgAlertError.textDia.setText("Please Call Support");

		}
	}

	private void goToCorrectPage() {
		//TODO fixit
		android.os.SystemClock.sleep(100);

		if(DataProvider.isDeviceOn()) {

				StopActivity.time = SharedPrefrences.getTime();
				Intent int_auto = new Intent(MainActivity.this, StopActivity.class);
				startActivity(int_auto);
		}
	}

	private void assignKeys() {
			backpressed=0;
			//declaring main menu buttons
			Button auto=(Button) findViewById(R.id.btn_auto);
			Button manual=(Button) findViewById(R.id.btn_manual);
			Button setting=(Button) findViewById(R.id.btn_settings);


			//declaring onclicklistener functions
			auto.setOnClickListener(this);
			manual.setOnClickListener(this);
			setting.setOnClickListener(this);

		}
		private void killUI() {
			//disable button menu
			Log.w("Main"," kill task bar");
	        if (Build.VERSION.SDK_INT < 16) {
	            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        }
            Process proc = null;

            String ProcID = "79"; //honeycomb and older

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
                ProcID = "42"; //ics and newer
            }

            try {
                proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "service call activity "+ProcID+" s16 com.android.systemui" });
            } catch (Exception e) {
                Log.w("Main","Failed to kill task bar (1).");
                e.printStackTrace();
            }
            try {
                proc.waitFor();
            } catch (Exception e) {
                Log.w("Main","Failed to kill task bar (2).");
                e.printStackTrace();
            }
			
		 }
		
		 @Override  
		 public void onBackPressed() {  
			 //disable button back
			 
			 backpressed++;
			 if(backpressed>3)
			 {
				 startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
				 backpressed=0;
			 }
		 }
		 
		@Override
		public void onClick(View arg0) {


			//starting corresponding intents
			if (arg0.getId()==R.id.btn_auto){
				Pages.auto_manual = Pages.AUTO;
				Intent int_auto=new Intent(MainActivity.this, Manager.getNextPage(MainActivity.class));
				startActivity(int_auto);
			}

			if (arg0.getId()==R.id.btn_manual){
				Pages.auto_manual = Pages.MANUAL;
				Intent int_manual=new Intent(MainActivity.this,Manager.getNextPage(MainActivity.class));
				startActivity(int_manual);
			}

			if (arg0.getId()==R.id.btn_settings){
				Intent int_settings = new Intent(MainActivity.this,EnterPassActivity.class);
				startActivity(int_settings);
			}

			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			
		}


		Runnable ErrorRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				if (DataProvider.isNotInError() && DataProvider.isError()) {
					if(LogCatEnabler.errorHappened)
						Log.e("TIRAX", "i am in error micro");
					ErrorDialog dlgAlertError = new ErrorDialog(nowActivity);
					//dlgAlertError.show();
					//dlgAlertError.textDia.setText("it is a bad error");

				}
			}catch(Exception ex){
				Log.e("TIRAX Error", "error showing error");
			}


			DisplayHandler.postDelayed(ErrorRunnable, 1000);


		}

	};

}