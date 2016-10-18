package wust.dayin1.adapter;

import com.example.haier_machine.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
	import android.view.ViewGroup;
	import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyFoodAdapter extends BaseAdapter {

	private Context context;
	private Cursor cursor;
	private LinearLayout layout;

	public MyFoodAdapter(Context context, Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
	}

	public Cursor getCursor() {
		return cursor;
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		layout = (LinearLayout) inflater.inflate(R.layout.food_list_cell, null);
		TextView tv_foodName = (TextView) layout.findViewById(R.id.food_name);
		TextView tv_effect = (TextView) layout.findViewById(R.id.effect);
		ImageView img = (ImageView) layout.findViewById(R.id.img_food);
		cursor.moveToPosition(position);
		String foodName = cursor.getString(cursor.getColumnIndex("name"));
		String effect = cursor.getString(cursor.getColumnIndex("effect"));
		String url = cursor.getString(cursor.getColumnIndex("path"));
		System.out.println(url);
		tv_foodName.setText(foodName);
		tv_effect.setText(effect);
		img.setImageBitmap(getPictureThumbnail(url, 200, 200));
		return layout;
	}

	public static Bitmap getPictureThumbnail(String uri, int width, int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(uri, options);
		options.inJustDecodeBounds = false;
		int beWidth = options.outWidth / width;
		int beHeight = options.outHeight / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(uri, options);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

}
