package test5;

/**
 * java局部类
 * 
 * 局部类的作用域就被限制在定义它的方法的方法体中，因此它不能用public或private访问修饰符来修饰。
 * 与常规内部类比较，局部类具有一个优势：可以访问局部变量
 * 。但是这有一个限制，就是它访问的局部变量必须被声明为final。简单地说，这是出于一致性的考虑。因为局部变量的生命周期随着方法的运行结束也随之结束了
 * ，而局部类的生命周期却不会随着方法的结束而结束。在方法运行完后，局部类为了能够继续访问局部变量，需要对局部变量进行备份。
 * 实际上，在创建局部类的对象时，编译器会隐式修改具备类的构造器
 * ，并将局部类要访问的“外部变量”作为参数传递给它，这样具备类可以在其内部创建一个拷贝并存储在自己的实例域中
 * 。设想若这个变量不是final的，即我们可以在具备类对它进行修改
 * ，这无疑会破坏数据的一致性（局部变量与其在局部类内部的拷贝版本不一样）。所以想让局部类访问的变量必须加上final修饰符。
 */
public class PartialInClass {
	private int testInP = 5;

	// 定义一个方法
	private void testPatrInClass() {
		int aa = 555;
		class InClass {// 在方法中定义一个局部类
			public int a = 55;

			public void show() {
				System.out.println(a);// 显示自身的参数
				System.out.println(aa);// 显示方法中定义的参数
				System.out.println(testInP);// 显示外部类中定义的私有参数
				// aa=40;
				// 此句不通，原因是在局部类中，实际上这个aa是以参数的形式传给局部类的，
				// 所以为了保证参数的一致性，aa必须定义为final，此时是隐式定义的
				
				System.out.println(this.hashCode());

			}
		}
		InClass ic = new InClass();
		ic.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartialInClass pic = new PartialInClass();
		pic.testPatrInClass();
	}

}
