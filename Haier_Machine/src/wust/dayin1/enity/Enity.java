package wust.dayin1.enity;

import cn.bmob.v3.datatype.BmobFile;

public class Enity {
	private String food_name;
	private BmobFile food_pic;
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public BmobFile getFood_pic() {
		return food_pic;
	}
	public void setFood_pic(BmobFile food_pic) {
		this.food_pic = food_pic;
	}	
}
