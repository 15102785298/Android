package wust.tantian.enity;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject{

	private String food_name;
	private String content;
	private int price = -1;
	private int num = -1;
	private int all_pay = -1;
	private String address;
	private String phone;
	private String username;
	private int status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String user_name) {
		this.username = user_name;
	}
	
	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getContent() {
		if (content != null)
			return content;
		else
			return "error";
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		if (price != -1)
			return price;
		else
			return 0;

	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNum() {
		if (num != -1)
			return num;
		else
			return 0;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAll_pay() {
		if (all_pay != -1)
			return all_pay;
		else
			return 0;
	}

	public void setAll_pay(int all_pay) {
		this.all_pay = all_pay;
	}

	public String getAddress() {
		if (address != null)
			return address;
		else
			return "error";
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		if (phone != null)
			return phone;
		else
			return "error";
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
