package main.activity;



import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Tirax.RF.*;
import com.Tirax.RF.Compiler;
import com.Tirax.RF.Dialogs.SummaryDialog;
import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.SharedPrefrences;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.*;
import com.friendlyarm.AndroidSDK.HardwareControler;

public class StopActivity extends MyActivity implements OnClickListener {
	
	
	private Handler timerHandler = new Handler();
	private Handler UIHandler = new Handler();
	public boolean firstUI=false;
	
	public static int time=10;
	public static int firstTime;

	private TextView power;
	private TextView timetext;
	private TextView vacuum;

	private int backpressed;
	public boolean finished=true;
	public boolean first=true;
	private boolean mute = false;
	private boolean pause = false;
	private ImageButton soundButton;
	private ImageButton stopButton;

	private boolean powerAutoIncrement = false;
	private boolean powerAutoDecrement = false;
	private boolean vacuumAutoIncrement = false;
	private boolean vacuumAutoDecrement = false;
	private int powerStep = 10;
	private Handler repeatUpdateHandler = new Handler();
	public int powerValue;
	public int vacuumValue;

	private Mode op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop);
		initialTextViews();
		finished=false;
		firstTime=time;
		backpressed=0;
		//declaring main menu buttons
		setButtons();

				
		timerHandler.postDelayed(TimerRunnable, 0);
		UIHandler.postDelayed(UIreportsRunnable, 0);



	}

	private void setButtons() {
		stopButton =(ImageButton) findViewById(R.id.btn_stop);
		stopButton.setOnClickListener(this);

		soundButton =(ImageButton) findViewById(R.id.btn_stop_sound);
		soundButton.setOnClickListener(this);

		ImageButton summeryButton =(ImageButton) findViewById(R.id.btn_summary);
		summeryButton.setOnClickListener(this);

		ImageButton settingsBtn =(ImageButton) findViewById(R.id.btn_stop_settings);
		settingsBtn.setOnClickListener(this);
	}


	private void initialTextViews() {
		//read temp datas and show
		power = (TextView) findViewById(R.id.txt_stop_power);
		timetext = (TextView) findViewById(R.id.txt_time_stop);
		vacuum = (TextView) findViewById(R.id.txt_stop_vacuum);

		op = Manager.getType();

		TextView frequece = (TextView) findViewById(R.id.txt_stop_freq);
		frequece.setText(op.frequency + "KHz");
		TextView monobi = (TextView)findViewById(R.id.txt_stop_monobi);
		monobi.setText(op.monobi + "");
		power.setText(op.power + "%");

		powerValue=op.power;
		vacuumValue = op.vacuumLevel;
		if(op.type ==Types.CAVITATION) powerStep = 50;

		activePowerButtons();
		activeVaccumButtons();

		if(op.type == Types.CAVITATION)
		{
			findViewById(R.id.img_freq_hider).setVisibility(View.VISIBLE);
		}



	}

	private void activeVaccumButtons() {
		if(Pages.bi_mono == Pages.VACUUM)
		{


			ImageButton addVacuum = (ImageButton) findViewById(R.id.btn_stop_vacuumup);
			ImageButton decVacuum = (ImageButton) findViewById(R.id.btn_stop_vacuumdown);

			addVacuum.setBackground(null);
			decVacuum.setBackground(null);

			addVacuum.setOnLongClickListener(
					new View.OnLongClickListener() {
						public boolean onLongClick(View arg0) {
							vacuumAutoIncrement = true;
							repeatUpdateHandler.post(new RptPowerUpdater());
							return false;
						}
					}
			);

			addVacuum.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
							&& vacuumAutoIncrement) {
						vacuumAutoIncrement = false;
					} else if (event.getAction() == MotionEvent.ACTION_DOWN)
						vacuumIncrement();
					return false;
				}


			});


			//power deccrease
			decVacuum.setOnLongClickListener(
					new View.OnLongClickListener() {
						public boolean onLongClick(View arg0) {
							vacuumAutoDecrement = true;
							repeatUpdateHandler.post(new RptPowerUpdater());
							return false;
						}
					}
			);

			decVacuum.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
							&& vacuumAutoDecrement) {
						vacuumAutoDecrement = false;
					} else if (event.getAction() == MotionEvent.ACTION_DOWN)
						vacuumDecrement();
					return false;
				}


			});
		}
	}

	private void vacuumIncrement() {
		vacuumValue+=10;
		if(vacuumValue >100)
			vacuumValue =100;
		vacuum.setText("" + vacuumValue+"%");
		DataProvider.setRegister(DataProvider.RVCLV, (char) vacuumValue);
	}
	public void vacuumDecrement(){
		vacuumValue-=10;
		if(vacuumValue <0)
			vacuumValue =0;
		vacuum.setText("" + vacuumValue+"%");
			DataProvider.setRegister(DataProvider.RVCLV, (char) vacuumValue);

	}



	@Override
	public void onClick(View arg0) {
		//starting corresponding intents
		if (arg0.getId() == R.id.btn_stop) {
			finishFunction();
		}
		if (arg0.getId() == R.id.btn_stop_sound) {
			mute = !mute;
			if (mute) {
				soundButton.setBackgroundResource(R.drawable.nosound);
			} else {
				soundButton.setBackgroundResource(R.drawable.sound);
			}
		}
		if (arg0.getId() == R.id.btn_summary) {
			showSummary();
		}
		if (arg0.getId() == R.id.btn_stop_settings) {
			Intent int_settings = new Intent(StopActivity.this,EnterPassActivity.class);
			startActivity(int_settings);
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


	}

	private void showSummary() {
		SummaryDialog s = new SummaryDialog(this,op);
		s.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		s.show();
	}

	public void activePowerButtons(){



		ImageButton addPower = (ImageButton) findViewById(R.id.btn_stop_powerup);
		ImageButton decPower = (ImageButton) findViewById(R.id.btn_stop_powerdown);

		addPower.setBackground(null);
		decPower.setBackground(null);

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
					powerIncrement();
				return false;
			}


		});


		//power deccrease
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
					powerDecrement();
				return false;
			}


		});
	}

	public void powerDecrement(){
		powerValue-=powerStep;
		if(powerValue <10)
			powerValue += powerStep;
		power.setText("" + powerValue + "%");

		if(op.type ==  Types.LF && op.monobi.equals("BLF"))
			DataProvider.setRegister(DataProvider.RPWR, (char) (powerValue*0.4));
		else
			DataProvider.setRegister(DataProvider.RPWR, (char) powerValue);

	}
	public void powerIncrement(){
		powerValue+=powerStep;
		if(powerValue >100)
			powerValue =100;
		power.setText("" + powerValue + "%");

		if(op.type ==  Types.LF && op.monobi.equals("BLF"))
			DataProvider.setRegister(DataProvider.RPWR, (char) (powerValue*0.4));
		else
			DataProvider.setRegister(DataProvider.RPWR, (char) powerValue);
	}

	private void finishFunction() {

		if(LogCatEnabler.startStop)
			Log.e("TIRAX3",">>>>>>>>>>>>>>> STOP <<<<<<<<<<<<<<<<<");

		finished=true;
		DataProvider.setRegister(DataProvider.RTYP0, (char) 0);

		SharedPrefrences.setTimeOfWorks(SharedPrefrences.getTimeOfWorks() + firstTime - time);

		this.finish();
	}
	
	 @Override  
	 public void onBackPressed() {  
		 backpressed++;
		 if(backpressed>3)
		 {
			 Intent int_temps=new Intent(StopActivity.this,ShowTimes.class);
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

        	if(time==0){
				playEndMusic();
        		finishFunction();
        		return;
        	}
			SharedPrefrences.setTime(StopActivity.time);
			timetext = (TextView) findViewById(R.id.txt_time_stop);
        	timetext.setText(time+"'");
			if(!pause)
        		time--;
        	if(!finished)
        		timerHandler.postDelayed(TimerRunnable, 60000);
        }
    };

	private void playEndMusic() {
		if(!mute && !finished) {
			for (int i = 0; i < 2; i++) {
				HardwareControler.PWMPlay(2000);
				android.os.SystemClock.sleep(1000);
				HardwareControler.PWMStop();

			}
		}

	}

	class RptPowerUpdater implements Runnable {
		private static final long REP_DELAY = 35;

		public void run() {
			if(powerAutoIncrement){

				powerIncrement();
				repeatUpdateHandler.postDelayed( new RptPowerUpdater(), REP_DELAY );
			} else if(  powerAutoDecrement ){
				powerDecrement();
				repeatUpdateHandler.postDelayed( new RptPowerUpdater(), REP_DELAY );
			}
		}
	}
	class RptVacuumUpdater implements Runnable {
		private static final long REP_DELAY = 35;

		public void run() {
			if(vacuumAutoIncrement){

				vacuumIncrement();
				repeatUpdateHandler.postDelayed( new RptVacuumUpdater(), REP_DELAY );
			} else if(  powerAutoDecrement ){
				vacuumDecrement();
				repeatUpdateHandler.postDelayed( new RptVacuumUpdater(), REP_DELAY );
			}
		}
	}
	private int inrunnable=0;
	Runnable UIreportsRunnable = new Runnable() {
		
		@Override
		public void run() {


/*
			HardwareControler.PWMStop();
			inrunnable++;


			if(inrunnable>60 && (DataProvider.getRegister(DataProvider.RCRN)>135 ||
					DataProvider.getRegister((char)(DataProvider.RCRN+1))>128))
			{
				if(!mute) {
					HardwareControler.PWMPlay(2000);
					inrunnable = 0;
				}

			}*/

			//Log.e("TIRAX","pause activated Values value: "+(int)DataProvider.getRegister(DataProvider.RMKY_PAUSE));
			if(DataProvider.getBit(DataProvider.RMKY, DataProvider.RMKY_PAUSE)) {

				if (Values.PAUSE == 0) {
					if (pause == false) {

						stopButton.setBackgroundResource(R.drawable.pause);
						pause = true;
						if(LogCatEnabler.startStop)
							Log.e("TIRAX3",">>>>>>>>>>>>>>> PAUSE <<<<<<<<<<<<<<<<<");
						DataProvider.setRegister(DataProvider.RTYP0, (char) 0);


					}else {

							stopButton.setBackgroundResource(R.drawable.stopbut);
							pause = false;
							Compiler.setRTYPRegister(op);

					}
				}
				Values.PAUSE=1;
			}else{
				Values.PAUSE=0;
			}
			if(!finished)
				UIHandler.postDelayed(UIreportsRunnable, 1);
		}

	};
	
	


}
