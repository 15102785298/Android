package wust.dayin1.activity;

import java.util.ArrayList;
import java.util.List;

import wust.dayin1.adapter.GridViewAdapter;
import wust.dayin1.enity.Enity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.haier_machine.R;

public class CloudActivity extends Activity {
	long exitTime = 0;
	ImageView login;
	ImageView tv_search;
	Button btn_nativeLocal;
	GridView gv_main_food;
	List<Enity> list = new ArrayList<Enity>();
	//数据在本地
	int[] pics = { R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4,
			R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8 };
	String[] strs = { "琥珀桃仁", "奶香爆米花", "蚝油南瓜", "榛仁巧克力牛轧糖", "微波蒸栗子", "奶油玉米",
			"蒜香排骨", "意大利披萨" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cloud);
		init();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {

		}
	}

	private void init() {
		for (int i = 0; i < 8; i++) {
			Enity enity = new Enity();
			enity.setFood_name(strs[i]);
			enity.setFood_pic(pics[i]);
			list.add(enity);
		}
		// 登陆
		login = (ImageView) findViewById(R.id.tv_login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					startActivity(new Intent(CloudActivity.this,
							PersonInfo.class));
			}
		});

		// 本地菜谱
		btn_nativeLocal = (Button) findViewById(R.id.btn_nativeLocal);
		btn_nativeLocal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CloudActivity.this,
						NativeFoodActivity.class);
				startActivity(i);
			}
		});
		gv_main_food = (GridView) findViewById(R.id.gv_cloud_food);
		gv_main_food.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(CloudActivity.this,
						FoodDetailActivity.class);
				i.putExtra("id", arg2 + "");
				startActivity(i);
			}
		});
		gv_main_food.setAdapter(new GridViewAdapter(CloudActivity.this, list));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
		}
		return super.onKeyDown(keyCode, event);
	}

}
