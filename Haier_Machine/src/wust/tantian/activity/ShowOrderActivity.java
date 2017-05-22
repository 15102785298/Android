package wust.tantian.activity;

import java.util.List;

import wust.tantian.adapter.OrderAdapter;
import wust.tantian.enity.Order;
import wust.tantian.view.RefreshListView;
import wust.tantian.view.RefreshListView.IRefreshListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.example.haier_machine.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowOrderActivity extends Activity implements IRefreshListener {
	private RefreshListView refreshListView;
	private TextView textView;
	private Button button;
	private BmobUser user;
	private List<Order> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_order);
		beforeCheck();
		init();
		getNewData();
	}

	private void beforeCheck() {
		user = BmobUser.getCurrentUser(getApplicationContext());
	}

	private void getData() {

	}

	private void getNewData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (user == null) {
					Toast.makeText(getApplicationContext(), "您还没有登录哦~", 1000)
							.show();
				} else {
					BmobQuery<Order> query = new BmobQuery<Order>();
					query.addWhereEqualTo("username", user.getUsername());
					query.setLimit(100);
					query.order("createdAt");
					query.findObjects(getApplicationContext(),
							new FindListener<Order>() {

								@Override
								public void onSuccess(List<Order> arg0) {
									// TODO Auto-generated method stub
									list = arg0;
									OrderAdapter orderAdapter = new OrderAdapter(
											getApplicationContext(), arg0);
									refreshListView.setAdapter(orderAdapter);
								}

								@Override
								public void onError(int arg0, String arg1) {
									// TODO Auto-generated method stub
								}
							});
				}
			}
		}).run();

	}

	private void init() {
		// TODO Auto-generated method stub
		refreshListView = (RefreshListView) findViewById(R.id.gv_order_food);
		textView = (TextView) findViewById(R.id.tv_back);
		button = (Button) findViewById(R.id.btn_ok);
		refreshListView.setInterface(this);

	}

	@Override
	public void onRefresh() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 获取最新数据

				getNewData();
				refreshListView.refreshComplete();
				// 通知界面显示

			}

		}, 2000);
	}
}
