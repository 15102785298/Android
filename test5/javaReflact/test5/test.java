package test5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import testModle.human;

public class test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		human cw = new human();
		Method m[];

		javaReflectUtils jr = new javaReflectUtils();
		jr.initByObj(cw);
		m = jr.getAllMethod();
		System.out.println(jr.getClassName());
		try {
			System.out.println(m[0].invoke(cw));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(m[0].getName());
		// System.out.println(m[1].getName());
		// System.out.println(m[4].getName());
		// System.out.println(jr.getAllMethod().length);
		// System.out.println(jr.getAllSuperMethod().length);
		// System.out.println(jr2.getClassName());
	}

}
