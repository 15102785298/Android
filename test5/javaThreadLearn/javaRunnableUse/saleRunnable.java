package javaRunnableUse;

public class saleRunnable implements Runnable {

	private int allTicket = 0;
	private boolean isLive = true;
	private int nowSale = 0;

	@Override
	public void run() {
		sale();
	}

	public void setTicket(int num) {
		this.allTicket = num;
	}
    /**
     * @synchronized �ؼ��ֿ������ڷ����Ķ��߳�ͬ��
     * */
	public synchronized void sale(){
		while (isLive) {
			if (allTicket == 0) {
				System.out.println("Ʊ�ۿ��ˣ�");
				isLive = false;
			} else {
				allTicket--;
				nowSale++;
				System.out.println("������" + nowSale + "��Ʊ��");

			}
		}
	}
}
