package main.activity.Auto;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.Enums.VersionTypes;
import com.Tirax.RF.MyActivity;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.*;

import main.activity.StartActivity;


public class AutoActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DataProvider.VERSION == VersionTypes.CAVITATION){
            setContentView(R.layout.cavitation_auto);
            Button cav= (Button) findViewById(R.id.btn_cavitation);
            cav.setOnClickListener(this);
        }
        else {
            setContentView(R.layout.auto);
        }
        //declaring main menu buttons
        Button tightening=(Button) findViewById(R.id.btn_tightening);
        Button lipolysis=(Button) findViewById(R.id.btn_lipolysis);
        Button lifting=(Button) findViewById(R.id.btn_lifting);
        Button back= (Button) findViewById(R.id.btn_auto_back);

        //declaring onclicklistener functions
        tightening.setOnClickListener(this);
        lipolysis.setOnClickListener(this);
        lifting.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View arg0) {

        //declaring intents
        Intent int_sex=new Intent(AutoActivity.this,SelectSex.class);

        //starting corresponding intents
        if (arg0.getId()==R.id.btn_tightening){
            Pages.auto_type = Pages.TIGHTENING;
            startActivity(int_sex);
        }

        if (arg0.getId()==R.id.btn_lipolysis){
            Pages.auto_type = Pages.LYPOLISI;
            startActivity(int_sex);
        }

        if (arg0.getId()==R.id.btn_lifting){
            Pages.auto_type = Pages.LIFTING;
            startActivity(int_sex);
        }

        if(arg0.getId() == R.id.btn_cavitation){
            Pages.auto_type = Pages.CAVITATION;
            Intent int_start=new Intent(AutoActivity.this,StartActivity.class);
            startActivity(int_start);
        }
        if (arg0.getId()==R.id.btn_auto_back){
            this.finish();
        }

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
