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


public class ContinuePulseActivity extends MyActivity implements OnClickListener {
	
	private boolean timeAutoIncrement = false;
	private boolean timeAutoDecrement = false;
	private boolean freqAutoIncrement = false;
	private boolean freqAutoDecrement = false;
	private Handler repeatUpdateHandler = new Handler();
	private TextView timeText;
	private TextView freqText;
	public int timeValue;
	public int freqValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cont_pulse);
		
				//declaring main menu buttons
				Button addTime=(Button) findViewById(R.id.btn_addTime);
				Button decTime=(Button) findViewById(R.id.btn_decTime);
				Button addFreq=(Button) findViewById(R.id.btn_addVacuum);
				Button decFreq=(Button) findViewById(R.id.btn_decVacuum);
				Button back = (Button) findViewById(R.id.btn_back);
				Button next = (Button) findViewById(R.id.btn_next);
				Button continus = (Button) findViewById(R.id.btn_cont);
				Button pulse = (Button) findViewById(R.id.btn_puls);

				
				
				
				timeText = (TextView) findViewById(R.id.txt_time);
				freqText = (TextView) findViewById(R.id.txt_vacuum);
				
				timeValue =10;
				freqValue =5;
				
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
								freqAutoIncrement = true;
								repeatUpdateHandler.post(new RptTimeUpdater());
								return false;
							}
						}
				);

				addFreq.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& freqAutoIncrement) {
							freqAutoIncrement = false;
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
								freqAutoDecrement = true;
								repeatUpdateHandler.post(new RptTimeUpdater());
								return false;
							}
						}
				);

				decFreq.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& freqAutoDecrement) {
							freqAutoDecrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							decrementTime();
						return false;
					}


				});
				back.setOnClickListener(this);
				next.setOnClickListener(this);
				pulse.setOnClickListener(this);
				continus.setOnClickListener(this);


		
	}
	public void decrement(){
		timeValue -=10;
		if(timeValue <0)
			timeValue =0;
		timeText.setText("" + timeValue);
	    
	}
	public void increment(){
		timeValue +=10;
		if(timeValue >100)
			timeValue =100;
		if(timeValue*freqValue>1000){
			timeValue -=10;
		}
	    timeText.setText("" + timeValue);
	}
	
	public void decrementTime(){
		freqValue-=1;
		if(freqValue <1)
			freqValue =1;
		freqText.setText("" + freqValue);
	    
	}
	public void incrementTime(){
		freqValue+=1;
		if(freqValue >10)
			freqValue =10;
		if(timeValue*freqValue>1000){
			freqValue +=1;
		}
		freqText.setText("" + freqValue);
	}
	@Override
	public void onClick(View arg0) {
		Intent int_next = new Intent(ContinuePulseActivity.this,StartActivity.class);
		
		if (arg0.getId()==R.id.btn_back){
			this.finish();
		}
		if (arg0.getId()==R.id.btn_next){
			if(!freqText.getText().equals("frq.") && !timeText.getText().equals("TIME")) {

				Values.pulseLength = this.timeValue;
				Values.pulseFrq = this.freqValue;
				Values.isPulse =true;
				startActivity(int_next);
			}
		}

		if (arg0.getId()==R.id.btn_cont){
			Values.isPulse = false;
			startActivity(int_next);
		}

		if (arg0.getId()==R.id.btn_puls){
			ImageView imgView = (ImageView)findViewById(R.id.hider);
			imgView.setVisibility(View.GONE);
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	class RptTimeUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if(freqAutoIncrement){
	        	
	            incrementTime();
	            repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
	        } else if(freqAutoDecrement){
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
