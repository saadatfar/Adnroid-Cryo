package main.activity.lpg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.cryo.*;


public class Lifting extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifting);
				//declaring main menu buttons
				Button brst=(Button) findViewById(R.id.btn_lifting_burst);
				Button face=(Button) findViewById(R.id.btn_lifting_face);
				Button neck=(Button) findViewById(R.id.btn_lifting_neck);
				Button back= (Button) findViewById(R.id.btn_lifting_back);
				
				//declaring onclicklistener functions
				brst.setOnClickListener(this);
				face.setOnClickListener(this);
				neck.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_start=new Intent(Lifting.this,StartLPG.class);
				

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_lifting_burst){
					StartLPG.time=30;
					StartLPG.period = 1;
					startActivity(int_start);
				}
				
				if (arg0.getId()==R.id.btn_lifting_face){
					StartLPG.time=20;
					StartLPG.period = 1.5;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_lifting_neck){
					StartLPG.time=10;
					StartLPG.period = 1.5;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_lifting_back){
					this.finish();
				}
				
	}
	

}
