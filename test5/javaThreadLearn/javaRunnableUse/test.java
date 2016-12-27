package javaRunnableUse;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		saleRunnable s = new saleRunnable();
		s.setTicket(50);

		new Thread(s).start();
		new Thread(s).start();
		new Thread(s).start();
	}

}
