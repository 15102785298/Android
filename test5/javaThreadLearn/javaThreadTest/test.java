package javaThreadTest;

class test {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		int a[]={0,1,2,3,4,5,6,7};
		int b[]={0,1,2,3,4,5,6,7};
		vectorModel v1=new vectorModel();
		vectorModel v2=new vectorModel();
		
		v1.setVector(a);
		v2.setVector(b);
		
		vectorModel res=vectorAdd.Add(v1, v2);
		
		res.show();
	}
        
}