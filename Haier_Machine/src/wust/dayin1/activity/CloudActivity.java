package wust.dayin1.activity;

import java.util.ArrayList;
import java.util.List;

import wust.dayin1.adapter.CommunityAdapter;
import wust.dayin1.adapter.GridViewAdapter;
import wust.dayin1.enity.Community;
import wust.dayin1.enity.Enity;
import wust.dayin1.enity.Menu;
import wust.dayin1.enity.User;
import wust.dayin1.tools.ZProgressHUD;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.example.haier_machine.R;

public class CloudActivity extends Activity {
	long exitTime = 0;
	ImageView login;
	ImageView tv_search;
	Button btn_nativeLocal;
	TextView tv_bind_machine;
	GridView gv_main_food;
	List<Enity> list = new ArrayList<Enity>();
	boolean flag;

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

		getMenuData();

		// 登陆
		login = (ImageView) findViewById(R.id.tv_login);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CloudActivity.this, PersonInfo.class));
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
				i.putExtra("menu", list.get(arg2));
				startActivity(i);
			}
		});

		tv_bind_machine = (TextView) findViewById(R.id.tv_bind_machine);
		flag = false;

		if (BmobUser.getCurrentUser(getApplicationContext()) == null) {
			tv_bind_machine.setText("绑定");
			flag = false;
		} else {
			System.out.println("emal/: "
					+ BmobUser.getCurrentUser(getApplicationContext())
							.getEmail());
			if (BmobUser.getCurrentUser(getApplicationContext()).getEmail() == null
					|| BmobUser.getCurrentUser(getApplicationContext())
							.getEmail().equals("")) {
				tv_bind_machine.setText("绑定");
				flag = false;
			} else {
				tv_bind_machine.setText("控制");
				flag = true;
			}
		}
		tv_bind_machine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (BmobUser.getCurrentUser(getApplicationContext()) == null) {
					Toast.makeText(getApplicationContext(), "您需要先登录哦~",
							Toast.LENGTH_SHORT).show();
				} else {
					if (flag) {
						startActivity(new Intent(CloudActivity.this,
								ControlMachineActivity.class));
					} else {
						startActivity(new Intent(CloudActivity.this,
								BindMachineActivity.class));
					}
				}
			}
		});
	}

	private void getMenuData() {
		// TODO Auto-generated method stub

		final ZProgressHUD progressHUD = ZProgressHUD.getInstance(this);
		progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
		progressHUD.show();
		// 新建线程加载图片信息，发送到消息队列中
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BmobQuery<Menu> query = new BmobQuery<Menu>();
				query.findObjects(getApplicationContext(),
						new FindListener<Menu>() {
							@Override
							public void onSuccess(List<Menu> arg0) {
								list.clear();
								int size = arg0.size();
								for (int i = 0; i < size; i++) {
									Enity enity = new Enity();
									Menu temp = arg0.get(i);
									enity.setFood_name(temp.getStr());
									enity.setFood_pic(temp.getPic());
									enity.setContents(temp.getContent());
									enity.setEffects(temp.getEffect());
									enity.setLevels(temp.getLevel());
									enity.setSteps(temp.getStep());
									enity.setId(temp.getId());
									enity.setTimes(temp.getTimes());
									list.add(enity);
								}
								gv_main_food.setAdapter(new GridViewAdapter(
										CloudActivity.this, list));
								Toast.makeText(getApplicationContext(),
										"载入成功！", Toast.LENGTH_LONG).show();
								progressHUD.dismissWithSuccess();
							}

							@Override
							public void onError(int arg0, String arg1) {
								Toast.makeText(getApplicationContext(), "载入错误",
										Toast.LENGTH_LONG).show();

							}
						});

			}
		}).start();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
		}
		return super.onKeyDown(keyCode, event);
	}

}
