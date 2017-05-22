package wust.tantian.activity;

import com.example.haier_machine.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSuccess extends Activity implements OnClickListener {

	private TextView tv_show_order_temp;
	private ImageView iv_order_pic;
	private Button btn_order_sure;
	private TextView tv_order_temp;
	private String flag = "success";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_success);
		flag = getIntent().getStringExtra("flag");
		initTools();
		initValue();
	}

	private void initValue() {
		if (flag.equals("success")) {
			Toast.makeText(getApplicationContext(), "下单成功！",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "出错啦！",
					Toast.LENGTH_SHORT).show();
			tv_show_order_temp.setText("下单失败");
			tv_order_temp.setText("程序出错啦！再试一次嘛！");
			iv_order_pic.setImageResource(R.drawable.order_fail);
		}
	}

	private void initTools() {
		tv_show_order_temp = (TextView) findViewById(R.id.tv_show_order_temp);
		iv_order_pic = (ImageView) findViewById(R.id.iv_order_pic);
		btn_order_sure = (Button) findViewById(R.id.btn_order_sure);
		btn_order_sure.setOnClickListener(this);
		tv_order_temp = (TextView) findViewById(R.id.tv_order_temp);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_order_sure:
			jump();
			break;
		}
	}

	private void jump() {
		Intent intent = new Intent(OrderSuccess.this, CloudActivity.class);
		startActivity(intent);
	}
}
