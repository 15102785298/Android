package test5;

import java.util.concurrent.Exchanger;

public class SelectionDouble {
	public static void sort(double[] a) {

		for (int i = 0; i < a.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			exchange(a, i, min);
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
