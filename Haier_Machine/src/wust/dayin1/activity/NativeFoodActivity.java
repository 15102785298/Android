package wust.dayin1.activity;

import java.util.ArrayList;
import java.util.List;

import wust.dayin1.DAO.DBhelper;
import wust.dayin1.DAO.Service;
import wust.dayin1.adapter.MyFoodAdapter;
import wust.dayin1.adapter.MyFoodAdapter2;
import wust.dayin1.enity.Enity;
import wust.dayin1.enity.Food;
import wust.dayin1.tools.ActivitySelector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class NativeFoodActivity extends Activity implements OnClickListener,
		OnItemLongClickListener, TextWatcher {

	private TextView tv_add;
	private TextView tv_back;
	private ListView lv;
	private MyFoodAdapter adapter;
	private DBhelper db;
	private SQLiteDatabase database;
	private Cursor cursor;
	private Service service;
	private EditText et;
	private MyFoodAdapter2 adapter2;
	private List<Food> datas;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nativelocal_food);
		init();
	}

	public void init() {
		db = new DBhelper(NativeFoodActivity.this);
		database = db.getReadableDatabase();
		cursor = database.query("food", null, null, null, null, null, null);
		tv_add = (TextView) findViewById(R.id.tv_fooddetail_add);
		tv_back = (TextView) findViewById(R.id.tv_fooddetail_back);
		et = (EditText) findViewById(R.id.et);
		et.addTextChangedListener(this);
		lv = (ListView) findViewById(R.id.subsoft_listview);
		service = new Service(getApplicationContext());
		tv_add.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(NativeFoodActivity.this,
						FoodDetailActivity.class);
				Enity enity = new Enity();
				enity.setContents(cursor.getString(cursor
						.getColumnIndex(DBhelper.CONTENT)));
				enity.setEffects(cursor.getString(cursor
						.getColumnIndex(DBhelper.EFFECT)));
				enity.setFood_name(cursor.getString(cursor
						.getColumnIndex(DBhelper.NAME)));
				enity.setLevels(cursor.getString(cursor
						.getColumnIndex(DBhelper.LEVEL)));
				enity.setSteps(cursor.getString(cursor
						.getColumnIndex(DBhelper.STEP)));
				enity.setTimes(cursor.getString(cursor
						.getColumnIndex(DBhelper.TIME)));
				enity.setFood_pic(null);
				i.putExtra("pic_path",
						cursor.getString(cursor.getColumnIndex(DBhelper.PATH)));

				i.putExtra("menu", enity);
				startActivity(i);
			}
		});

		lv.setOnItemLongClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_fooddetail_add:
			Intent i = new Intent(NativeFoodActivity.this,
					AddFoodActivity.class);
			startActivity(i);
			break;
		case R.id.tv_fooddetail_back:
			ActivitySelector.closeActivity(NativeFoodActivity.this);
			break;
		default:
			break;
		}
	}

	public void selectDB() {
		cursor = database.query(DBhelper.TABLE_NAME, null, null, null, null,
				null, null);
		adapter = new MyFoodAdapter(this, cursor);
		lv.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		selectDB();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		cursor.moveToPosition(position);
		final int _id = cursor.getInt(cursor.getColumnIndex("_id"));
		AlertDialog.Builder builder = new AlertDialog.Builder(
				NativeFoodActivity.this);
		builder.setTitle("请选择操作");
		// 指定下拉列表的显示数据
		final String[] cities = { "删除", "上传", "修改" };
		// 设置一个下拉的列表选择项
		builder.setItems(cities, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					service.delete(_id);
					selectDB();
					Toast.makeText(getApplicationContext(), "删除成功",
							Toast.LENGTH_LONG).show();
				}
				if (which == 1) {
					Toast.makeText(getApplicationContext(), "正在上传",
							Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), "上传成功",
							Toast.LENGTH_LONG).show();
				}
				if (which == 2) {
					Intent i = new Intent(NativeFoodActivity.this,
							ModifyFoodActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("id", _id);
					i.putExtras(bundle);
					startActivity(i);
				}
			}
		});
		builder.create().show();
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}
	//监听输入的值的变化
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		datas = new ArrayList<Food>();
		service = new Service(getApplicationContext());
		datas = service.fuzzyQuery(et.getText().toString());
		adapter2 = new MyFoodAdapter2(getApplicationContext(), datas);
		lv.setAdapter(adapter2);
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}

}
