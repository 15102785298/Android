package wust.dayin1.activity;

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

import wust.dayin1.enity.Community;

public class AddCommunityActivity extends Activity implements OnClickListener {
	private EditText content;
	private EditText title;
	private TextView back;
	private TextView done;
	private String title_;
	private String content_;
	private Button btn_community_sure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_community);
		init();
	}

	private void init() {
		content = (EditText) findViewById(R.id.et_content);
		title = (EditText) findViewById(R.id.et_title);
		back = (TextView) findViewById(R.id.tv_addcommunity_back);
		done = (TextView) findViewById(R.id.tv_done);
		btn_community_sure = (Button) findViewById(R.id.btn_community_sure);
		btn_community_sure.setOnClickListener(this);
		back.setOnClickListener(this);
		done.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_addcommunity_back:
			startActivity(new Intent(AddCommunityActivity.this,
					CommunityActivity.class));
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			break;
		case R.id.tv_done:
			submit_community();
			break;
		case R.id.btn_community_sure:
			submit_community();
			break;
		default:
			break;
		}

	}

	private void submit_community() {
		if (BmobUser.getCurrentUser(getApplicationContext()) == null) {
			Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT)
					.show();
		} else {
			title_ = title.getText().toString();
			content_ = content.getText().toString();
			if (title_.equals("") || content_.equals(""))
				Toast.makeText(AddCommunityActivity.this, "请将信息填写完整",
						Toast.LENGTH_SHORT).show();
			else {
				Community c = new Community();
				c.setTitle(title_);
				c.setContent(content_);
				c.setUserName(BmobUser.getCurrentUser(getApplicationContext())
						.getUsername());
				c.save(this, new SaveListener() {
					@Override
					// 返回成功
					public void onSuccess() {
						startActivity(new Intent(AddCommunityActivity.this,
								CommunityActivity.class));
						Toast.makeText(AddCommunityActivity.this, "添加成功",
								Toast.LENGTH_LONG).show();
					}

					@Override
					// 返回失败
					public void onFailure(int i, String s) {
						Toast.makeText(AddCommunityActivity.this, "添加失败",
								Toast.LENGTH_LONG).show();
					}
				});
			}
		}
	}
}
