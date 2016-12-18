package com.Tirax.RF.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Enums.VersionTypes;
import com.Tirax.RF.Mode;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.R;


/**
 * Created by Lenovo on 7/4/2016.
 */
public class SummaryDialog extends Dialog implements
        View.OnClickListener {

    private Mode mode;
    public Activity c;
    public Dialog d;
    public Button next, back;


    public SummaryDialog(Activity a, Mode m) {
        super(a);

        this.c = a;
        mode =  m;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary_dialog);
        this.setTitle("Summary");
        this.setCancelable(true);

        ImageButton con=(ImageButton)findViewById(R.id.summery_continue);
        con.setOnClickListener(this);

        setTexts();

        setHandPieces();
    }

    private void setHandPieces() {
        if(mode.type == Types.CAVITATION)
            findViewById(R.id.img_summary_cav).setBackgroundResource(R.drawable.socket);
        if(mode.monobi.equals("Monopolar"))
            findViewById(R.id.img_summary_plate).setBackgroundResource(R.drawable.socket);
        if(mode.type == Types.HF)
            findViewById(R.id.img_summary_high).setBackgroundResource(R.drawable.sockethf);
        if(mode.type == Types.LF)
            findViewById(R.id.img_summary_low).setBackgroundResource(R.drawable.socketlf);

        findViewById(R.id.img_summary_handpiece).setBackgroundResource(mode.handpieceImg);
    }

    private void setTexts() {
        TextView monobi = (TextView) findViewById(R.id.txt_summery_monobi);
        monobi.setText("Mode: "+mode.completeName());

        TextView freq = (TextView) findViewById(R.id.txt_summery_freq);
        if(mode.type == Types.CAVITATION) {
            freq.setVisibility(View.INVISIBLE);
            findViewById(R.id.img_summary_freq).setVisibility(View.INVISIBLE);
            findViewById(R.id.txt_summery_pulse).setVisibility(View.INVISIBLE);
            findViewById(R.id.img_summary_pulse).setVisibility(View.INVISIBLE);

        }else{

            freq.setText("Frequency: " + mode.frequency + " KHz");
        }
        TextView power = (TextView) findViewById(R.id.txt_summery_power);
        power.setText("Power: " + mode.power+"%");

        TextView time = (TextView) findViewById(R.id.txt_summery_time);
        time.setText("Time: " + mode.time+ "'");

        if(Pages.bi_mono == Pages.VACUUM) {
            TextView vacuum = (TextView) findViewById(R.id.txt_summery_vacuum);
            vacuum.setVisibility(View.VISIBLE);
            findViewById(R.id.img_summary_vacuum).setVisibility(View.VISIBLE);
            vacuum.setText("Vacuum: " + mode.vacuumLevel + "%");
        }

        TextView pulse = (TextView) findViewById(R.id.txt_summery_pulse);
        if(mode.pulseFrq == 0)
        {
            pulse.setText("Continuous");
        }
        else {
            pulse.setText("Pulse Frq.: " + mode.pulseFrq + " Hz   Pulse Len.:" + mode.pulseLength + " Ms");
        }


    }


    @Override
    public void onClick(View v) {
        if(R.id.summery_continue == v.getId()) {
            this.cancel();
        }

    }


}