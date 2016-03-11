package main.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;





import com.Tirax.cryo.DataProvider;
import com.example.cryo.*;


public class ManualSettingsActivity extends Activity implements OnClickListener {
	
	private boolean tempAutoIncrement = false;
	private boolean tempAutoDecrement = false;
	private boolean timeAutoIncrement = false;
	private boolean timeAutoDecrement = false;
	private Handler repeatUpdateHandler = new Handler();
	private TextView tempText ;
	private TextView timeText ;
	public int tempmValue;
	public int timeValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manual);
		
				//declaring main menu buttons
				Button addTemp=(Button) findViewById(R.id.btn_addWeight);
				Button decTemp=(Button) findViewById(R.id.btn_decWeight);
				Button addTime=(Button) findViewById(R.id.btn_addHeight);
				Button decTime=(Button) findViewById(R.id.btn_decHeight);
				Button back = (Button) findViewById(R.id.btn_back_manual);
				Button next = (Button) findViewById(R.id.btn_next_manual);
				
				
				
				tempText = (TextView) findViewById(R.id.txt_temp_manual);
				timeText = (TextView) findViewById(R.id.txt_time_manual);
				
				tempmValue=-10;
				timeValue=10;
				
				//temp increase
				addTemp.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                    tempAutoIncrement = true;
			                    repeatUpdateHandler.post( new RptTempUpdater() );
			                    return false;
			                }
			            }
			    );   

				addTemp.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && tempAutoIncrement ){
			                tempAutoIncrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	increment();
			            return false;
			        }

					
			    });  
				//time increase
				addTime.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                	timeAutoIncrement = true;
			                    repeatUpdateHandler.post( new RptTimeUpdater() );
			                    return false;
			                }
			            }
			    );   

				addTime.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && timeAutoIncrement ){
			            	timeAutoIncrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	incrementTime();
			            return false;
			        }

					
			    }); 

				//temp deccrease
				decTemp.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                    tempAutoDecrement = true;
			                    repeatUpdateHandler.post( new RptTempUpdater() );
			                    return false;
			                }
			            }
			    );   

				decTemp.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && tempAutoDecrement ){
			                tempAutoDecrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	decrement();
			            return false;
			        }

					
			    });  
				//time decrease
				decTime.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                	timeAutoDecrement = true;
			                    repeatUpdateHandler.post( new RptTimeUpdater() );
			                    return false;
			                }
			            }
			    );   

				decTime.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && timeAutoDecrement ){
			            	timeAutoDecrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	decrementTime();
			            return false;
			        }

					
			    }); 
				back.setOnClickListener(this);
				next.setOnClickListener(this);


		
	}
	public void decrement(){
		tempmValue--;
		if(tempmValue<-17)
			tempmValue=-17;
		tempText.setText( ""+tempmValue );
	    
	}
	public void increment(){
		tempmValue++;
		if(tempmValue>10)
			tempmValue=10;
	    tempText.setText( ""+tempmValue );
	}
	
	public void decrementTime(){
		timeValue--;
		if(timeValue<0)
			timeValue=0;
		timeText.setText( ""+timeValue );
	    
	}
	public void incrementTime(){
		timeValue++;
		if(timeValue>120)
			timeValue=120;
		timeText.setText( ""+timeValue );
	}
	@Override
	public void onClick(View arg0) {
		Intent int_next = new Intent(ManualSettingsActivity.this,SelectHandpieceActivity.class);
		
		if (arg0.getId()==R.id.btn_back_manual){
			this.finish();
		}
		if (arg0.getId()==R.id.btn_next_manual){
			StartActivity.time = this.timeValue;
			char r = (char)(this.tempmValue+30);
			Byte b =0;
			DataProvider.setTempRefrence(b, r);
			
			startActivity(int_next);
		}
				
	}
	
	class RptTimeUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if( timeAutoIncrement ){
	        	
	            incrementTime();
	            repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
	        } else if( timeAutoDecrement ){
	            decrementTime();
	            repeatUpdateHandler.postDelayed( new RptTimeUpdater(), REP_DELAY );
	        }
	    }
	}
	
	class RptTempUpdater implements Runnable {
	    private static final long REP_DELAY = 35;

		public void run() {
	        if( tempAutoIncrement ){
	        	
	            increment();
	            repeatUpdateHandler.postDelayed( new RptTempUpdater(), REP_DELAY );
	        } else if( tempAutoDecrement ){
	            decrement();
	            repeatUpdateHandler.postDelayed( new RptTempUpdater(), REP_DELAY );
	        }
	    }
	}
}
