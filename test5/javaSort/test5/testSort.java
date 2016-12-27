package test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testSort {

	public static void main(String[] args) {
		sortModle so = new sortModle();
		boolean bo = true;

		try {
			bo=Validate.validate(so);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bo);
		


		/*
		 * sortModle[] sortmodle = new sortModle[10]; sortUtils sutl = new
		 * sortUtils(); sortmodle[0] = new sortModle();
		 * sortmodle[0].setMsg("this is 0"); sortmodle[0].setSortId(5);
		 * 
		 * sortmodle[1] = new sortModle(); sortmodle[1].setMsg("this is 1");
		 * sortmodle[1].setSortId(9);
		 * 
		 * sortmodle[2] = new sortModle(); sortmodle[2].setMsg("this is 1");
		 * sortmodle[2].setSortId(10);
		 * 
		 * sortmodle[3] = new sortModle(); sortmodle[3].setMsg("this is 1");
		 * sortmodle[3].setSortId(-1);
		 * 
		 * sortmodle[4] = new sortModle(); sortmodle[4].setMsg("this is 1");
		 * sortmodle[4].setSortId(99);
		 * 
		 * for (int i = 0; i < 5; i++)
		 * System.out.println(sortmodle[i].getSortId());
		 * 
		 * // sortUtils.popSort(sortmodle); Arrays.sort(sortmodle);
		 * System.out.println(Arrays.toString(sortmodle));
		 * 
		 * for (int i = 0; i < 5; i++)
		 * System.out.println(sortmodle[i].getSortId());
		 */
	}

}
