package test5;

public class BubbleInt {

	private static void sort(int a[]) {
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length - i - 1; j++)
				if (a[j] > a[j + 1])
					exchange(a,j, j + 1);
	}

	private static void exchange(int[] a, int j, int i) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void exchangeNoRom(int[] a, int i, int j) {
		a[i] = a[i] + a[j];
		a[j] = a[i] - a[j];
		a[i] = a[i] = a[j];
	}

	private static int getRandom(int begin, int end) {
		return (int) (begin + Math.random() * end);
	}

	private static void show(int[] a) {
		System.out.println("/*********排序结果*********/");
		for (int item : a) {
			System.out.println(item);
		}
		System.out.println("/*************************/");
	}

	private static void show(int[] a, String msg) {
		System.out.println("/*********" + msg + "*********/");
		for (int item : a) {
			System.out.println(item);
		}
		System.out.println("/*************************/");
	}

	public static void main(String[] args) {
		int N = 20;
		int[] a = new int[N];
		for (int i = 0; i < a.length; i++) {
			a[i] = getRandom(1, 5);
		}
		show(a, "开始数组");
		sort(a);
		show(a, "排序结果");
	}
}
