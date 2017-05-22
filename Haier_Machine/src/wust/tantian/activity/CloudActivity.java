package wust.tantian.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wust.tantian.adapter.CommunityAdapter;
import wust.tantian.adapter.GridViewAdapter;
import wust.tantian.enity.Community;
import wust.tantian.enity.Enity;
import wust.tantian.enity.Menu;
import wust.tantian.enity.User;
import wust.tantian.tools.ZProgressHUD;
import wust.tantian.view.RefreshListView;
import wust.tantian.view.RefreshListView.IRefreshListener;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

import com.example.haier_machine.R;

public class CloudActivity extends Activity implements IRefreshListener {
	long exitTime = 0;
	ImageView login;
	ImageView tv_search;
	Button btn_nativeLocal;
	TextView tv_bind_machine;
	RefreshListView gv_main_food;
	List<Enity> list = new ArrayList<Enity>();
	boolean flag;
	Button btn_reload;

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
		btn_reload = (Button) findViewById(R.id.btn_reload);
		btn_reload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View paramView) {
				
				getMenuData();
			}
		});
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
		gv_main_food = (RefreshListView) findViewById(R.id.gv_cloud_food);
		gv_main_food.setInterface(this);
		gv_main_food.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(CloudActivity.this,
						FoodDetailActivity.class);
				i.putExtra("menu", list.get(arg2-1));
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
				query.order("id");
				query.findObjects(getApplicationContext(),
						new FindListener<Menu>() {
							@Override
							public void onSuccess(List<Menu> arg0) {
								if (btn_reload.getVisibility() == 0) {
									btn_reload.setVisibility(4);
								}
								if(gv_main_food.getVisibility()==4){
									gv_main_food.setVisibility(0);
								}
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
								// 通知listView刷新数据完毕
								gv_main_food.refreshComplete();
								Toast.makeText(getApplicationContext(),
										"载入成功！", Toast.LENGTH_SHORT).show();
								progressHUD.dismissWithSuccess();
							}

							@Override
							public void onError(int arg0, String arg1) {
								if(gv_main_food.getVisibility()==0){
									gv_main_food.setVisibility(4);
								}
								if(btn_reload.getVisibility()==4){
									btn_reload.setVisibility(0);
								}
								progressHUD.dismissWithFailure();
								Toast.makeText(getApplicationContext(), "载入错误",
										Toast.LENGTH_SHORT).show();

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

	@Override
	public void onRefresh() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 获取最新数据
				getMenuData();
				// 通知界面显示

			}
		}, 2000);
	}

}
