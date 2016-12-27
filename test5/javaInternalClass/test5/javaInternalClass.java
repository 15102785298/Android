package test5;

/**
 * java内部类
 * 
 * 内部类就是定义在一个类内部的类，那么为什么要使用内部类呢？主要原因有以下几点：第一，内部类中定义的方法能访问到它所在外部类的私有属性及方法；第二，
 * 外部类无法实现对同一包中的其他类隐藏，而内部类可以做到这一点；第三，匿名内部类在我们只需使用该类的实例依次时可以有效减少我们的代码量
 */
public class javaInternalClass {

	private int OuterClassPI = 50;

	/**
	 * 在外部类中定义一个内部类实例，并调用内部类的方法，注意，内部类不会随着方法的结束而销毁
	 */
	public void OuterShow() {
		InternalClassToPrivate incc = this.new InternalClassToPrivate();// 用this.new更加明显
		// 显示的将内部类持有的外围类引用指向其它的外围类对象，假设outerObject是一个Outer类实例
		// Outer.Inner inner = outerObject.new Inner();
		incc.showOuterPi();
	}

	/**
	 * 也可以调用jic的main方法，会出现栈溢出
	 * */
	public static void main(String[] args) {
		javaInternalClass jic = new javaInternalClass();

		String s[] = { "dd", "ddd" };
		jic.OuterShow();
	}

	/**
	 * 内部类访问外部类的私有方法
	 * */
	private class InternalClassToPrivate {
		public void showOuterPi() {
			System.out.println(javaInternalClass.this.OuterClassPI);// 实际上内部类隐式持有了一个外部类的引用，可能会导致内存泄漏
		}
	}
}
