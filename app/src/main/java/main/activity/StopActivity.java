package main.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.cryo.DataProvider;
import com.Tirax.cryo.SharedPrefrences;
import com.example.cryo.*;

public class StopActivity extends Activity implements OnClickListener {
	
	
	private Handler timerHandler = new Handler();
	private Handler UIHandler = new Handler();
	public boolean firstUI=false;
	
	public static int time=0;
	public static int firstTime;
	private TextView tempL;
	private TextView tempR;
	private TextView tempVaccum;
	private TextView pres1;
	private TextView timetext;
	private int backpressed;
	public boolean finished=true;
	public boolean first=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop);
		initialTextViews();
		finished=false;
		firstTime=time;
		backpressed=0;
		//declaring main menu buttons
	    Button stop=(Button) findViewById(R.id.btn_stop);
		stop.setOnClickListener(this);
				
		timerHandler.postDelayed(TimerRunnable, 0);
		UIHandler.postDelayed(UIreportsRunnable, 0);
				

	}



	private void initialTextViews() {
		//read temp datas and show
		pres1 = (TextView) findViewById(R.id.txt_press_stop2);
		timetext = (TextView) findViewById(R.id.txt_time_stop);
		tempL = (TextView) findViewById(R.id.txt_temp1_stop);
		tempR = (TextView) findViewById(R.id.txt_temp3_stop);
		tempVaccum = (TextView) findViewById(R.id.txt_temp2_stop);
	}


	
	@Override
	public void onClick(View arg0) {		
		//starting corresponding intents
		if (arg0.getId()==R.id.btn_stop){
			finishedCryo();
		}	
	}


	private void finishedCryo() {
		DataProvider.deviceOff();
		Log.e("TIRAX CRYO","stop activated");
		finished=true;
		this.finish();
	}
	
	 @Override  
	 public void onBackPressed() {  
		 backpressed++;
		 if(backpressed>3)
		 {
			 Intent int_temps=new Intent(StopActivity.this,ShowAllTemps.class);
			 startActivity(int_temps);
			 backpressed=0;
			 finished=true;
			 this.finish();
		 }
	 }
	
	//Stop When Timer Finished
	Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {
        	Log.e("TIRAX","*******TImer time"+time);
        	if(time==0){
        		finishedCryo();
        		return;
        	}
			SharedPrefrences.setTime(StopActivity.time);
			timetext = (TextView) findViewById(R.id.txt_time_stop);
        	timetext.setText(time+"'");
        	time--;
        	if(!finished)
        		timerHandler.postDelayed(TimerRunnable, 60000);
        }
    };
    Runnable UIreportsRunnable = new Runnable() {
		
		@Override
		public void run() {
			pres1.setText(DataProvider.getPresure()*25+"%");
			if(first){
				tempR.setText(" ");
				tempL.setText(" ");
				tempVaccum.setText(" ");
				first = false;
				if(!finished)
					UIHandler.postDelayed(UIreportsRunnable, 4000);
			}
			else {
				tempR.setText(DataProvider.getDisplayTemp(DataProvider.RIGHT_TEMP, firstTime - time) + "°");
				tempL.setText(DataProvider.getDisplayTemp(DataProvider.LEFT_TEMP, firstTime - time) + "\u00b0");
				tempVaccum.setText(DataProvider.getDisplayTemp(DataProvider.VACCUM_TEMP, firstTime - time) + "\u00b0");
				if(!finished)
					UIHandler.postDelayed(UIreportsRunnable, 300);
			}

		}
	};
	
	


}
