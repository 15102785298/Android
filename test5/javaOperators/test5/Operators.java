package test5;

public class Operators {

	/**
	 * 在第二个参数是2的倍数时可以用该方法快速取模
	 * 
	 * @param int int
	 * @return int
	 */
	public static int module(int x, int y) {
		return x & (y - 1);
	}
}
