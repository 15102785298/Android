package wust.tantian.adapter;

import java.util.List;

import com.example.haier_machine.R;

import wust.tantian.enity.Community;
import wust.tantian.enity.Enity;
import wust.tantian.enity.Order;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private List<Order> data;
	private int size;
	LayoutInflater inflater;

	public OrderAdapter(Context context, List<Order> data) {
		inflater = LayoutInflater.from(context);
		this.data = data;
		size = data.size();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int paramInt) {
		// TODO Auto-generated method stub
		return data.get(paramInt);
	}

	@Override
	public long getItemId(int paramInt) {
		// TODO Auto-generated method stub
		return paramInt;
	}

	@Override
	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		ViewHolder viewHolder;
		if (paramView == null) {
			viewHolder = new ViewHolder();
			paramView = inflater.inflate(R.layout.order_list_cell,
					paramViewGroup, false);
			viewHolder.tv_food_name = (TextView) paramView
					.findViewById(R.id.tv_food_name);
			viewHolder.tv_order_id = (TextView) paramView
					.findViewById(R.id.tv_order_id);
			viewHolder.tv_order_status = (TextView) paramView
					.findViewById(R.id.tv_order_status);
			paramView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) paramView.getTag();
		}
		Order c = data.get(paramInt);
		viewHolder.tv_food_name.setText(c.getFood_name());
		viewHolder.tv_order_id.setText(c.getObjectId());
		switch (c.getStatus()) {
		case 0:
			viewHolder.tv_order_status.setText("已提交");
			break;
		case 1:
			viewHolder.tv_order_status.setText("已接单");
			break;
		case 2:
			viewHolder.tv_order_status.setText("配送中");
			break;
		case 3:
			viewHolder.tv_order_status.setText("已签收");
			break;

		}
		return paramView;
	}

	class ViewHolder {
		TextView tv_food_name;
		TextView tv_order_id;
		TextView tv_order_status;
	}

}
