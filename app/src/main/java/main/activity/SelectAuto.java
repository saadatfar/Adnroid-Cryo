package main.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.cryo.DataProvider;
import com.example.cryo.*;


public class SelectAuto extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto_manual);
				//declaring main menu buttons
				Button auto=(Button) findViewById(R.id.btn_auto);
				Button manual=(Button) findViewById(R.id.btn_manual);
				Button back=(Button) findViewById(R.id.btn_back_auto_manual);
				
				//declaring onclicklistener functions
				auto.setOnClickListener(this);
				manual.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
	
		
				//declaring intents
				Intent int_auto=new Intent(SelectAuto.this,SelectSex.class);
				Intent int_manual=new Intent(SelectAuto.this,ManualSettingsActivity.class);

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_auto){
					DataProvider.setTempRefrence(0, (char) 20);
					DataProvider.setTempRefrence(1,(char)20);
					DataProvider.setTempRefrence(2,(char)20);
					DataProvider.setTempRefrence(3,(char)20);
					StartActivity.temp = -10;
					startActivity(int_auto);
				}
				
				if (arg0.getId()==R.id.btn_manual){
					startActivity(int_manual);
				}
				if (arg0.getId()==R.id.btn_back_auto_manual){

					this.finish();
				}
				
	}
	

}
