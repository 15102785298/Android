package wust.dayin1.activity;

import org.apache.commons.lang.StringUtils;

import wust.dayin1.DAO.DBhelper;
import wust.dayin1.enity.Community;
import wust.dayin1.enity.Order;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.example.haier_machine.R;

public class BuyFoodActivity extends Activity implements OnClickListener {

	private String content;
	private String food_name;
	private EditText et_food_name;
	private EditText et_content;
	private EditText et_price;
	private EditText et_num;
	private EditText et_all_price;
	private EditText et_address;
	private EditText et_phone;
	private Button btn_shopbus_submit;
	private TextView tv_add_address;
	private TextView tv_shopbus_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		initTool();
		initValue();

	}

	// 初始化控件
	private void initTool() {

		et_phone = (EditText) findViewById(R.id.et_phone);
		et_food_name = (EditText) findViewById(R.id.et_food_name);
		et_content = (EditText) findViewById(R.id.et_content);
		et_price = (EditText) findViewById(R.id.et_price);
		et_num = (EditText) findViewById(R.id.et_num);
		et_all_price = (EditText) findViewById(R.id.et_all_price);
		et_address = (EditText) findViewById(R.id.et_address);
		et_phone = (EditText) findViewById(R.id.et_phone);

		tv_add_address = (TextView) findViewById(R.id.tv_add_address);
		btn_shopbus_submit = (Button) findViewById(R.id.btn_shopbus_submit);
		tv_shopbus_back = (TextView) findViewById(R.id.tv_shopbus_back);
		tv_add_address.setOnClickListener(this);
		btn_shopbus_submit.setOnClickListener(this);
		tv_shopbus_back.setOnClickListener(this);
	}

	private void initValue() {
		content = getIntent().getStringExtra(DBhelper.CONTENT);
		food_name = getIntent().getStringExtra(DBhelper.NAME);
		String[] content_foods = StringUtils.split(content, '，');
		StringBuffer text = new StringBuffer("主食：");
		boolean flag = true;

		for (int i = 0; i < content_foods.length; i++) {
			if (flag) {
				text.append(content_foods[i]);
				text.append("*400g; ");
				text.append("配料: ");
				flag = false;
			} else {
				text.append(content_foods[i]);
				text.append("*20g;");
			}
		}
		et_food_name.setText(food_name);
		et_content.setText(text.toString());

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.tv_add_address:
			jumpToAdd();
			break;
		case R.id.btn_shopbus_submit:
			submit();
			jumpback();
			break;
		case R.id.tv_shopbus_back:
			startActivity(new Intent(BuyFoodActivity.this,
					CloudActivity.class));
			break;
		}
	}

	private void jumpback() {
		// TODO Auto-generated method stub

	}

	private void submit() {
		if (BmobUser.getCurrentUser(getApplicationContext()) == null) {
			Toast.makeText(getApplicationContext(), "您还没有登录哦~",
					Toast.LENGTH_SHORT).show();
		} else {

			String food_name = et_food_name.getText().toString();
			String content = et_content.getText().toString();
			String price = et_price.getText().toString();
			String num = et_num.getText().toString();
			String all_price = et_all_price.getText().toString();
			String address = et_address.getText().toString();
			String phone = et_phone.getText().toString();

			if (food_name.equals("") || content.equals("") || price.equals("")
					|| num.equals("") || all_price.equals("")
					|| address.equals("") || phone.equals(""))
				Toast.makeText(BuyFoodActivity.this, "您的订单不完整哦！",
						Toast.LENGTH_SHORT).show();
			else {
				Toast.makeText(BuyFoodActivity.this, "下单中...",
						Toast.LENGTH_SHORT).show();
				Order c = new Order();
				c.setFood_name(food_name);
				c.setContent(content);
				c.setPrice(Integer.parseInt(price));
				c.setNum(Integer.parseInt(num));
				c.setAll_pay(Integer.parseInt(all_price));
				c.setAddress(address);
				c.setPhone(phone);
				c.setUserName(BmobUser.getCurrentUser(getApplicationContext())
						.getUsername());
				c.save(getApplicationContext(), new SaveListener() {
					@Override
					// 返回成功
					public void onSuccess() {
						Intent intent = new Intent(BuyFoodActivity.this,
								OrderSuccess.class);
						intent.putExtra("flag", "success");
						startActivity(intent);
					}

					@Override
					// 返回失败
					public void onFailure(int i, String s) {
						Intent intent = new Intent(BuyFoodActivity.this,
								OrderSuccess.class);
						intent.putExtra("flag", "fail");
						startActivity(intent);
					}
				});
			}
		}
	}

	private void jumpToAdd() {
		// TODO Auto-generated method stub

	}

}
