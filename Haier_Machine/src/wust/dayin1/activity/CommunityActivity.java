package wust.dayin1.activity;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wust.dayin1.adapter.CommunityAdapter;
import wust.dayin1.enity.Community;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

import com.example.haier_machine.R;

public class CommunityActivity extends Activity implements OnClickListener {
	private ListView lv;
	private TextView add_community;
	private TextView back;
	private CommunityAdapter adapter;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_community);
		init();
		initData();
	}

	private void init() {
		lv = (ListView) findViewById(R.id.list);
		add_community = (TextView) findViewById(R.id.tv_community_add);
		add_community.setOnClickListener(this);
		back = (TextView) findViewById(R.id.tv_back);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
			startActivity(new Intent(CommunityActivity.this, PersonInfo.class));
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			break;
		case R.id.tv_community_add:
			startActivity(new Intent(CommunityActivity.this,
					AddCommunityActivity.class));
			break;
		default:
			break;
		}
	}

	public void initData() {
		new Thread() {
			public void run() {
				BmobQuery<Community> query = new BmobQuery<Community>();
				query.findObjects(getApplicationContext(),
						new FindListener<Community>() {
							@Override
							public void onSuccess(List<Community> arg0) {
								//for(int i = 0;i<arg0.size();i++)
									//arg0.get(i).setTime(arg0.get(i).getCreatedAt());
								adapter = new CommunityAdapter(
										getApplicationContext(), arg0);
								lv.setAdapter(adapter);
							}

							@Override
							public void onError(int arg0, String arg1) {
								Toast.makeText(getApplicationContext(), "ÔØÈë´íÎó",
										Toast.LENGTH_LONG).show();

							}
						});
				Message message = new Message();
				message.arg1 = 1;
				handler.sendMessage(message);
			};
		}.start();
	}

}
