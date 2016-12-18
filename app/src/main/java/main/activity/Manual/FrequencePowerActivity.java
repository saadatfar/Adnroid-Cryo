package main.activity.Manual;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Tirax.RF.MyActivity;

import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;


public class FrequencePowerActivity extends MyActivity implements OnClickListener {
	
	private boolean powerAutoIncrement = false;
	private boolean powerAutoDecrement = false;
	private boolean freqAutoIncrement = false;
	private boolean freqAutoDecrement = false;
	private boolean timeAutoIncrement = false;
	private boolean timeAutoDecrement = false;
	private Handler repeatUpdateHandler = new Handler();
	private TextView powerText ;
	private TextView freqText;
	private TextView timeText;
	public int powerValue;
	public int freqValue;
	public int timeValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freq_power);
		
				//declaring main menu buttons
				Button addPower=(Button) findViewById(R.id.btn_addPower);
				Button decPower=(Button) findViewById(R.id.btn_decPower);
				ImageButton addFreq=(ImageButton) findViewById(R.id.btn_addVacuum);
				ImageButton decFreq=(ImageButton) findViewById(R.id.btn_decVacuum);
				Button addTime=(Button) findViewById(R.id.btn_addTime);
				Button decTime=(Button) findViewById(R.id.btn_decTime);
				Button back = (Button) findViewById(R.id.btn_back_manualseting);
				Button next = (Button) findViewById(R.id.btn_next_manualseting);
				
				
				
				powerText = (TextView) findViewById(R.id.txt_power);
				freqText = (TextView) findViewById(R.id.txt_vacuum);
				timeText = (TextView) findViewById(R.id.txt_time);

				powerValue =0;
				freqValue =500;
				timeValue=10;

				//time increase
				addTime.setOnLongClickListener(
						new View.OnLongClickListener(){
							public boolean onLongClick(View arg0) {
								timeAutoIncrement = true;
								repeatUpdateHandler.post( new RptTimeUpdater() );
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
							incrementTime();
						return false;
					}


				});

				//time decrease
				decTime.setOnLongClickListener(
						new View.OnLongClickListener(){
							public boolean onLongClick(View arg0) {
								timeAutoDecrement = true;
								repeatUpdateHandler.post( new RptTimeUpdater() );
								return false;
							}
						}
				);

				decTime.setOnTouchListener( new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL)
								&& timeAutoDecrement ){
							timeAutoDecrement = false;
						}
						else if(event.getAction()==MotionEvent.ACTION_DOWN)
							decrementTime();
						return false;
					}


				});
				//temp increase
				addPower.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								powerAutoIncrement = true;
								repeatUpdateHandler.post(new RptPowerUpdater());
								return false;
							}
						}
				);

				addPower.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& powerAutoIncrement) {
							powerAutoIncrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							increment();
						return false;
					}


				});


				//temp deccrease
				decPower.setOnLongClickListener(
						new View.OnLongClickListener() {
							public boolean onLongClick(View arg0) {
								powerAutoDecrement = true;
								repeatUpdateHandler.post(new RptPowerUpdater());
								return false;
							}
						}
				);

				decPower.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
								&& powerAutoDecrement) {
							powerAutoDecrement = false;
						} else if (event.getAction() == MotionEvent.ACTION_DOWN)
							decrement();
						return false;
					}


				});
				//time decrease
				if(Pages.bi_mono != Pages.BIPOLAR_HIGH) {
					addFreq.setOnLongClickListener(
							new View.OnLongClickListener() {
								public boolean onLongClick(View arg0) {
									freqAutoIncrement = true;
									repeatUpdateHandler.post(new RptFreqUpdater());
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
								incrementFreq();
							return false;
						}


					});
					decFreq.setOnLongClickListener(
							new View.OnLongClickListener() {
								public boolean onLongClick(View arg0) {
									freqAutoDecrement = true;
									repeatUpdateHandler.post(new RptFreqUpdater());
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
								decrementFreq();
							return false;
						}


					});
				}else
				{
					freqValue = 4000;
					freqText.setText("" + freqValue);
					addFreq.setBackgroundResource(R.drawable.hider);
					decFreq.setBackgroundResource(R.drawable.hider);

				}
				back.setOnClickListener(this);
				next.setOnClickListener(this);


		
	}
	public void decrement(){
		powerValue-=10;
		if(powerValue <0)
			powerValue =0;
		powerText.setText("" + powerValue);
	    
	}

	public void increment(){
		powerValue+=10;
		if(powerValue >100)
			powerValue =100;
	    powerText.setText("" + powerValue);
	}
	
	public void decrementFreq(){
		freqValue-=50;
		if(freqValue <300)
			freqValue =300;
		freqText.setText("" + freqValue);
	    
	}
	public void incrementFreq(){
		freqValue+=50;
		if(freqValue >700)
			freqValue =700;
		freqText.setText("" + freqValue);
	}

	public void decrementTime(){
		timeValue--;
		if(timeValue<0)
			timeValue=0;
		timeText.setText( ""+timeValue );

	}
	public void incrementTime(){
		timeValue++;
		if(timeValue>120)
			timeValue=120;
		timeText.setText( ""+timeValue );
	}
	@Override
	public void onClick(View arg0) {
		Intent int_next = new Intent(FrequencePowerActivity.this,ContinuePulseActivity.class);
		
		if (arg0.getId()==R.id.btn_back_manualseting){
			this.finish();
		}
		if (arg0.getId()==R.id.btn_next_manualseting){
			if(!freqText.getText().equals("FRQ.") && !powerText.getText().equals("POWER") && !freqText.getText().equals("TIME"))
			{
				Values.time = this.timeValue;
				Values.frequency = this.freqValue;
				Values.power = this.powerValue;

				startActivity(int_next);
			}
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				
	}
	
	class RptFreqUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if(freqAutoIncrement){
	        	
	            incrementFreq();
	            repeatUpdateHandler.postDelayed( new RptFreqUpdater(), REP_DELAY );
	        } else if(freqAutoDecrement) {
				decrementFreq();
				repeatUpdateHandler.postDelayed(new RptFreqUpdater(), REP_DELAY);
			}
	    }
	}
	
	class RptPowerUpdater implements Runnable {
	    private static final long REP_DELAY = 35;

		public void run() {
	        if(powerAutoIncrement){
	        	
	            increment();
	            repeatUpdateHandler.postDelayed( new RptPowerUpdater(), REP_DELAY );
	        } else if(  powerAutoDecrement ){
	            decrement();
	            repeatUpdateHandler.postDelayed( new RptPowerUpdater(), REP_DELAY );
	        }
	    }
	}


	class RptTimeUpdater implements Runnable {
		private static final long REP_DELAY = 5;

		public void run() {
			if(timeAutoIncrement){

				incrementTime();
				repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
			} else if(timeAutoDecrement) {
				decrementTime();
				repeatUpdateHandler.postDelayed(new RptTimeUpdater(), REP_DELAY);
			}
		}
	}
}
