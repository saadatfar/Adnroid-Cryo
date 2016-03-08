package main.activity.lpg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.cryo.*;


public class Resurfacing extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resurfacing);
				//declaring main menu buttons
				Button body=(Button) findViewById(R.id.btn_resurfacing_body);
				Button face=(Button) findViewById(R.id.btn_resurfacing_face);
				Button preorbital=(Button) findViewById(R.id.btn_resurfacing_preorbital);
				Button back= (Button) findViewById(R.id.btn_resurfacing_back);
				
				//declaring onclicklistener functions
				body.setOnClickListener(this);
				face.setOnClickListener(this);
				preorbital.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_start=new Intent(Resurfacing.this,StartLPG.class);
				

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_resurfacing_body){
					StartLPG.time=25;
					StartLPG.period = 10000;
					startActivity(int_start);
				}
				
				if (arg0.getId()==R.id.btn_resurfacing_face){
					StartLPG.time=15;
					StartLPG.period = 10000;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_resurfacing_preorbital){
					StartLPG.time=1;
					StartLPG.period = 10000;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_resurfacing_back){
					this.finish();
				}
				
	}
	

}
