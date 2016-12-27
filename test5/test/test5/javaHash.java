package test5;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class javaHash {

	public static void main(String[] args) {
		sortModle ss = new sortModle();
		sortModle ss2 = new sortModle();
		System.out.println(ss.hashCode());
		HashMap<sortModle, String> hm = new HashMap<sortModle, String>();
		hm.put(ss, "111");
		hm.put(ss2, "121");
		ss.msg = "asdfasdfsd";
		ss.msg2 = "asdfasdfasd";
		System.out.println(ss.hashCode());
		System.out.println(hm.get(ss));
		System.out.println(hm.get(ss2));

		HashMap<String, String> mp2 = new HashMap<String, String>();
		String a1 = "ab";
		String a2 = "a";
		a2 = a2 + "b";
		System.out.println(a1.hashCode());
		System.out.println(a2.hashCode());
		mp2.put(a1, "222");
		mp2.put(a2, "232");
		System.out.println(mp2.get(a1));
		System.out.println(mp2.get(a2));

		Scanner sc = new Scanner(System.in);
		byte s;
		/*while ((s=sc.next())!=null) {
			try{
			System.out.println(Integer.parseInt(s.toString())+5);}
			catch(Exception e){
				System.out.println("«Î ‰»Î ˝◊÷£°");				
			}
		}*/

		
		while(sc.hasNext()){
			s=sc.nextByte();
			System.out.println(s);
		}
		System.out.println("end");
	}
}
