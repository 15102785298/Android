package test5;

import java.util.HashMap;

public class FileSystem {

	// String
	public static void main(String[] args) {
		Object a = "";
		if (true) {
			System.out.println("a");
			HashMap<String, String> map=new HashMap<String, String>(4,0.75f);
			map.put("1", "1");
			map.put("1", "2");
			System.out.println(map.get("1"));
			System.out.println(map.get("1"));
		}
	}
}
