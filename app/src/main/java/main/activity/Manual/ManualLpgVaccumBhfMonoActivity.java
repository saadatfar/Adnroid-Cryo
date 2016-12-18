package main.activity.Manual;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.Enums.Types;
import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.R;


public class ManualLpgVaccumBhfMonoActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacuum_lpg_blf_mlf);
        //declaring main menu buttons
        Button monopolar=(Button) findViewById(R.id.btn_monopolar);
        Button lbipolar=(Button) findViewById(R.id.btn_blf);
        Button vacuum=(Button) findViewById(R.id.btn_vacuum);
        Button lpg=(Button) findViewById(R.id.btn_lpg);

        Button back=(Button) findViewById(R.id.btn_back);

        //declaring onclicklistener functions
        monopolar.setOnClickListener(this);
        vacuum.setOnClickListener(this);
        lpg.setOnClickListener(this);
        lbipolar.setOnClickListener(this);
        back.setOnClickListener(this);



    }

    @Override
    public void onClick(View arg0) {


        //declaring intents


        //starting corresponding intents

        if (arg0.getId()==R.id.btn_monopolar){
            Intent int_manual_settings=new Intent(ManualLpgVaccumBhfMonoActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.MONOPOLAR;
            Values.type = Types.LF;
            startActivity(int_manual_settings);
        }


        if (arg0.getId()==R.id.btn_blf){
            Intent int_manual_settings=new Intent(ManualLpgVaccumBhfMonoActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.BIPOLAR_LOW;
            Values.type = Types.LF;
            startActivity(int_manual_settings);
        }


        if (arg0.getId()==R.id.btn_lpg){
            Intent int_manual_settings=new Intent(ManualLpgVaccumBhfMonoActivity.this,FrequencePowerActivity.class);
            Pages.bi_mono = Pages.LPG;
            Values.type = Types.LPG;
            startActivity(int_manual_settings);
        }

        if (arg0.getId()==R.id.btn_vacuum){
            Intent int_manual_settings=new Intent(ManualLpgVaccumBhfMonoActivity.this,VacuumActivity.class);
            Pages.bi_mono = Pages.VACUUM;
            Values.type = Types.VACUUM;
            startActivity(int_manual_settings);
        }

        if (arg0.getId()==R.id.btn_back){

            this.finish();
        }

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }


}
