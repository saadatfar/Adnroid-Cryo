package main.activity;



import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Tirax.RF.*;
import com.Tirax.RF.Compiler;
import com.Tirax.RF.Dialogs.SummaryDialog;
import com.Tirax.RF.Enums.Types;
import com.example.cryo.*;


	public class StartActivity extends MyActivity implements OnClickListener {



	public static int power;
	public static int time;
	public static int frequency;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);



		declareButtons();


		Mode op = Manager.getType();
		TextView time_text = (TextView) findViewById(R.id.txt_time_start);
		time_text.setText(op.time + "'");
		StopActivity.time = op.time;
		TextView power_text = (TextView) findViewById(R.id.txt_power_start);
		power_text.setText(op.power + "%");
		TextView frequency = (TextView) findViewById(R.id.txt_start_freq);
		frequency.setText(op.frequency + "KHZ");
		TextView monobi = (TextView) findViewById(R.id.txt_start_monobi);
		monobi.setText(op.monobi);
		if(op.type == Types.CAVITATION)
		{
			findViewById(R.id.img_freq_hider).setVisibility(View.VISIBLE);
		}
		showSummary();

			
		
	}


		private void showSummary() {
			SummaryDialog s = new SummaryDialog(this,Manager.getType());
			s.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			s.show();


		}

		private void declareButtons() {
		//declaring main menu buttons
		Button auto=(Button) findViewById(R.id.btn_start);
		Button back=(Button) findViewById(R.id.btn_back_start);
		ImageButton summeryButton =(ImageButton) findViewById(R.id.btn_start_summary);
		ImageButton settingsBtn =(ImageButton) findViewById(R.id.btn_start_settings);

		//declaring onclicklistener functions
		settingsBtn.setOnClickListener(this);
		summeryButton.setOnClickListener(this);

		auto.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_start){
					sendStartCommunicationData();
					//declaring intents

					Intent int_stop=new Intent(StartActivity.this,StopActivity.class);
					startActivity(int_stop);
					this.finish();
				}
				
				if (arg0.getId()==R.id.btn_back_start){
					this.finish();
				}
		if (arg0.getId() == R.id.btn_start_summary) {
			showSummary();
		}
		if (arg0.getId() == R.id.btn_start_settings) {
			Intent int_settings = new Intent(StartActivity.this,EnterPassActivity.class);
			startActivity(int_settings);
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				
	}
	
	public void sendStartCommunicationData(){
		try{

			if(LogCatEnabler.startStop)
				Log.e("TIRAX3",">>>>>>>>>>>>>>> START <<<<<<<<<<<<<<<<<");
			Mode op = Manager.getType();

			Compiler.setRegisters(op);

		}
		catch(Exception ex){
			Log.e("EXCEPTION TIRAX",ex+"");
		}
	}

	

}
