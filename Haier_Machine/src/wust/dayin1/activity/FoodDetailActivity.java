package wust.dayin1.activity;

import java.io.File;

import wust.dayin1.DAO.DBhelper;
import wust.dayin1.tools.DialogDemo;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class FoodDetailActivity extends Activity implements OnClickListener {
	public TextView tv_fooddetail_back;
	public TextView tv_detail_foodname;
	public TextView tv_detail_skill; // 步骤
	public TextView tv_detail_effect;
	public TextView tv_detail_level;
	public TextView tv_detail_comment;// 数组加上循环加上监听器
	public TextView tv_detail_origin; // 食材
	public TextView tv_fooddetail_cook;
	ImageView iv_food;
	Button btn_share, btn_order;
	// 本地数据
	int[] pics = { R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4,
			R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8 };
	String[] strs = { "琥珀桃仁", "奶香爆米花", "蚝油南瓜", "榛仁巧克力牛轧糖", "微波蒸栗子", "奶油玉米",
			"蒜香排骨", "意大利披萨" };
	String[] contents = { "桃仁，糖", "大米，牛奶", "南瓜，耗油，葱", "巧克力，糖，榛仁", "栗子，糖",
			"玉米，奶油", "排骨，大蒜，葱", "面粉，起司，肉馅" };
	String[] levels = { "简单", "中等", "简单", "较难", "简单", "简单", "较难", "简单" };
	String[] effects = { "健胃", "健胃", "美容", "健胃", "美容", "健胃", "健脾", "健胃" };
	String[] steps = {
			"1.将核桃仁放入微波炉中,中高火叮2分钟,将核桃仁放入微波炉中,中高火 2.取出核桃仁加入糖、蜂蜜、黄油搅拌均匀 3.再入微波炉叮2分钟,取出即可",
			"1.锅内放黄油,把黄油融化 2.放入爆裂玉米,搅拌均匀,使玉米都沾上黄油 3.放入细砂糖搅拌均匀 4.放入微波炉，高火加热1分钟左右,至爆米花全部爆开即可",
			"1.将南瓜洗净，切除外皮，将中心部位的瓜瓤软肉层用不锈锅勺挖除 2.将南瓜洗净，切除外皮，将中心部位的瓜瓤软肉层用不锈锅勺挖除 3.大蒜粒去皮洗净，放置案板切成蒜茸，将蒜茸放入大碗中，加入蚝油、盐、色拉油和香麻油拌匀 4放入微波炉大火“叮”5分钟至熟取出，小葱洗净切末撒上即成 ",
			"1.棉花糖和黄油一起放微波炉叮1分钟，黄油放在碗底容易彻底融化 2.倒入可可粉和奶粉的混合物 3拌匀成面团，加入榛子仁，放在油纸上，整理成长条状 4凉后切成条状即可",
			"1.用刀在头上划十字的刀花 2将准备好的栗子放入微波炉，然后在储水盒加入纯净水，关门 3蒸好的栗子十字刀花已经打开，煞是喜人，可以看到里面金灿灿的栗子肉了",
			"1.玉米切段，连同黄油和白糖一起放入可微波的碗里，加入与其持平的白开水 2.放入微波炉，叮1分钟，即可",
			"1.排骨用流动水反复冲洗干净；大蒜剁成蒜末；料酒、盐备好 2.排骨冷水入锅，烧开后汆烫3分钟捞出，用清水洗净 3.排骨拌入盐、料酒、蒜蓉 4充分混合均匀，覆盖上保鲜膜，放入冰箱腌制2小时以上 4.启动自动锅，选择【收汁•无锡排】",
			"1.牛奶温热(不要超过40度)，放入酵母拌匀静置5分钟，把饼坯材料中除黄油外的所有材料混合揉成光滑的面团，再加黄油揉匀，盖上保鲜膜放在温暖的地方发酵至两倍大 2.和好的面发酵至两倍大后，排气滚圆，松弛20分钟 3.披盘抹薄薄一层橄榄油或黄油 4.撒一层薄薄的马苏里拉奶酪丝，然后铺上火腿片、香肠粒，培根，再均匀放上洋葱、青椒丝、小番茄片 5.烤箱200度预热，中层烤10分钟后取出" };
	String[] times = { "60", "40", "80", "100", "60", "40", "40", "80" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_detail);
		initView();
		initData();
	}

	public void initView() {
		tv_detail_foodname = (TextView) findViewById(R.id.tv_detail_foodname);
		tv_fooddetail_back = (TextView) findViewById(R.id.tv_fooddetail_back);
		tv_detail_level = (TextView) findViewById(R.id.tv_details_level);
		tv_detail_skill = (TextView) findViewById(R.id.tv_detail_skill);
		tv_detail_origin = (TextView) findViewById(R.id.tv_detail_origin);
		tv_detail_effect = (TextView) findViewById(R.id.tv_detail_effect);
		tv_detail_comment = (TextView) findViewById(R.id.tv_detail_comment);
		iv_food = (ImageView) findViewById(R.id.iv_food);
		btn_share = (Button) findViewById(R.id.btn_fooddetail_share);
		btn_share.setOnClickListener(this);
		btn_order = (Button) findViewById(R.id.btn_fooddetail_order);
		btn_order.setOnClickListener(this);
		tv_detail_origin.setOnClickListener(this);
		tv_detail_skill.setOnClickListener(this);
		tv_fooddetail_cook = (TextView) findViewById(R.id.tv_fooddetail_cook);
		tv_fooddetail_cook.setOnClickListener(this);
		tv_fooddetail_back.setOnClickListener(this);
		tv_detail_comment.setOnClickListener(this);
	}

	private void initData() {
		if (getIntent().getStringExtra("id") == null) {
			tv_detail_foodname.setText(getIntent()
					.getStringExtra(DBhelper.NAME));
			tv_detail_level.setText(getIntent().getStringExtra(DBhelper.LEVEL));
			tv_detail_effect.setText(getIntent()
					.getStringExtra(DBhelper.EFFECT));
			Bitmap bitmap = BitmapFactory.decodeFile(getIntent()
					.getStringExtra(DBhelper.PATH));
			iv_food.setImageBitmap(bitmap);
		} else {
			for (int i = 0; i < 8; i++) {
				if (getIntent().getStringExtra("id").equals("" + i)) {
					tv_detail_foodname.setText(strs[i]);
					tv_detail_level.setText(levels[i]);
					tv_detail_effect.setText(effects[i]);
					iv_food.setImageResource(pics[i]);
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_fooddetail_back:
			startActivity(new Intent(FoodDetailActivity.this,
					CloudActivity.class));
			break;
		case R.id.tv_fooddetail_cook:
			Intent i = new Intent(FoodDetailActivity.this,
					ControlActivity.class);
			if (getIntent().getStringExtra("id") == null) {
				i.putExtra(DBhelper.NAME,
						getIntent().getStringExtra(DBhelper.NAME));
				i.putExtra(DBhelper.STEP,
						getIntent().getStringExtra(DBhelper.STEP));
				i.putExtra(DBhelper.TIME,
						getIntent().getStringExtra(DBhelper.TIME));
				i.putExtra(DBhelper.CONTENT,
						getIntent().getStringExtra(DBhelper.CONTENT));
				i.putExtra(DBhelper.LEVEL,
						getIntent().getStringExtra(DBhelper.LEVEL));
				i.putExtra(DBhelper.PATH,
						getIntent().getStringExtra(DBhelper.PATH));
				i.putExtra(DBhelper.EFFECT,
						getIntent().getStringExtra(DBhelper.EFFECT));
			} else {
				for (int j = 0; j < 8; j++) {
					if (getIntent().getStringExtra("id").equals("" + j)) {
						i.putExtra("id", "" + j);
						i.putExtra(DBhelper.NAME, strs[j]);
						i.putExtra(DBhelper.STEP, steps[j]);
						i.putExtra(DBhelper.TIME, times[j]);
						i.putExtra(DBhelper.CONTENT, contents[j]);
						i.putExtra(DBhelper.LEVEL, levels[j]);
						i.putExtra(DBhelper.EFFECT, effects[j]);
					}
				}
			}
			startActivity(i);
			break;
		case R.id.tv_detail_skill:
			if (getIntent().getStringExtra("id") == null) {
				DialogDemo.builder2(this, "技巧",
						getIntent().getStringExtra(DBhelper.STEP));
			} else {
				for (int j = 0; j < 8; j++) {
					if (getIntent().getStringExtra("id").equals("" + j)) {
						String step[] = steps[j].split(" ");
						String step_ = "";
						for (int k = 0; k < step.length; k++)
							step_ = step_ + step[k] + "\n";
						DialogDemo.builder2(this, "技巧", step_);
					}
				}
			}
			break;
		case R.id.tv_detail_origin:
			if (getIntent().getStringExtra("id") == null) {
				DialogDemo.builder_buyfood(this, "用料", getIntent()
						.getStringExtra(DBhelper.CONTENT), getIntent()
						.getStringExtra(DBhelper.NAME));
			} else {
				for (int j = 0; j < 8; j++) {
					if (getIntent().getStringExtra("id").equals("" + j)) {
						DialogDemo.builder_buyfood(this, "用料", contents[j],
								getIntent().getStringExtra(DBhelper.NAME));
					}
				}
			}
			break;
		case R.id.btn_fooddetail_share:
			shareMsg("Test", "水煮肉片", "味道不错", null);
			break;
		case R.id.tv_detail_comment:
			startActivity(new Intent(FoodDetailActivity.this,
					ComentFoodActivity.class));
			break;
		case R.id.btn_fooddetail_order:
			Intent intent = new Intent(FoodDetailActivity.this,
					BuyFoodActivity.class);
			if (getIntent().getStringExtra("id") == null) {
				intent.putExtra(DBhelper.CONTENT,
						getIntent().getStringExtra(DBhelper.CONTENT));
				intent.putExtra(DBhelper.NAME,
						getIntent().getStringExtra(DBhelper.NAME));
			} else {
				for (int j = 0; j < 8; j++) {
					if (getIntent().getStringExtra("id").equals("" + j)) {
						intent.putExtra(DBhelper.CONTENT, contents[j]);
						intent.putExtra(DBhelper.NAME, strs[j]);
					}
				}
			}
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	public void shareMsg(String activityTitle, String msgTitle, String msgText,
			String imgPath) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals("")) {
			intent.setType("text/plain"); // 纯文本
		} else {
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile()) {
				intent.setType("image/jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u);
			}
		}
		intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(Intent.EXTRA_TEXT, msgText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, activityTitle));
	}

}
