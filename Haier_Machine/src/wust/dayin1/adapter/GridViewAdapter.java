package wust.dayin1.adapter;

import java.util.List;

import wust.dayin1.enity.Enity;
import wust.dayin1.tools.LoadImagesTask;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haier_machine.R;

public class GridViewAdapter extends BaseAdapter {
	LayoutInflater inflater;
	List<Enity> pkInfos;// 数据源

	// 框架，很重要
	public GridViewAdapter(Context context, List<Enity> packageInfos) {
		inflater = LayoutInflater.from(context);
		this.pkInfos = packageInfos;
	}

	@Override
	public int getCount() {// 循环次数
		// TODO Auto-generated method stub
		return pkInfos.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return pkInfos.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;// 注意position是怎么去变化的，conertview就是当前“在”刷新的每一项

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.gv_item, null);

			holder.label = (TextView) convertView
					.findViewById(R.id.gv_item_appname);
			holder.icon = (ImageView) convertView
					.findViewById(R.id.gv_item_icon);
			holder.icon.setImageBitmap(BitmapFactory.decodeResource(null,
					R.drawable.loading));

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.icon.setImageBitmap(BitmapFactory.decodeResource(null,
					R.drawable.loading));
		}

		holder.label.setText(pkInfos.get(position).getFood_name());

		new LoadImagesTask(holder.icon).execute(pkInfos.get(position)
				.getFood_pic());
		return convertView;
	}

	class ViewHolder {
		ImageView icon;
		TextView label;
	}

}
