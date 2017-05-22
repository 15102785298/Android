package wust.tantian.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wust.tantian.adapter.GridViewAdapter;
import wust.tantian.enity.CommunityToFood;
import wust.tantian.enity.Enity;
import wust.tantian.enity.Menu;
import wust.tantian.tools.ActivitySelector;
import android.app.Activity;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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
	ArrayAdapter<String> score_adapter;
	Spinner spinner_food_score;
	Context context;
	Integer score = 5;
	static Enity enity = null;
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	List<String> score_list = new ArrayList<String>();
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
						communityToFood.setScore(score);
						communityToFood.save(getApplicationContext(),
								new SaveListener() {

									@Override
									public void onSuccess() {

										getData();
										spinner_food_score.setSelection(0);
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

		spinner_food_score = (Spinner) findViewById(R.id.spinner_food_score);
		setSpinner();

		et_comment = (EditText) findViewById(R.id.et_commmet);
		lv_comment_food = (ListView) findViewById(R.id.lv_food_commmet);

		tv_foodcomment_submit = (TextView) findViewById(R.id.tv_foodcomment_submit);
		tv_foodcomment_back = (TextView) findViewById(R.id.tv_foodcomment_back);
		tv_foodcomment_submit.setOnClickListener(listener);
		tv_foodcomment_back.setOnClickListener(listener);
	}

	private void setSpinner() {
		score_list.add("给打个分吧~");
		score_list.add("5分");
		score_list.add("4分");
		score_list.add("3分");
		score_list.add("2分");
		score_list.add("1分");

		score_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, score_list);
		score_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_food_score.setAdapter(score_adapter);
		// 添加事件Spinner事件监听
		spinner_food_score
				.setOnItemSelectedListener(new SpinnerSelectedListener());
	}

	private void getData() {
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
								list.clear();
								int size = arg0.size();
								if (size > 0)
									for (int i = 0; i < size; i++) {
										CommunityToFood communityToFood = arg0
												.get(i);
										Map temp = new HashMap<String, String>();
										temp.put("content",
												communityToFood.getContent());
										temp.put("score",
												communityToFood.getScore()
														+ "分");
										list.add(temp);
									}
								else {
									Map temp = new HashMap<String, String>();
									temp.put("content", "暂无评价哦~");
									temp.put("score", 5 + "分");
									list.add(temp);
								}
								SimpleAdapter adSimpleAdapter = new SimpleAdapter(
										getApplicationContext(), list,
										R.layout.coment_food_cell,
										new String[] { "content", "score" },
										new int[] { R.id.tv_content,
												R.id.tv_score });

								lv_comment_food.setAdapter(adSimpleAdapter);
							}
						});
			}
		}).start();

	}

	// 使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
			case 1:
				score = 5;
				break;
			case 2:
				score = 4;
				break;
			case 3:
				score = 3;
				break;
			case 4:
				score = 2;
				break;
			case 5:
				score = 1;
				break;

			default:
				score = 5;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
}
