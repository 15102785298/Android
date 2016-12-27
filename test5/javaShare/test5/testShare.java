package test5;

public class testShare {

	public static void main(String[] arg) throws InterruptedException {
		String obj1 = "ddd";
		ShareUtils su = new ShareUtils();
		su.setShareObj(obj1);
		
		testThread th1=new testThread(su);
		testThread th2=new testThread(su);
		
		th1.setS("1111");
		th2.setS("2222");
		
//		th1.run();
//		th2.run();
		
		th1.start();
		th2.start();
		
//		th1.join();
//		th2.join();

		while(true){
			System.out.println("3333");
			th1.join();
			System.out.println(su.getShareObj());
			Thread.sleep(500);
		}
		
	}
}
