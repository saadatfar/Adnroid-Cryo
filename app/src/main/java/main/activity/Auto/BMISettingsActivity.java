package main.activity.Auto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.RF.Enums.BodyPart;
import com.Tirax.RF.Manager;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;

import main.activity.StartActivity;


public class BMISettingsActivity extends Activity implements OnClickListener {
	
	private boolean weightAutoIncrement = false;
	private boolean weightAutoDecrement = false;
	private boolean heightAutoIncrement = false;
	private boolean heightAutoDecrement = false;
	private Handler repeatUpdateHandler = new Handler();
	private TextView weightText ;
	private TextView heightText ;
	public int weightValue;
	public int heightValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi_settings);
		
				//declaring main menu buttons
				Button addWeight=(Button) findViewById(R.id.btn_addWeight);
				Button decWeight=(Button) findViewById(R.id.btn_decWeight);
				Button addHeight=(Button) findViewById(R.id.btn_addHeight);
				Button decHeight=(Button) findViewById(R.id.btn_decHeight);
				Button back = (Button) findViewById(R.id.btn_back);
				Button next = (Button) findViewById(R.id.btn_next);
				
				
				weightText = (TextView) findViewById(R.id.txt_weight);
				heightText = (TextView) findViewById(R.id.txt_height);
				
				weightValue=70;
				heightValue=160;
				
				//temp increase
				addWeight.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                    weightAutoIncrement = true;
			                    repeatUpdateHandler.post( new WeightUpdater() );
			                    return false;
			                }
			            }
			    );   

				addWeight.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && weightAutoIncrement ){
			                weightAutoIncrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	incrementWeight();
			            return false;
			        }

					
			    });  
				//time increase
				addHeight.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                	heightAutoIncrement = true;
			                    repeatUpdateHandler.post( new HeightUpdater() );
			                    return false;
			                }
			            }
			    );   

				addHeight.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && heightAutoIncrement ){
			            	heightAutoIncrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	incrementHeight();
			            return false;
			        }

					
			    }); 

				//temp deccrease
				decWeight.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                    weightAutoDecrement = true;
			                    repeatUpdateHandler.post( new WeightUpdater() );
			                    return false;
			                }
			            }
			    );   

				decWeight.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && weightAutoDecrement ){
			                weightAutoDecrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	decrementWeight();
			            return false;
			        }

					
			    });  
				//time decrease
				decHeight.setOnLongClickListener( 
			            new View.OnLongClickListener(){
			                public boolean onLongClick(View arg0) {
			                	heightAutoDecrement = true;
			                    repeatUpdateHandler.post( new HeightUpdater() );
			                    return false;
			                }
			            }
			    );   

				decHeight.setOnTouchListener( new View.OnTouchListener() {
					@Override
			        public boolean onTouch(View v, MotionEvent event) {
			            if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL) 
			                    && heightAutoDecrement ){
			            	heightAutoDecrement = false;
			            }
			            else if(event.getAction()==MotionEvent.ACTION_DOWN)
			            	decrementHeight();
			            return false;
			        }

					
			    }); 
				back.setOnClickListener(this);
				next.setOnClickListener(this);

		
	}
	public void decrementWeight(){
		weightValue--;
		if(weightValue<0)
			weightValue=0;
		weightText.setText( ""+weightValue );
	    
	}
	public void incrementWeight(){
		weightValue++;
		if(weightValue>200)
			weightValue=200;
	    weightText.setText( ""+weightValue );
	}
	
	public void decrementHeight(){
		heightValue--;
		if(heightValue<0)
			heightValue=0;
		heightText.setText( ""+heightValue );
	    
	}
	public void incrementHeight(){
		heightValue++;
		if(heightValue>300)
			heightValue=300;
		heightText.setText( ""+heightValue );
	}
	@Override
	public void onClick(View arg0) {
		Intent int_next=new Intent(BMISettingsActivity.this,StartActivity.class);
		
		if (arg0.getId()== R.id.btn_back){
			this.finish();
		}
		if (arg0.getId()== R.id.btn_next){
			Values.weight = weightValue;
			Values.height = heightValue;
			if( Values.getBMI()>30 && Pages.part_of_body == BodyPart.BODY) {
				Intent int_first_sec  = new Intent(BMISettingsActivity.this,FirstSecActivity.class);
				startActivity(int_first_sec);
			}
			else {
				Pages.auto_stage = Pages.FIRST;
				startActivity(int_next);
			}
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	class HeightUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if( heightAutoIncrement ){
	        	
	            incrementHeight();
	            repeatUpdateHandler.postDelayed( new HeightUpdater(), REP_DELAY );
	        } else if( heightAutoDecrement ){
	            decrementHeight();
	            repeatUpdateHandler.postDelayed( new HeightUpdater(), REP_DELAY );
	        }
	    }
	}
	
	class WeightUpdater implements Runnable {
	    private static final long REP_DELAY = 5;

		public void run() {
	        if( weightAutoIncrement ){
	        	
	            incrementWeight();
	            repeatUpdateHandler.postDelayed( new WeightUpdater(), REP_DELAY );
	        } else if( weightAutoDecrement ){
	            decrementWeight();
	            repeatUpdateHandler.postDelayed( new WeightUpdater(), REP_DELAY );
	        }
	    }
	}
}
