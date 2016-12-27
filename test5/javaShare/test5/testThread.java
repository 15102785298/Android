package test5;

public class testThread extends Thread {

	ShareUtils su;
	String s;

	public testThread(ShareUtils obj) {
		su = obj;
	}

	@Override
	public void run() {
		su.setShareObj(s);
		int count=0;
		while(count<5){
			try {
				Thread.sleep(500);
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s);
			
		}
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getRse() {
		return 1;
	}

}
