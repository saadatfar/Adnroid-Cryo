package main.activity.Manual;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;

import main.activity.StartActivity;


public class PowerTimeActivity extends MyActivity implements OnClickListener {
	
	private boolean timeAutoIncrement = false;
	private boolean timeAutoDecrement = false;
	private boolean powerAutoIncrement = false;
	private boolean powerAutoDecrement = false;
	private Handler repeatUpdateHandler = new Handler();
	private TextView timeText;
	private TextView powerText;
	public int timeValue;
	public int powerValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manual_power_time);
		
				//declaring main menu buttons
				Button addTime=(Button) findViewById(R.id.btn_addTime);
				Button decTime=(Button) findViewById(R.id.btn_decTime);
				Button addFreq=(Button) findViewById(R.id.btn_addPower);
				Button decFreq=(Button) findViewById(R.id.btn_decPower);
				Button back = (Button) findViewById(R.id.btn_back_manualseting);
				Button next = (Button) findViewById(R.id.btn_next_manualseting);

				
				timeText = (TextView) findViewById(R.id.txt_time);
				powerText = (TextView) findViewById(R.id.txt_power);
				
				timeValue =30;
				powerValue =100;
				
				//temp increase
				addTime.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								timeAutoIncrement = true;
								repeatUpdateHandler.post(new RptTempUpdater());
								return false;
							}
						}
				);

				addTime.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& timeAutoIncrement) {
							timeAutoIncrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							increment();
						return false;
					}


				});
				//time increase
				addFreq.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								powerAutoIncrement = true;
								repeatUpdateHandler.post(new RptTimeUpdater());
								return false;
							}
						}
				);

				addFreq.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& powerAutoIncrement) {
							powerAutoIncrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							incrementTime();
						return false;
					}


				});

				//temp deccrease
				decTime.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								timeAutoDecrement = true;
								repeatUpdateHandler.post(new RptTempUpdater());
								return false;
							}
						}
				);

				decTime.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& timeAutoDecrement) {
							timeAutoDecrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							decrement();
						return false;
					}


				});
				//time decrease
				decFreq.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								powerAutoDecrement = true;
								repeatUpdateHandler.post(new RptTimeUpdater());
								return false;
							}
						}
				);

				decFreq.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& powerAutoDecrement) {
							powerAutoDecrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							decrementTime();
						return false;
					}


				});
				back.setOnClickListener(this);
				next.setOnClickListener(this);


		
	}
	public void decrement(){
		timeValue -=5;
		if(timeValue <0)
			timeValue =0;
		timeText.setText("" + timeValue);
	    
	}
	public void increment(){
		timeValue +=5;
		if(timeValue >100)
			timeValue =100;
	    timeText.setText("" + timeValue);
	}
	
	public void decrementTime(){
		powerValue -=50;
		if(powerValue <0)
			powerValue =0;
		powerText.setText("" + powerValue);
	    
	}
	public void incrementTime(){
		powerValue +=50;
		if(powerValue >100)
			powerValue =100;
		powerText.setText("" + powerValue);
	}
	@Override
	public void onClick(View arg0) {
		Intent int_next = new Intent(PowerTimeActivity.this,StartActivity.class);
		
		if (arg0.getId()==R.id.btn_back_manualseting){
			this.finish();
		}
		if (arg0.getId()==R.id.btn_next_manualseting){
			if(!powerText.getText().equals("POWER") && !timeText.getText().equals("TIME")) {

				Values.power = powerValue;
				Values.time = timeValue;
				startActivity(int_next);
			}
		}

		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	class RptTimeUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if(powerAutoIncrement){
	        	
	            incrementTime();
	            repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
	        } else if(powerAutoDecrement){
	            decrementTime();
	            repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
	        }
	    }
	}
	
	class RptTempUpdater implements Runnable {
	    private static final long REP_DELAY = 35;

		public void run() {
	        if(timeAutoIncrement){
	        	
	            increment();
	            repeatUpdateHandler.postDelayed( new RptTempUpdater(), REP_DELAY );
	        } else if(timeAutoDecrement){
	            decrement();
	            repeatUpdateHandler.postDelayed( new RptTempUpdater(), REP_DELAY );
	        }
	    }
	}
}
