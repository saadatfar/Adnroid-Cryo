package main.activity.lpg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.cryo.*;


public class Sport extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sport);
				//declaring main menu buttons
				Button mus=(Button) findViewById(R.id.btn_sport_musdinic);
				Button tend=(Button) findViewById(R.id.btn_sport_tendinic);
				Button back= (Button) findViewById(R.id.btn_sport_back);
				
				//declaring onclicklistener functions
				mus.setOnClickListener(this);
				tend.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_start=new Intent(Sport.this,StartLPG.class);
				

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_sport_musdinic){
					StartLPG.time=15;
					StartLPG.period = 0.75;
					startActivity(int_start);
				}
				
				if (arg0.getId()==R.id.btn_sport_tendinic){
					StartLPG.time=10;
					StartLPG.period = 0.7;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_sport_back){
					this.finish();
				}
				
	}
	

}
