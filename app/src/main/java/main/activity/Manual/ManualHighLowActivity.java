package main.activity.Manual;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.Enums.VersionTypes;
import com.Tirax.RF.MyActivity;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;


public class ManualHighLowActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DataProvider.VERSION == VersionTypes.CAVITATION) {
            setContentView(R.layout.manual_bi_mono_cav);
            Button cav=(Button) findViewById(R.id.btn_cavitation);
            cav.setOnClickListener(this);
        }
        else
            setContentView(R.layout.manual_high_low);
        //declaring main menu buttons
        Button monopolar=(Button) findViewById(R.id.btn_monopolar);
        Button hbipolar=(Button) findViewById(R.id.btn_bipolar_high);
        Button lbipolar=(Button) findViewById(R.id.btn_bipolar_low);
        Button back=(Button) findViewById(R.id.btn_back_manual);

        //declaring onclicklistener functions
        monopolar.setOnClickListener(this);
        hbipolar.setOnClickListener(this);
        lbipolar.setOnClickListener(this);
        back.setOnClickListener(this);



    }

    @Override
    public void onClick(View arg0) {


        //declaring intents


        //starting corresponding intents

        if (arg0.getId()==R.id.btn_monopolar){
            Intent int_manual_settings=new Intent(ManualHighLowActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.MONOPOLAR;
            Values.type = Types.LF;
            startActivity(int_manual_settings);
        }

        if (arg0.getId()==R.id.btn_bipolar_high){
            Intent int_manual_settings=new Intent(ManualHighLowActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.BIPOLAR_HIGH;
            Values.type = Types.HF;
            startActivity(int_manual_settings);
        }


        if (arg0.getId()==R.id.btn_bipolar_low){
            Intent int_manual_settings=new Intent(ManualHighLowActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.BIPOLAR_LOW;
            Values.type = Types.LF;
            startActivity(int_manual_settings);
        }

        if (arg0.getId()==R.id.btn_cavitation){
            Intent int_power_time=new Intent(ManualHighLowActivity.this,PowerTimeActivity.class);
            Values.type = Types.CAVITATION;
            Pages.bi_mono = Pages.MANUAL_CAVITATION;
            startActivity(int_power_time);
        }

        if (arg0.getId()==R.id.btn_back_manual){

            this.finish();
        }

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }


}
