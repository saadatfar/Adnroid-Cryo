package main.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.Tirax.RF.MyActivity;
import com.example.cryo.R;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

  public class ShowLogCat extends MyActivity implements View.OnClickListener {
    int backpressed=0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.logcat);
        Button con=(Button)findViewById(R.id.btn_logcat_filter);
        Button send=(Button)findViewById(R.id.btn_filter_send);
        Button rec=(Button)findViewById(R.id.btn_filter_rec);
        Button tirax=(Button)findViewById(R.id.btn_filter_tirax);
        Button clear=(Button)findViewById(R.id.btn_filter_clear);
        Button back=(Button)findViewById(R.id.btn_filter_back);
        Button refresh=(Button)findViewById(R.id.btn_filter_refresh);

        con.setOnClickListener(this);
        send.setOnClickListener(this);
        rec.setOnClickListener(this);
        tirax.setOnClickListener(this);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);

        showLog("");
        }

      private void showLog(String filterVal) {
          try {

              Process process = Runtime.getRuntime().exec("logcat -d");
              BufferedReader bufferedReader = new BufferedReader(
                      new InputStreamReader(process.getInputStream()));
              StringBuilder log=new StringBuilder();
              String line = "";
              while ((line = bufferedReader.readLine()) != null) {

                  if(line.contains(filterVal)) {
                      log.insert(0,line+"\n");

                  }

              }
              TextView tv = (TextView)findViewById(R.id.textView1);
              tv.setMovementMethod(new ScrollingMovementMethod());
              tv.setText(log.toString());
              tv.scrollTo(0,0);
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

      @Override
      public void onClick(View v) {
          if(R.id.btn_logcat_filter == v.getId()) {
              showLog(((EditText)findViewById(R.id.txt_filter)).getText().toString());
          }
          if(R.id.btn_filter_send == v.getId()) {
              showLog("TIRAX1");
          }
          if(R.id.btn_filter_rec == v.getId()) {
              showLog("TIRAX4");
          }
          if(R.id.btn_filter_tirax == v.getId()) {
              showLog("TIRAX");
          }
          if(R.id.btn_filter_clear == v.getId()) {
              ((TextView)findViewById(R.id.textView1)).setText("");
          }
          if(R.id.btn_filter_refresh == v.getId()) {
              ((TextView)findViewById(R.id.textView1)).setText("");
              showLog("");
          }
          if(R.id.btn_filter_back == v.getId()) {
              this.finish();
          }
      }
  }
