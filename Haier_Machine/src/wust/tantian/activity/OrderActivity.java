package wust.tantian.activity;

import wust.tantian.tools.ActivitySelector;
import wust.tantian.tools.DialogDemo;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class OrderActivity extends Activity {
	Button btn_confirm;
	TextView tv_shopbus_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		btn_confirm=(Button)findViewById(R.id.btn_shopbus_submit);
		btn_confirm.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivitySelector.closeActivity(OrderActivity.this);
				Toast.makeText(getApplicationContext(), "提交成功，请耐心等待快递员派送", Toast.LENGTH_SHORT).show();
			}
		});
		tv_shopbus_back=(TextView)findViewById(R.id.tv_shopbus_back);
		tv_shopbus_back.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivitySelector.closeActivity(OrderActivity.this);
			}
		});
	}

}
