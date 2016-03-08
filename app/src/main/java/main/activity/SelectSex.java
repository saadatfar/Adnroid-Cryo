package main.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.cryo.*;


public class SelectSex extends Activity implements OnClickListener {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto);
				//declaring main menu buttons
				Button man=(Button) findViewById(R.id.btn_man);
				Button woman=(Button) findViewById(R.id.btn_woman);
				Button back= (Button) findViewById(R.id.btn_back_auto);
				
				//declaring onclicklistener functions
				man.setOnClickListener(this);
				woman.setOnClickListener(this);
				back.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View arg0) {
		
				//declaring intents
				Intent int_man=new Intent(SelectSex.this,ManSettings.class);
				Intent int_woman=new Intent(SelectSex.this,WomanSettings.class);

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_man){
					startActivity(int_man);
				}
				
				if (arg0.getId()==R.id.btn_woman){
					startActivity(int_woman);
				}
				if (arg0.getId()==R.id.btn_back_auto){
					this.finish();
				}
				
	}
	

}
