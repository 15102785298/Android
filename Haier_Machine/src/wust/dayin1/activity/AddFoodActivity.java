package wust.dayin1.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import wust.dayin1.DAO.Service;
import wust.dayin1.enity.Food;
import wust.dayin1.tools.ActivitySelector;
import wust.dayin1.tools.ImageUtils;
import wust.dayin1.tools.NumberUtil;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class AddFoodActivity extends Activity implements OnClickListener {

	private TextView tv_back;
	private ImageView iv_add_pic;
	private EditText foodName;
	private EditText level;
	private EditText time;
	private EditText effect;
	private EditText mainstep;
	private EditText content;
	private TextView tv_add;
	private String pic_path;
	private Button btn_add_food;
	private String url;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food);
		init();
	}

	// ��ʼ��
	private void init() {
		tv_back = (TextView) findViewById(R.id.tv_shopbus_back);
		iv_add_pic = (ImageView) findViewById(R.id.iv_add_pic);
		foodName = (EditText) findViewById(R.id.et_foodName);
		level = (EditText) findViewById(R.id.et_level);
		time = (EditText) findViewById(R.id.et_time);
		effect = (EditText) findViewById(R.id.et_effect);
		mainstep = (EditText) findViewById(R.id.et_foodskill);
		content = (EditText) findViewById(R.id.et_foodcontent);
		tv_add = (TextView) findViewById(R.id.tv_add);
		btn_add_food = (Button) findViewById(R.id.btn_add_food);
		tv_back.setOnClickListener(this);
		tv_add.setOnClickListener(this);
		iv_add_pic.setOnClickListener(this);
		btn_add_food.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_shopbus_back:
			ActivitySelector.closeActivity(AddFoodActivity.this);
			break;
		case R.id.tv_add:
			submit();
			break;
		case R.id.iv_add_pic:
			ImageUtils.showImagePickDialog(AddFoodActivity.this);
			break;
		case R.id.btn_add_food:
			submit();
			break;
		default:
			break;
		}
	}

	private void submit() {
		if (foodName.getText().toString().equals("")
				|| level.getText().toString().equals("")
				|| time.getText().toString().equals("")
				|| effect.getText().toString().equals("")
				|| mainstep.getText().toString().equals("")
				|| content.getText().toString().equals("") || pic_path == null) {
			Toast.makeText(this, "�뽫��Ϣ��д����", Toast.LENGTH_SHORT).show();
		} else if (NumberUtil.isInteger(time.getText().toString())) {
			Service service = new Service(getApplicationContext());
			boolean flag = service.save(getFood());
			if (flag) {
				Intent i = new Intent(AddFoodActivity.this,
						NativeFoodActivity.class);
				startActivity(i);
				Toast.makeText(this, "��ӳɹ�", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "��Ӵ���", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "ʱ�����Ϊ���֣�", Toast.LENGTH_SHORT).show();
		}
	}

	// ��ȡʳ����Ϣ
	private Food getFood() {

		Food food = new Food();
		food.setName(foodName.getText().toString());
		food.setTime(time.getText().toString());
		food.setLevel(level.getText().toString());
		food.setPath(pic_path);
		food.setEffect(effect.getText().toString());
		food.setStep(mainstep.getText().toString());
		food.setContent(content.getText().toString());
		return food;
	}

	// ��ȡʱ��
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		return str;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		iv_add_pic = (ImageView) findViewById(R.id.iv_add_pic);
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor actualimagecursor;
		int actual_image_column_index;
		switch (requestCode) {
		case ImageUtils.REQUEST_CODE_FROM_ALBUM: // ���
			Uri imageUri = data.getData();
			iv_add_pic.setImageURI(imageUri);

			actualimagecursor = managedQuery(imageUri, proj, null, null, null);
			actual_image_column_index = actualimagecursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			actualimagecursor.moveToFirst();
			pic_path = actualimagecursor.getString(actual_image_column_index);
			break;
		case ImageUtils.REQUEST_CODE_FROM_CAMERA: // ���
			Uri imageUriFromCamera = ImageUtils.imageUriFromCamera;
			iv_add_pic.setImageURI(imageUriFromCamera);
			actualimagecursor = managedQuery(imageUriFromCamera, proj, null,
					null, null);
			actual_image_column_index = actualimagecursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			actualimagecursor.moveToFirst();
			pic_path = actualimagecursor.getString(actual_image_column_index);
			break;
		default:
			break;
		}
	}
}
