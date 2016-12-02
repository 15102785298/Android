package wust.dayin1.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import wust.dayin1.DAO.Service;
import wust.dayin1.adapter.MyFoodAdapter;
import wust.dayin1.enity.Food;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class ModifyFoodActivity extends Activity implements OnClickListener {

	private EditText et_foodName;
	private EditText et_foodSkill;
	private EditText et_time;
	private EditText et_foodContent;
	private EditText et_foodEffect;
	private EditText et_foodLevel;
	private Service service;
	private TextView tv_cancel;
	private TextView tv_save;
	private TextView tv_replacePic;
	private ImageView img;
	private File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_food);
		intiView();
		initData();
	}

	private void intiView() {
		service = new Service(getApplicationContext());
		et_foodName = (EditText) findViewById(R.id.et_foodName);
		et_foodSkill = (EditText) findViewById(R.id.et_foodskill);
		et_time = (EditText) findViewById(R.id.et_time);
		et_foodContent = (EditText) findViewById(R.id.et_foodcontent);
		et_foodEffect = (EditText) findViewById(R.id.et_effect);
		et_foodLevel = (EditText) findViewById(R.id.et_level);
		img = (ImageView) findViewById(R.id.img);

		tv_cancel = (TextView) findViewById(R.id.tv_cancel);
		tv_save = (TextView) findViewById(R.id.tv_save);
		tv_replacePic = (TextView) findViewById(R.id.tv_replace_pic);

		tv_save.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
		tv_replacePic.setOnClickListener(this);
	}

	private void initData() {
		int id = this.getIntent().getExtras().getInt("id");
		Food food = new Food();
		food = service.getById(id);
		et_foodName.setText(food.getName());
		et_foodSkill.setText(food.getStep());
		et_time.setText(food.getTime());
		et_foodContent.setText(food.getContent());
		et_foodEffect.setText(food.getEffect());
		et_foodLevel.setText(food.getLevel());
		img.setImageBitmap(MyFoodAdapter.getPictureThumbnail(food.getPath(),
				200, 200));
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tv_cancel:
			finish();
			break;
		case R.id.tv_save:
			Food food = new Food();
			food.setName(et_foodName.getText().toString());
			food.setStep(et_foodSkill.getText().toString());
			food.setTime(et_time.getText().toString());
			food.setContent(et_foodContent.getText().toString());
			food.setEffect(et_foodEffect.getText().toString());
			food.setLevel(et_foodLevel.getText().toString());
			food.setPath(file + "");
			service.update(food, this.getIntent().getExtras().getInt("id"));
			startActivity(new Intent(ModifyFoodActivity.this,
					NativeFoodActivity.class));
			Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.tv_replace_pic:
			Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			file = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".jpg");
			iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(iimg, 1);
			break;
		default:
			break;
		}
	}

	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		return str;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			img.setImageBitmap(bitmap);
		}
	}
}
