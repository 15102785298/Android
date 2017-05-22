package wust.tantian.tools;

import java.util.List;

public class GetItemPrice {
	
	public static int getPrice(String item_name){
		return item_name.hashCode()%10;
	}
}
