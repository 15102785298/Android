package javaThreadGroup;

import java.util.Scanner;


public class test {

	class mm{
		public int a;
	}
	public static void main(String[] args){
		test t=new test();
		mm a=t.new mm();
		mm b=t.new mm();
		a.a=50;
		b.a=888;
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		a.a=8888;
		System.out.println(a.hashCode());

	}

}
