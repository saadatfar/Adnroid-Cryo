package main.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.cryo.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

  public class ShowLogCat extends Activity {
    int backpressed=0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.logcat);
            try {
            	
              Process process = Runtime.getRuntime().exec("logcat -d");
              BufferedReader bufferedReader = new BufferedReader(
              new InputStreamReader(process.getInputStream()));
              StringBuilder log=new StringBuilder();
              String line = ""; 
              while ((line = bufferedReader.readLine()) != null) {
            	if(line.indexOf("TIRAX")>=0){
	                log.append(line);
	                log.append("\n");
            	}

              }   
              TextView tv = (TextView)findViewById(R.id.textView1);
              tv.setMovementMethod(new ScrollingMovementMethod());
              tv.setText(log.toString());
            } catch (IOException e) {
          }
        }
	
	 @Override  
	 public void onBackPressed() {  
		 //disable button back
		 backpressed++;
		 if(backpressed>1)
		 {
	        	try {
					Runtime.getRuntime().exec("logcat -c");
					this.finish();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 backpressed=0;
		 }
	 }
	 
} 
