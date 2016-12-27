package test5;

public class BubbleDouble {

	public static void sort(double[] a) {
		int N = a.length;
		for (int i = 0; i < N - 1; i++)
			for (int j = 0; j < N - i - 1; j++) {
				if (a[j] > a[j + 1])
					exchange(a, j, j + 1);
			}
	}

	private static void exchange(double[] a, int j, int i) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void exchangeNoRom(double[] a, int i, int j) {
		a[i] = a[i] + a[j];
		a[j] = a[i] - a[j];
		a[i] = a[i] = a[j];
	}

	private static double getRandom(double begin, double end) {
		return (double) (begin + Math.random() * end);
	}

	private static void show(double[] a) {
		System.out.println("/*********排序结果*********/");
		for (double item : a) {
			System.out.println(item);
		}
		System.out.println("/*************************/");
	}

	private static void show(double[] a, String msg) {
		System.out.println("/*********" + msg + "*********/");
		for (double item : a) {
			System.out.println(item);
		}
		System.out.println("/*************************/");
	}

	public static void main(String[] args) {
		int N = 20;
		double[] a = new double[N];
		for (int i = 0; i < a.length; i++) {
			a[i] = getRandom(1.0, 5.0);
		}
		show(a, "开始数组");
		sort(a);
		show(a, "排序结果");
	}
}
