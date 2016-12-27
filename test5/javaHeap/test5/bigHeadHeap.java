package test5;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Vector;

public class bigHeadHeap {

	private int length = 0;// 目前堆的大小
	private int maxObj = 10;// 该堆仅保存maxObj个数据
	private HeadHeapObj[] objArray = null;
	private modle[] md = new modle[10];

	/**
	 * 向堆中添加数据
	 */
	public void add(Object obj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		if (objArray == null)// 如果堆为空则创建堆
			objArray = new HeadHeapObj[maxObj];

		if (length < maxObj) {// 当堆还没有保存满时
			objArray[length] = new HeadHeapObj();
			objArray[length].setContentObj(obj);
			length++;
			buildMaxHeap();
			show();
		}
	}

	private void adjustDownToTop(int k) {
		HeadHeapObj temp = objArray[k];
		for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1) {
			if (i < length
					&& objArray[i].getHeapObjId() < objArray[i + 1]
							.getHeapObjId()) {
				i++;
			}
			if (temp.getHeapObjId() >= objArray[i].getHeapObjId()) {
				break;
			} else {
				objArray[k] = objArray[i];
				k = i;
			}
		}
		objArray[k] = temp;
	}

	public Object getMaxObj() {
		Object res = null;
		res = objArray[0].getContentObj();
		objArray[0] = objArray[length - 1];
		objArray[length - 1] = null;
		length--;
		adjustDownToTop(0);
		show();
		return res;
	}

	private void buildMaxHeap() {
		for (int i = (length - 2) / 2; i >= 0; i--) {
			adjustDownToTop(i);
		}
	}

	public void show() {

		for (int i = 0; i < length; i++) {
			System.out.println(objArray[i].getHeapObjId());
		}
	}

}
