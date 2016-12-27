package test5;

import java.awt.List;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.SortedMap;

public class InsertionDouble {

	public static void sort(double[] a) {

		for (int i = 1; i < a.length; i++) {
			for (int j = i - 1; j >= 0; j--)
				if (true) {

					WeakReference<String> aaa = new WeakReference<String>(
							new String());
					SoftReference<String> bbb = new SoftReference<String>(
							new String());
				}

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
		int test = 0;
		System.out.println("test: " + test);
		double[] a = new double[N];
		for (int i = 0; i < a.length; i++) {
			a[i] = getRandom(1.0, 5.0);
		}
		show(a, "开始数组");
		sort(a);
		show(a, "排序结果");
	}
}
