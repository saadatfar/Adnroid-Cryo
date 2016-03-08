package main.activity.lpg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.cryo.*;


public class Cellulit extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cellulit);
				//declaring main menu buttons
				Button odm=(Button) findViewById(R.id.btn_cellulit_oedeme);
				Button soft=(Button) findViewById(R.id.btn_cellulit_soft);
				Button tonic=(Button) findViewById(R.id.btn_cellulit_tonic);
				Button doubleSuc=(Button) findViewById(R.id.btn_cellulit_double);
				Button back= (Button) findViewById(R.id.btn_cellulit_back);
				
				//declaring onclicklistener functions
				odm.setOnClickListener(this);
				soft.setOnClickListener(this);
				tonic.setOnClickListener(this);
				doubleSuc.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_start=new Intent(Cellulit.this,StartLPG.class);
				
				

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_cellulit_oedeme){
					StartLPG.time=30;
					StartLPG.period = 0.8;
					startActivity(int_start);
				}
				
				if (arg0.getId()==R.id.btn_cellulit_tonic){
					StartLPG.time=30;
					StartLPG.period = 0.7;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_cellulit_soft){
					StartLPG.time=30;
					StartLPG.period = 0.8;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_cellulit_double){
					StartLPG.time=10;
					StartLPG.period = 0.9;
					startActivity(int_start);
				}

				
				if (arg0.getId()==R.id.btn_cellulit_back){
					this.finish();
				}
				
	}
	

}
