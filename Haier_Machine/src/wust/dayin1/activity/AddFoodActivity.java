package wust.dayin1.activity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import wust.dayin1.DAO.Service;
import wust.dayin1.enity.Food;
import wust.dayin1.tools.ActivitySelector;
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

public class AddFoodActivity extends Activity implements OnClickListener {

	private TextView tv_back;
	private TextView add_pic;
	private EditText foodName;
	private EditText level;
	private EditText time;
	private EditText effect;
	private EditText mainstep;
	private EditText content;
	private ImageView img;
	private TextView tv_add;
	private File phoneFile;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food);
		init();
	}
	//初始化
	private void init() {
		tv_back = (TextView) findViewById(R.id.tv_shopbus_back);
		add_pic = (TextView) findViewById(R.id.tv_add_pic);
		foodName = (EditText) findViewById(R.id.et_foodName);
		level = (EditText) findViewById(R.id.et_level);
		time = (EditText) findViewById(R.id.et_time);
		effect = (EditText) findViewById(R.id.et_effect);
		mainstep = (EditText) findViewById(R.id.et_foodskill);
		content = (EditText) findViewById(R.id.et_foodcontent);
		img = (ImageView) findViewById(R.id.img);
		tv_add = (TextView) findViewById(R.id.tv_add);
		tv_back.setOnClickListener(this);
		tv_add.setOnClickListener(this);
		add_pic.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_shopbus_back:
			ActivitySelector.closeActivity(AddFoodActivity.this);
			break;
		case R.id.tv_add:
			if (foodName.getText().toString().equals("")
					|| level.getText().toString().equals("")
					|| time.getText().toString().equals("")
					|| effect.getText().toString().equals("")
					|| mainstep.getText().toString().equals("")
					|| content.getText().toString().equals("")) {
				Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
			} else {
				Service service = new Service(getApplicationContext());
				boolean flag = service.save(getFood());
				if (flag) {
					Intent i = new Intent(AddFoodActivity.this,
							NativeFoodActivity.class);
					startActivity(i);
					Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "添加错误", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.tv_add_pic:
			Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			phoneFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".jpg");
			iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
			startActivityForResult(iimg, 1);
			break;
		default:
			break;
		}
	}
	//获取食物信息
	private Food getFood() {
		Food food = new Food();
		food.setName(foodName.getText().toString());
		food.setTime(time.getText().toString());
		food.setLevel(level.getText().toString());
		food.setPath(phoneFile + "");
		food.setEffect(effect.getText().toString());
		food.setStep(mainstep.getText().toString());
		food.setContent(content.getText().toString());
		return food;
	}
	
	//获取时间
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
			Bitmap bitmap = BitmapFactory.decodeFile(phoneFile
					.getAbsolutePath());
			img.setImageBitmap(bitmap);
		}
	}

}
