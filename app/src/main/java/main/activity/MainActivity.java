package main.activity;


import main.activity.lpg.LPGMain;
import main.activity.lpg.StopLPG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.Tirax.cryo.DataProvider;
import com.Tirax.cryo.ResetTask;
import com.Tirax.cryo.SerialPort;
import com.Tirax.cryo.SharedPrefrences;
import com.example.cryo.R;

public class MainActivity  extends Activity implements OnClickListener{

	 
	 int backpressed=0;
	 
	 
	 
		@SuppressLint("NewApi")
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main_page);
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

			 Log.e("TIRAX", "Log set" + SharedPrefrences.getReseted());
			 //run serial port
			 SerialPort.turnOn();
			 new DataProvider().execute();

			 goToCorrectPage();

		 }catch(Exception ex){
			 Log.e("TIRAX ERROR","error accured"+ex);
		 }
	 }

	private void goToCorrectPage() {
		android.os.SystemClock.sleep(100);

		if(DataProvider.isDeviceOn()) {
			if(DataProvider.getCryo()) {
				StopActivity.time = SharedPrefrences.getTime();
				Intent int_auto = new Intent(MainActivity.this, StopActivity.class);
				startActivity(int_auto);

			}else{
				StopLPG.time = SharedPrefrences.getTime();
				Intent int_lpg = new Intent(MainActivity.this, StopLPG.class);
				startActivity(int_lpg);

			}
		}
	}

	private void assignKeys() {
			backpressed=0;
            Button am = (Button) findViewById(R.id.btn_cryo);
            am.setOnClickListener(this);
            
            Button lpg = (Button) findViewById(R.id.btn_lpg);
            lpg.setOnClickListener(this);
		}
		private void killUI() {
			//disable button menu
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
			
			Intent int_auto=new Intent(MainActivity.this,SelectAuto.class);
			Intent int_lpg=new Intent(MainActivity.this,LPGMain.class);
			if (arg0.getId()==R.id.btn_cryo){
				startActivity(int_auto);
				
			}
			
			if (arg0.getId()==R.id.btn_lpg){
				startActivity(int_lpg);
				
			}

			
		}

}