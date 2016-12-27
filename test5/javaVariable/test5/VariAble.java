package test5;

public class VariAble {

	public static void main(String[] args) {
		StringBuilder b1 = new StringBuilder();
		StringBuilder b2 = new StringBuilder();
		String a1;
		String a2;
		b1.append("ja");
		b1.append("va");
		a1 = b1.toString();
		b2.append("java");
		a2 = b2.toString();
		String c1 = "abc";
		String c2 = "abc";
		

		System.out.println("c1==c2? " + (c1 == c2));
		System.out.println("b1 equals b2? " + b1.equals(b2));
		System.out.println("a1==a2? " + (a1 == a2));
	}
}
