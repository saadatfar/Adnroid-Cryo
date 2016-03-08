

package main.activity.lpg;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.cryo.DataProvider;
import com.Tirax.cryo.TextFont;
import com.example.cryo.*;


public class StopLPG extends Activity implements OnClickListener {
	
	public static int time;
	TextView time_text;
	public static boolean finished=true;
	
	private Handler timerHandler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop_lpg);
		
		setButtons();
		initialVars();
		
		timerHandler.postDelayed(TimeRunnable, 0);
		
		
	}

	private void initialVars() {
		finished=false;
		time_text = (TextView) findViewById(R.id.txt_time_stoplpg);
	}

	private void setButtons() {
		//declaring main menu buttons
		Button stop=(Button) findViewById(R.id.btn_stoplpg);
		Button back=(Button) findViewById(R.id.btn_back_stoplpg);
				
		//declaring onclicklistener functions
		stop.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		
		//starting corresponding intents
		if (arg0.getId()==R.id.btn_stoplpg || arg0.getId()==R.id.btn_back_stoplpg){
	        finishedLPG();        		
		}

				
	}

	private void finishedLPG() {
		DataProvider.deviceOff();
		Log.v("TIRAX", "finished lpg.");
		finished=true;
		StopLPG.this.finish();
	}
	
	 @Override  
	 public void onBackPressed() { 
		 finishedLPG();
	 }

	//Stop When Timer Finished
	Runnable TimeRunnable = new Runnable() {

        @Override
        public void run() {
        	if(time==0){
        		finishedLPG();
        		return;
        	}
        	time_text.setText(time+"'");
        	time--;
        	if(!finished)
        		timerHandler.postDelayed(TimeRunnable, 60000);
        }
    };

}
