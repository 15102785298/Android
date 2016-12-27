package javaThreadGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test2 {
	public static String getDic(List args){
		ArrayList<String> list=new ArrayList<String>();
		list.addAll(args);
	

		System.out.println(list.toString());
		return list.toString();
		}
	public static void main(String[] args){
		List a =new ArrayList();
		for(int i=1;i<100;i++)
			a.add(i);
		getDic(a);
	}
}
