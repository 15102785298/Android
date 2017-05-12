package wust.dayin1.activity;

import java.util.ArrayList;
import java.util.List;

import wust.dayin1.adapter.GridViewAdapter;
import wust.dayin1.enity.CommunityToFood;
import wust.dayin1.enity.Enity;
import wust.dayin1.enity.Menu;
import wust.dayin1.tools.ActivitySelector;
import android.app.Activity;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.haier_machine.R;

public class ComentFoodActivity extends Activity {
	ListView lv_comment_food;
	EditText et_comment;
	TextView tv_foodcomment_back;
	TextView tv_foodcomment_submit;
	ArrayAdapter<String> adapter;
	Context context;
	static Enity enity = null;
	List<String> list = new ArrayList<String>();
	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tv_foodcomment_back:
				ActivitySelector.closeActivity(ComentFoodActivity.this);
				break;

			case R.id.tv_foodcomment_submit:
				String str = et_comment.getText().toString();
				if (!str.equals("")) {
					BmobUser bmobuser = BmobUser
							.getCurrentUser(getApplicationContext());
					if (bmobuser == null) {
						Toast.makeText(getApplicationContext(), "您需要先登录哦~",
								Toast.LENGTH_SHORT).show();
					} else {
						CommunityToFood communityToFood = new CommunityToFood();
						communityToFood.setAim_id(enity.getId() + "");
						communityToFood.setContent(str);
						communityToFood.setUserName(bmobuser.getUsername());
						communityToFood.save(getApplicationContext(),
								new SaveListener() {

									@Override
									public void onSuccess() {
										adapter.notifyDataSetChanged();// 底层的观察者模式
										et_comment.setText("");
									}

									@Override
									public void onFailure(int arg0, String arg1) {
										showToast("评论失败：" + arg1);
									}
								});
					}
				} else {
					showToast("内容不许为空");
				}
				break;
			default:
				break;
			}
		}

		private void showToast(String string) {
			Toast.makeText(context, string, 1).show();

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coment_food);
		init();
	}

	private void init() {
		enity = (Enity) getIntent().getSerializableExtra("menu");
		getData();
		context = this;

		et_comment = (EditText) findViewById(R.id.et_commmet);
		lv_comment_food = (ListView) findViewById(R.id.lv_food_commmet);

		tv_foodcomment_submit = (TextView) findViewById(R.id.tv_foodcomment_submit);
		tv_foodcomment_back = (TextView) findViewById(R.id.tv_foodcomment_back);
		tv_foodcomment_submit.setOnClickListener(listener);
		tv_foodcomment_back.setOnClickListener(listener);
	}

	private void getData() {
		// 新建线程加载图片信息，发送到消息队列中
		new Thread(new Runnable() {
			@Override
			public void run() {
				BmobQuery<CommunityToFood> query = new BmobQuery<CommunityToFood>();
				query.addWhereEqualTo("aim_id", enity.getId() + "");
				query.setLimit(100);
				query.order("createdAt");
				query.findObjects(getApplicationContext(),
						new FindListener<CommunityToFood>() {
							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub
							}

							@Override
							public void onSuccess(List<CommunityToFood> arg0) {
								int size = arg0.size();
								if (size > 0)
									for (int i = 0; i < size; i++) {
										list.add(arg0.get(i).getContent());
									}
								else
									list.add("暂无评价哦~");
								adapter = new ArrayAdapter<String>(context,
										android.R.layout.simple_list_item_1,
										list);
								lv_comment_food.setAdapter(adapter);
							}
						});
			}
		}).start();

	}

}
