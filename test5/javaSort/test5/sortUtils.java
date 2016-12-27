package test5;

import java.io.Serializable;
import java.util.HashMap;

public class sortUtils implements Serializable{

	private javaReflectUtils jru;
	private String methodname;

	/**************************** 公共方法 ******************************/

	private void changeObj(Object[] objlist, int index1, int index2) {
		Object temp = null;
		temp = objlist[index1];
		objlist[index1] = objlist[index2];
		objlist[index2] = temp;
	}

	private double getSortId(Object obj) {
		double res = (double) jru.invokeMethodByName(obj, methodname);
		return res;
	}

	/**
	 * 设置排序的关键变量，排序将会用传来的方法获取排序的数据，数据应为double
	 * 
	 * @param methodname
	 *            :获取关键变量的方法名
	 */
	public void setSortId(String methodname) {
		this.methodname = methodname;
		jru = new javaReflectUtils();
	}

	/***************************** 冒泡排序 ****************************/

	/**
	 * 对全部数据进行冒泡排序
	 * 
	 * @param objlist
	 *            :需要排序的对象数组
	 * */
	public void popSort(Object[] objlist) {
		int count = 0;
		while (objlist[count] != null)
			count++;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				{
					if (getSortId(objlist[i]) < getSortId(objlist[j])) {
						changeObj(objlist, i, j);
					}
				}
			}
		}
		System.out.println("全部数据排序成功！");
	}

	/**
	 * 对前count位进行冒泡排序
	 * 
	 * @param objlist
	 *            ：需要排序的对象数组
	 * @param count
	 *            ：需要排序的数组位数
	 * */
	public void popSort(Object[] objlist, int count) {
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				{
					if (getSortId(objlist[i]) < getSortId(objlist[j])) {
						changeObj(objlist, i, j);
					}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("前");
		sb.append(count);
		sb.append("位排序成功！");
		System.out.println(sb.toString());
	}

	/**
	 * 对部分数组进行排序
	 * 
	 * @param objlist
	 *            ：需要排序的对象数组
	 * @param from
	 *            ：从第几位开始
	 * @param to
	 *            ：到第几位结束
	 * */
	public void popSort(Object[] objlist, int from, int to) {
		int count = to - from;
		for (int i = from - 1; i < count + from; i++) {
			for (int j = i + 1; j < count + from; j++) {
				{
					if (getSortId(objlist[i]) < getSortId(objlist[j])) {
						changeObj(objlist, i, j);
					}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("第");
		sb.append(from);
		sb.append("个数到第");
		sb.append(to);
		sb.append("个数排序成功！");
		System.out.println(sb.toString());
	}
	

	/***************************** 快速排序 ****************************/

	public void quickSort(Object[] objlist) {
		int count = 0;
		while (objlist[count] != null)
			count++;
		boolean flag = true;
		while (flag) {
			int i;
			int j;
			for (i = 1; i < count; i++) {
				if (getSortId(objlist[0]) < getSortId(objlist[i])) {
					break;
				}
			}
			for (j = count - 1; j > 0; j--) {
				if (i == j) {
					Object temp;
					temp = objlist[0]; 
					objlist[0] = objlist[i];
					objlist[i] = temp;
					break;
				}
				if (getSortId(objlist[0]) > getSortId(objlist[j])) {
					break;
				} 
			}
		}
	}
}
