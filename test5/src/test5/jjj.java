package test5;

import java.io.IOException;

public class jjj {

	public static int Rev(int inputNum){
		int res=0;
		String sbf=""+inputNum+"";
		System.out.println(inputNum);
		StringBuffer resString=new StringBuffer();
		
		for(int i=0;i<sbf.toString().length();i++){
			
		}
	//	res=Integer.parseInt(resString.toString());
		return res;
	}
	public static void main(String[] args) {
		int input = 0;
		try {
			input = System.in.read();
			System.out.println(input);
			Rev(input);
		} catch (IOException e) {
			System.out.println("ÇëÊäÈëÕûÊý£¡");
			e.printStackTrace();
		}
		
		
	}

}
