package main.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.SharedPrefrences;
import com.example.cryo.R;

public class SettingsActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        TextView time = (TextView) findViewById(R.id.txt_settings_time);
        time.setText("Works Time: "+ SharedPrefrences.getTimeOfWorks());
    }

}
