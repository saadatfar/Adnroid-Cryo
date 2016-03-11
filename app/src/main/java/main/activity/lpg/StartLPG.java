

package main.activity.lpg;



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


public class StartLPG extends Activity implements OnClickListener {
	

	public static int time;
	public static double period;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.strat_lpg);
		//declaring main menu buttons
		Button start=(Button) findViewById(R.id.btn_startlpg);
		Button back=(Button) findViewById(R.id.btn_back_startlpg);
				
		//declaring onclicklistener functions
		start.setOnClickListener(this);
		back.setOnClickListener(this);
				
		TextView time_text = (TextView) findViewById(R.id.txt_time_startlpg);
		time_text.setText(time+"'");
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_stop=new Intent(StartLPG.this,StopLPG.class);

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_startlpg){
					StopLPG.time=time;
					Log.e("TIRAX", "LPG FREQUENCES" + (char) Math.round(period * 100)+"  "+period);
					DataProvider.setLpgFrqns((char) Math.round(period * 100));
					DataProvider.setLpg();
					DataProvider.deviceOn();
					startActivity(int_stop);
					this.finish();
				}
				
				if (arg0.getId()==R.id.btn_back_startlpg){
					this.finish();
				}
				
	}

	

}
