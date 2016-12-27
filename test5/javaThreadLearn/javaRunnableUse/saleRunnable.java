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
     * @synchronized 关键字可以用于方法的多线程同步
     * */
	public synchronized void sale(){
		while (isLive) {
			if (allTicket == 0) {
				System.out.println("票售空了！");
				isLive = false;
			} else {
				allTicket--;
				nowSale++;
				System.out.println("卖出第" + nowSale + "张票！");

			}
		}
	}
}
