package wust.tantian.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import wust.tantian.DAO.DBhelper;
import wust.tantian.enity.Enity;
import wust.tantian.tools.DialogDemo;
import wust.tantian.tools.LoadImagesTask;
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
	public TextView tv_detail_comment;// 数组加上循环加上监听�?
	public TextView tv_detail_origin; // 食材
	public TextView tv_fooddetail_cook;
	ImageView iv_food;
	Button btn_share, btn_order;
	Enity enity = null;

	// 本地数据

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

		enity = (Enity) getIntent().getSerializableExtra("menu");
		tv_detail_foodname.setText(enity.getFood_name());
		tv_detail_level.setText(enity.getLevels());
		tv_detail_effect.setText(enity.getEffects());
		String pic_path = enity.getFood_pic();
		if (pic_path.startsWith("http://")) // 网络�?
			new LoadImagesTask(iv_food).execute(pic_path);
		else // 本地�?
		{
			Bitmap pic = BitmapFactory.decodeFile(pic_path);
			iv_food.setImageBitmap(pic);
		}
	}

	/**
	 * 加载本地图片 http://bbs.3gstdy.com
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 从服务器取图
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setConnectTimeout(10000);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
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
			i.putExtra("menu", enity);
			startActivity(i);
			break;
		// case R.id.tv_fooddetail_cook:
		// Intent i = new Intent(FoodDetailActivity.this,
		// ControlActivity.class);
		// if (getIntent().getStringExtra("id") == null) {
		// i.putExtra(DBhelper.NAME,
		// getIntent().getStringExtra(DBhelper.NAME));
		// i.putExtra(DBhelper.STEP,
		// getIntent().getStringExtra(DBhelper.STEP));
		// i.putExtra(DBhelper.TIME,
		// getIntent().getStringExtra(DBhelper.TIME));
		// i.putExtra(DBhelper.CONTENT,
		// getIntent().getStringExtra(DBhelper.CONTENT));
		// i.putExtra(DBhelper.LEVEL,
		// getIntent().getStringExtra(DBhelper.LEVEL));
		// i.putExtra(DBhelper.PATH,
		// getIntent().getStringExtra(DBhelper.PATH));
		// i.putExtra(DBhelper.EFFECT,
		// getIntent().getStringExtra(DBhelper.EFFECT));
		// } else {
		// for (int j = 0; j < 8; j++) {
		// if (getIntent().getStringExtra("id").equals("" + j)) {
		// i.putExtra("id", "" + j);
		// i.putExtra(DBhelper.NAME, strs[j]);
		// i.putExtra(DBhelper.STEP, steps[j]);
		// i.putExtra(DBhelper.TIME, times[j]);
		// i.putExtra(DBhelper.CONTENT, contents[j]);
		// i.putExtra(DBhelper.LEVEL, levels[j]);
		// i.putExtra(DBhelper.EFFECT, effects[j]);
		// }
		// }
		// }
		// startActivity(i);
		// break;
		case R.id.tv_detail_skill:
			if (enity == null) {
				DialogDemo.builder2(this, "�?�?", "数据还未加载进来哦~");
			} else {
				DialogDemo.builder2(this, "�?�?", enity.getSteps());
			}
			break;
		case R.id.tv_detail_origin:
			if (enity == null) {
				DialogDemo.builder2(this, "用料", "数据还未加载进来哦~");
			} else {
				DialogDemo.builder2(this, "�?�?", enity.getContents());
			}
			break;
		case R.id.btn_fooddetail_share:
			if (enity == null) {
				DialogDemo.builder2(this, "提示", "数据还未加载进来哦~");
			} else {
				shareMsg("Test", enity.getFood_name(), "味道不错", null);
			}

			break;
		case R.id.tv_detail_comment:
			if (enity.getId() != null) {
				Intent intent_comment = new Intent(FoodDetailActivity.this,
						ComentFoodActivity.class);
				intent_comment.putExtra("menu", enity);
				startActivity(intent_comment);
			} else {
				DialogDemo.builder2(this, "提示", "本地数据不能评价哦~");
			}
			break;
		case R.id.btn_fooddetail_order:
			Intent intent = new Intent(FoodDetailActivity.this,
					BuyFoodActivity.class);
			if (enity == null) {
				DialogDemo.builder2(this, "提示", "数据还未加载进来哦~");
			} else {
				intent.putExtra("menu", enity);
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
			intent.setType("分享/plain"); 
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
