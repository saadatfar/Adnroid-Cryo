package main.activity.Auto;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.*;

import main.activity.StartActivity;


public class SelectSex extends MyActivity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.woman_man);
				//declaring main menu buttons
				Button man=(Button) findViewById(R.id.btn_man);
				Button woman=(Button) findViewById(R.id.btn_woman);
				Button back= (Button) findViewById(R.id.btn_back_woman_man);
				
				//declaring onclicklistener functions
				man.setOnClickListener(this);
				woman.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_body_face=new Intent(SelectSex.this,BodyFaceActivity.class);
				Intent int_body_part=new Intent(SelectSex.this,SelectBodyPartActivity.class);

				//starting corresponding intents

				if (arg0.getId()==R.id.btn_man){
					Pages.woman_man = Pages.MAN;
					if(Pages.auto_type != Pages.LYPOLISI) {
						startActivity(int_body_face);
					}else{
						startActivity(int_body_part);
					}
				}
				
				if (arg0.getId()==R.id.btn_woman){
					Pages.woman_man = Pages.WOMAN;
					if(Pages.auto_type != Pages.LYPOLISI) {
						startActivity(int_body_face);
					}else{
						startActivity(int_body_part);
					}
				}
				if (arg0.getId()==R.id.btn_back_woman_man){
					this.finish();
				}

		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				
	}
	

}
