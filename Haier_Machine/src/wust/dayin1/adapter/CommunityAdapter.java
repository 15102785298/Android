package wust.dayin1.adapter;

import java.util.List;

import wust.dayin1.enity.Community;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.haier_machine.R;

public class CommunityAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Community> datas;

	public CommunityAdapter(Context context, List<Community> datas) {
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
			convertView = inflater.inflate(R.layout.community_list_cell,
					parent, false);
			holder.title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.content = (TextView) convertView
					.findViewById(R.id.tv_describe);
			holder.userName = (TextView) convertView
					.findViewById(R.id.tv_username);
			holder.time = (TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Community c = datas.get(position);
		holder.title.setText(c.getTitle());
		holder.content.setText(c.getContent());
		holder.userName.setText(c.getUsername());
		holder.time.setText(c.getTime());
		return convertView;
	}

	private class ViewHolder {
		TextView title;
		TextView content;
		TextView userName;
		TextView time;
	}

}
