package wust.dayin1.adapter;

import java.util.List;

import wust.dayin1.enity.Food;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haier_machine.R;

public class MyFoodAdapter2 extends BaseAdapter {

	LayoutInflater inflater;
	private List<Food> datas;

	public MyFoodAdapter2(Context context, List<Food> datas) {
		inflater = LayoutInflater.from(context);
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.food_list_cell, parent,
					false);
			holder.img = (ImageView) convertView.findViewById(R.id.img_food);
			holder.name = (TextView) convertView.findViewById(R.id.food_name);
			holder.effect = (TextView) convertView.findViewById(R.id.effect);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Food food = datas.get(position);
		holder.name.setText(food.getName());
		holder.effect.setText(food.getEffect());
		holder.img
				.setImageBitmap(getPictureThumbnail(food.getPath(), 200, 200));
		return convertView;
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

	private class ViewHolder {
		ImageView img;
		TextView name;
		TextView effect;
	}
}
