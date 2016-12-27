package test5;

import java.lang.reflect.InvocationTargetException;

public class testHeap {

	public static void main(String[] args) {
		bigHeadHeap hh = new bigHeadHeap();
		try {
			hh.add(new modle(0));
			hh.add(new modle(9));
			hh.add(new modle(5));
			hh.add(new modle(7));
			hh.add(new modle(6));
			hh.add(new modle(4));
			hh.add(new modle(8));
			hh.getMaxObj();
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
