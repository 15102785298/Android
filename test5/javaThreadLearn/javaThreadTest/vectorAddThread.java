package javaThreadTest;

public class vectorAddThread extends Thread {

	private int res = 0;

	public vectorAddThread(int a, int b) {
		this.res = vectorAdd.Add(a, b);
	}

	@Override
	public void run() {

	}

	public int getRse() {
		return res;
	}
}
