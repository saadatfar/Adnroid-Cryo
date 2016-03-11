package main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.Tirax.cryo.DataProvider;
import com.example.cryo.R;

public class SelectHandpieceActivity extends Activity implements View.OnClickListener {

    public static boolean right=true;
    public static boolean left=true;
    public static boolean vaccum=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.handpiece);

        //declaring main menu buttons
        Button right=(Button) findViewById(R.id.btn_rplate);
        Button left=(Button) findViewById(R.id.btn_lplate);
        Button vaccum=(Button) findViewById(R.id.btn_vaccum);
        Button back= (Button) findViewById(R.id.btn_handpiece_back);
        Button next= (Button) findViewById(R.id.btn_next_hand);

        right.setOnClickListener(this);
        left.setOnClickListener(this);
        vaccum.setOnClickListener(this);
        back.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        //declaring intents
        Intent int_start=new Intent(SelectHandpieceActivity.this,StartActivity.class);
        Log.e("TIRAX", "i got a click");


        //starting corresponding intents
        if (arg0.getId()== R.id.btn_rplate){
            Button rightVisible = (Button) findViewById(R.id.btn_rplate_hidden);
            if(right){
                rightVisible.setVisibility(View.VISIBLE);
            }else{
                rightVisible.setVisibility(View.INVISIBLE);
            }
            right=!right;
        }

        if (arg0.getId()==R.id.btn_lplate){
            Button leftVisible = (Button) findViewById(R.id.btn_lplate_hidden);
            if(left){
                leftVisible.setVisibility(View.VISIBLE);
            }else{
                leftVisible.setVisibility(View.INVISIBLE);
            }
            left=!left;
        }
        if (arg0.getId()==R.id.btn_vaccum){
            Button vaccumVisible = (Button) findViewById(R.id.btn_vaccum_hidden);
            if(vaccum){
                vaccumVisible.setVisibility(View.VISIBLE);
            }else{
                vaccumVisible.setVisibility(View.INVISIBLE);
            }
            vaccum=!vaccum;
        }

        if (arg0.getId()==R.id.btn_handpiece_back){
            this.finish();
        }
        if (arg0.getId()==R.id.btn_next_hand){
            if(right | left | vaccum){
                Log.e("TIRAX","*******************I Want to Select Handpiece Cryo**************************");
                DataProvider.setHandpiece(right,left,vaccum);
                startActivity(int_start);
            }
        }

    }
}
