package main.activity.lpg;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.cryo.*;


public class LPGMain extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lgp);
				//declaring main menu buttons
				Button lifting=(Button) findViewById(R.id.btn_lifting);
				Button cellulit=(Button) findViewById(R.id.btn_cellulit);
				Button rlx=(Button) findViewById(R.id.btn_relaxation);
				Button resurf=(Button) findViewById(R.id.btn_recurfacing);
				Button sport=(Button) findViewById(R.id.btn_sport);
				Button back= (Button) findViewById(R.id.btn_lpg_back);
				
				//declaring onclicklistener functions
				lifting.setOnClickListener(this);
				cellulit.setOnClickListener(this);
				rlx.setOnClickListener(this);
				resurf.setOnClickListener(this);
				sport.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
				//declaring intents
				Intent int_lift=new Intent(LPGMain.this,Lifting.class);
				Intent int_cellu=new Intent(LPGMain.this,Cellulit.class);
				Intent int_resurf=new Intent(LPGMain.this,Resurfacing.class);
				Intent int_sport=new Intent(LPGMain.this,Sport.class);
				Intent int_start=new Intent(LPGMain.this,StartLPG.class);
				

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_lifting){
					startActivity(int_lift);
				}
				
				if (arg0.getId()==R.id.btn_cellulit){
					startActivity(int_cellu);
				}

				if (arg0.getId()==R.id.btn_relaxation){
					StartLPG.time=10;
					StartLPG.period=0.8;
					startActivity(int_start);
				}

				if (arg0.getId()==R.id.btn_recurfacing){
					startActivity(int_resurf);
				}

				if (arg0.getId()==R.id.btn_sport){
					startActivity(int_sport);
				}
				
				if (arg0.getId()==R.id.btn_lpg_back){
					this.finish();
				}
				
	}
	

}
