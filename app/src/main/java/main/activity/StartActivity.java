package main.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.cryo.DataProvider;
import com.example.cryo.*;


public class StartActivity extends Activity implements OnClickListener {
	
	public static int temp;
	public static int time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		declareButtons();
				
		TextView time_text = (TextView) findViewById(R.id.txt_time_start);
		time_text.setText(time+"'");
		TextView temp_text = (TextView) findViewById(R.id.txt_temp_start);
		temp_text.setText(temp+"\u00b0");
			
		
	}

	private void declareButtons() {
		//declaring main menu buttons
		Button auto=(Button) findViewById(R.id.btn_start);
		Button back=(Button) findViewById(R.id.btn_back_start);
		
		//declaring onclicklistener functions
		auto.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		
				//declaring intents
				Intent int_stop=new Intent(StartActivity.this,StopActivity.class);

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_start){
					StopActivity.time = time;
					sendStartCommunicationData();
					startActivity(int_stop);
					this.finish();
				}
				
				if (arg0.getId()==R.id.btn_back_start){
					Log.e("DEBUG","finished");
					this.finish();
				}
				
	}
	
	public void sendStartCommunicationData(){
				try{
					DataProvider.setStartPresure();
					DataProvider.setCryo();
					DataProvider.deviceOn();

				}
				catch(Exception ex){
					Log.e("EXCEPTION TIRAX",ex+"");
				}
			
	}

	

}
