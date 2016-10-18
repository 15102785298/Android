package wust.dayin1.activity;
import java.util.ArrayList;
import java.util.List;
import wust.dayin1.tools.ActivitySelector;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class ComentFoodActivity extends Activity {
	ListView lv_comment_food;
	EditText et_comment;
	TextView tv_foodcomment_back;
	TextView tv_foodcomment_submit;
	ArrayAdapter<String> adapter ;
	Context context;
	String[] strs={"试做了，味道不错，卖相也好！","很简单，看上去不错。",
			"南瓜削皮的时候弄破了手。","吃起来有点咸。","我的蚝油南瓜配料放多了","吃起来有点硬,下一次要挑好南瓜了！"
			,"这款软件真是是不错!"};
	List<String> list=new ArrayList<String>();
	private OnClickListener listener=new OnClickListener() {		
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
					case R.id.tv_foodcomment_back:
						ActivitySelector.closeActivity(ComentFoodActivity.this);
						break;
						
					case R.id.tv_foodcomment_submit:
						String str=et_comment.getText().toString();
						if(!str.equals("")){
							list.add(0, str);
							adapter.notifyDataSetChanged();//底层的观察者模式
							showToast("评论成功");
							et_comment.setText("");
					//		et_comment.setFocusable(false);
						}else{
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
		context=this;
		for(int i=0;i<strs.length;i++){
			list.add(strs[i]);
		}
		et_comment=(EditText)findViewById(R.id.et_commmet);
		lv_comment_food=(ListView)findViewById(R.id.lv_food_commmet);
		adapter= new ArrayAdapter<String>(
	            context, android.R.layout.simple_list_item_1, list);
		lv_comment_food.setAdapter(adapter);
		tv_foodcomment_submit=(TextView)findViewById(R.id.tv_foodcomment_submit);
		tv_foodcomment_back=(TextView)findViewById(R.id.tv_foodcomment_back);
		tv_foodcomment_submit.setOnClickListener(listener);
		tv_foodcomment_back.setOnClickListener(listener);
	}

}
