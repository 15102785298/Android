package javaThreadTest;

public class vectorModel {	
	static int a[] ={0,0,0,0,0,0,0,0};
	
	public static int[] getVector(){
		return a;
	}
	
	public static void setVector(int[] vector){
		for(int i=0;i<8;i++){
			a[i]=vector[i];
		}
	}
	
	public void show(){
		for(int i=0;i<8;i++){
			System.out.println(a[i]);
		}
	}

}
