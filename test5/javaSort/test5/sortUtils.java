package test5;

import java.io.Serializable;
import java.util.HashMap;

public class sortUtils implements Serializable{

	private javaReflectUtils jru;
	private String methodname;

	/**************************** �������� ******************************/

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
	 * ��������Ĺؼ����������򽫻��ô����ķ�����ȡ��������ݣ�����ӦΪdouble
	 * 
	 * @param methodname
	 *            :��ȡ�ؼ������ķ�����
	 */
	public void setSortId(String methodname) {
		this.methodname = methodname;
		jru = new javaReflectUtils();
	}

	/***************************** ð������ ****************************/

	/**
	 * ��ȫ�����ݽ���ð������
	 * 
	 * @param objlist
	 *            :��Ҫ����Ķ�������
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
		System.out.println("ȫ����������ɹ���");
	}

	/**
	 * ��ǰcountλ����ð������
	 * 
	 * @param objlist
	 *            ����Ҫ����Ķ�������
	 * @param count
	 *            ����Ҫ���������λ��
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
		sb.append("ǰ");
		sb.append(count);
		sb.append("λ����ɹ���");
		System.out.println(sb.toString());
	}

	/**
	 * �Բ��������������
	 * 
	 * @param objlist
	 *            ����Ҫ����Ķ�������
	 * @param from
	 *            ���ӵڼ�λ��ʼ
	 * @param to
	 *            �����ڼ�λ����
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
		sb.append("��");
		sb.append(from);
		sb.append("��������");
		sb.append(to);
		sb.append("��������ɹ���");
		System.out.println(sb.toString());
	}
	

	/***************************** �������� ****************************/

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
