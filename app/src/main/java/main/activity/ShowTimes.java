package main.activity;


import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.SharedPrefrences;
import com.example.cryo.R;

public class ShowTimes extends MyActivity implements View.OnClickListener {
    int backpressed=0;
	private Handler UIHandler = new Handler();
	public static boolean finished=true;
	@Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.time_log);
         finished=false;
         UIHandler.postDelayed(UIreportsRunnable, 0);
         
         
		Button back = (Button) findViewById(R.id.btn_back_times_work);
		back.setOnClickListener(this);
         
	}
	 @Override  
	 public void onBackPressed() { 
		 finished=true;
		 //SerialPort.sendAndWait("PWOF");
		 this.finish();
	 }
	 Runnable UIreportsRunnable = new Runnable() {
			
			@Override
			public void run() {
		         TextView tv = (TextView)findViewById(R.id.txt_time);
		         StringBuilder log=new StringBuilder();
		         

		         tv.setMovementMethod(new ScrollingMovementMethod());
				int t = SharedPrefrences.getTimeOfWorks();
		         tv.setText("time: "+t);
				if(!finished)
						UIHandler.postDelayed(UIreportsRunnable, 300);
			}
		};

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_back_times_work)
		{
			this.finish();
		}
	}
}
