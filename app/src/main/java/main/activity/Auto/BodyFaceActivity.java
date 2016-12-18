package main.activity.Auto;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.Enums.BodyPart;
import com.Tirax.RF.MyActivity;

import com.Tirax.RF.Storage.Pages;
import com.example.cryo.*;

import main.activity.StartActivity;


public class BodyFaceActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_face);
        //declaring main menu buttons
        Button body=(Button) findViewById(R.id.btn_body);
        Button face=(Button) findViewById(R.id.btn_face);
        Button back= (Button) findViewById(R.id.btn_back_body_face);

        //declaring onclicklistener functions
        body.setOnClickListener(this);
        face.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View arg0) {

        //declaring intents
        Intent int_start=new Intent(BodyFaceActivity.this,StartActivity.class);


        //starting corresponding intents

        if (arg0.getId()==R.id.btn_body){
            Pages.part_of_body = BodyPart.BODY;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_face){
            Pages.part_of_body = BodyPart.FACE;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_back_body_face){
            this.finish();
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
