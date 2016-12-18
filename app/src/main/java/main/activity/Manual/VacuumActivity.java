package main.activity.Manual;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Options.ManualOption;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;


public class VacuumActivity extends MyActivity implements OnClickListener {

    private boolean vacuumAutoIncrement = false;
    private boolean vacuumAutoDecrement = false;
    private Handler repeatUpdateHandler = new Handler();
    private TextView vacuumText;

    public int vacuumValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacuum);

        //declaring main menu buttons
        Button addfrq=(Button) findViewById(R.id.btn_addVacuum);
        Button decfrq=(Button) findViewById(R.id.btn_decVacuum);
        Button back = (Button) findViewById(R.id.btn_vacuum_back);
        Button next = (Button) findViewById(R.id.btn_next);



        vacuumText = (TextView) findViewById(R.id.txt_vacuum);
        vacuumValue =500;


        //temp increase
        addfrq.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        vacuumAutoIncrement = true;
                        repeatUpdateHandler.post(new RptFreqUpdater());
                        return false;
                    }
                }
        );

        addfrq.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                        && vacuumAutoIncrement) {
                    vacuumAutoIncrement = false;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN)
                    increment();
                return false;
            }


        });

        //temp deccrease
        decfrq.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View arg0) {
                        vacuumAutoDecrement = true;
                        repeatUpdateHandler.post(new RptFreqUpdater());
                        return false;
                    }
                }
        );

        decfrq.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                        && vacuumAutoDecrement) {
                    vacuumAutoDecrement = false;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN)
                    decrement();
                return false;
            }


        });

        back.setOnClickListener(this);
        next.setOnClickListener(this);



    }
    public void decrement(){
        vacuumValue -=100;
        if(vacuumValue <300)
            vacuumValue =300;
        vacuumText.setText("" + vacuumValue);

    }
    public void increment(){
        vacuumValue +=100;
        if(vacuumValue >700)
            vacuumValue =700;
        vacuumText.setText("" + vacuumValue);
    }

    @Override
    public void onClick(View arg0) {
        Intent int_next = new Intent(VacuumActivity.this,FrequencePowerActivity.class);

        if (arg0.getId()== R.id.btn_vacuum_back){
            this.finish();
        }
        if (arg0.getId()== R.id.btn_next){
            Values.vacuumLevel = this.vacuumValue;
            startActivity(int_next);
        }

    }



    class RptFreqUpdater implements Runnable {
        private static final long REP_DELAY = 35;

        public void run() {
            if(vacuumAutoIncrement){

                increment();
                repeatUpdateHandler.postDelayed( new RptFreqUpdater(), REP_DELAY );
            } else if(vacuumAutoDecrement){
                decrement();
                repeatUpdateHandler.postDelayed( new RptFreqUpdater(), REP_DELAY );
            }
        }
    }
}
