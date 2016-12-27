package ExcelRead;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class num {
	public static void main(String[] args) {
		int x = 0, n = 0, num = 0,k=0;
		try {
			n = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (n <= 0) {
			System.out.println("ÊäÈëÓÐÎó£¡");
			return;
		}
		for (k = 1; k <= n; k++) {
		    x=k;
			String sb = new String(Integer.toBinaryString(x));
			int a = 0;
			for (int i = 0; i <= sb.length()-1; i++) {
				a = a + Integer.parseInt(String.valueOf(sb.charAt(i)));
			}
			int a2 = 0;
			while (x != 0) {
				a2 = x % 10 + a2;
				x /= 10;
			}
			if (a == a2) {
				num++;
			}
		}
		System.out.println(num);
	}
}
