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


public class SelectBodyPartActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_body_part);
        //declaring main menu buttons
        Button body=(Button) findViewById(R.id.btn_body_body);
        Button face=(Button) findViewById(R.id.btn_body_face);
        Button neck=(Button) findViewById(R.id.btn_body_neck);
        Button legs=(Button) findViewById(R.id.btn_body_legs);
        Button back= (Button) findViewById(R.id.btn_back_body_part);

        //declaring onclicklistener functions
        body.setOnClickListener(this);
        face.setOnClickListener(this);
        neck.setOnClickListener(this);
        legs.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View arg0) {

        //declaring intents
        Intent int_bmi=new Intent(SelectBodyPartActivity.this,BMISettingsActivity.class);



        //starting corresponding intents

        if (arg0.getId()==R.id.btn_body_body){
            Pages.part_of_body = BodyPart.BODY;
            startActivity(int_bmi);
        }

        if (arg0.getId()==R.id.btn_body_face){
            Pages.part_of_body = BodyPart.FACE;
            startActivity(int_bmi);
        }


        if (arg0.getId()==R.id.btn_body_neck){
            Pages.part_of_body = BodyPart.NECK;
            startActivity(int_bmi);
        }


        if (arg0.getId()==R.id.btn_body_legs){
            Pages.part_of_body = BodyPart.LEGS;
            startActivity(int_bmi);
        }

        if (arg0.getId()==R.id.btn_back_body_part){
            this.finish();
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
