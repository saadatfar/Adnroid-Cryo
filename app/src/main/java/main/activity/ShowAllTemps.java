package main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.Tirax.cryo.SerialPort;
import com.example.cryo.R;

public class ShowAllTemps extends Activity {
    int backpressed=0;
	private Handler UIHandler = new Handler();
	public static boolean finished=true;
	@Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.logcat);
         finished=false;
         UIHandler.postDelayed(UIreportsRunnable, 0);
         
         

         
	}
	 @Override  
	 public void onBackPressed() { 
		 finished=true;
		 //SerialPort.sendAndWait("PWOF");
		 this.finish();
	 }
	 Runnable UIreportsRunnable = new Runnable() {
			
			@Override
			public void run() {
		         TextView tv = (TextView)findViewById(R.id.textView1);
		         StringBuilder log=new StringBuilder();
		         
		         for(int i=0;i<8;i++){
		        	 //String line = "Temp sensor "+i+": "+CryoFields.tempsensors[i]+"\n"; 
		        	 //log.append(line);
		        	 
		         }
		         tv.setMovementMethod(new ScrollingMovementMethod());
		         tv.setText(log.toString());
				if(!finished)
						UIHandler.postDelayed(UIreportsRunnable, 300);
			}
		};
	
}
