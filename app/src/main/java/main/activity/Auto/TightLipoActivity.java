package main.activity.Auto;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.R;

import main.activity.StartActivity;


public class TightLipoActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tight_lypo);
        //declaring main menu buttons
        Button lipo=(Button) findViewById(R.id.btn_lipo);
        Button tight=(Button) findViewById(R.id.btn_tight);
        Button back= (Button) findViewById(R.id.btn_back);

        //declaring onclicklistener functions
        lipo.setOnClickListener(this);
        tight.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View arg0) {

        //declaring intents



        //starting corresponding intents

        if (arg0.getId()==R.id.btn_lipo){
            Pages.auto_type = Pages.LYPOLISI;
            Intent int_start=new Intent(TightLipoActivity.this,SelectSex.class);
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_tight){
            Pages.auto_type = Pages.TIGHTENING;
            Intent int_start=new Intent(TightLipoActivity.this,SelectSex.class);
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_back){
            this.finish();
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
