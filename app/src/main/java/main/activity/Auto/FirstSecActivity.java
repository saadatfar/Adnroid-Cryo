package main.activity.Auto;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.Enums.BodyPart;
import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.R;

import main.activity.StartActivity;


public class FirstSecActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_second);
        //declaring main menu buttons
        Button first=(Button) findViewById(R.id.btn_first);
        Button sec=(Button) findViewById(R.id.btn_second);
        Button back= (Button) findViewById(R.id.btn_back_first_sec);

        //declaring onclicklistener functions
        first.setOnClickListener(this);
        sec.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View arg0) {

        //declaring intents
        Intent int_start=new Intent(FirstSecActivity.this,StartActivity.class);


        //starting corresponding intents

        if (arg0.getId()==R.id.btn_first){
            Pages.auto_stage = Pages.FIRST;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_second){
            Pages.auto_stage = Pages.SECOND;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_back_first_sec){
            this.finish();
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
