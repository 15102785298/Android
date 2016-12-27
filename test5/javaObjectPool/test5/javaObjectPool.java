package test5;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author tantian
 * @see �˷���Ϊ�����ڶ��߳������ʹ�õĶ���أ�Ĭ�ϴ�СΪ10������СΪ50
 *      <p>
 *      count���ȴ��������� time���Ѿ��ȴ��Ĵ���
 */
public class javaObjectPool {

	private int numObjects = 10;// ����صĴ�С
	private int maxObjects = 50;// ����ص�����С
	private Object inObj = null;

	@SuppressWarnings("rawtypes")
	private Vector objVector = null;// ��Ŷ�����ж��������,ʹ��������ԭ�����ڶ��̱߳��ʱ����������ListҪ��

	/**
	 * ����صĿղ������캯��
	 * */
	public javaObjectPool() {
	}

	/**
	 * ����һ������أ�����synchronizedΪ�߳�ͬ������
	 * 
	 * @param Object
	 *            :���������Ҫ��������ݶ�������
	 * 
	 * */
	@SuppressWarnings("rawtypes")
	public synchronized void createPool(Object obj) {
		if (objVector != null) {// ����̳߳��Ѿ�������
			return;
		}
		this.inObj = obj;
		// ��������������������ʼʱ��0��Ԫ��
		objVector = new Vector();
		System.out.println("����ش����ɹ���");

		// ���ݶ���ش�С��ѭ������ָ����Ŀ�Ķ���
		/*
		 * for (int x = 0; x < numObjects; x++) { if ((this.objVector.size() ==
		 * 0)// �����ǰ�����ĳߴ�Ϊ�� && (this.objVector.size() < this.maxObjects)) {//
		 * �����ǰ�����ĳߴ�С�����ߴ� PoolObj obj = new PoolObj();// ����������еĶ���
		 * System.out.println("����ش����ɹ���"); objVector.addElement(obj);// �����������ӽڵ�
		 * } }
		 */
	}

	/**
	 * ���̳߳���ȡһ��Object
	 * 
	 * @return Object
	 * @exception InterruptedException
	 * 
	 * */
	public synchronized Object getObject() throws InterruptedException {
		if (objVector == null) {// �����ǰ����δ�������򷵻�null
			System.out.println("�����ػ�δ������");
			return null;
		}

		int count = 5;// ���û�ȡ����
		int time = 0;// ��ʼ������
		Object obj = getFreeObj();// ��ÿ��ж���

		while (obj == null && time < count) {// �����ǰ�޿��ж���
			try {
				wait(50);// ÿ��50ms���»�ȡ
				System.out.println("��ǰ�޿��ж���");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj = getFreeObj();
			time++;
		}
		if (obj != null) {
			System.out.println("�����ȡ�ɹ���");
		}

		if (time == count) {// �����ȡ��ʱ
			System.out.println("��ȡ��������" + count + "�Σ���ȡʧ�ܣ�");
			return null;
		}

		return obj;
	}

	/**
	 * �Ӷ�����л��һ�����ö���
	 * 
	 * @return Object
	 * */
	private synchronized Object getFreeObj() {
		Object obj = findFreeObj();// ���ҿ��ö���

		if (obj == null) {// ������Ҳ������ж���
			tryCreateObj();// ������������
			obj = findFreeObj();// �ٴβ��ҿ��ж���
			if (obj == null) {// ��������������û�п��ж����򷵻�null
				System.out.println("�����������");
				return null;
			}
		}
		return obj;
	}

	/**
	 * �����������أ������С��maxObjects��numObjects��ͬ����
	 */
	@SuppressWarnings("unchecked")
	private synchronized void tryCreateObj() {
		if (this.objVector == null) {
			// �����δִ��create�򷵻ؿ�
			System.out.println("�����ػ�δ������");
			return;
		}
		// ��������
		if (this.objVector.size() < maxObjects) {
			// �����ǰ����ش�СС���趨�����ֵ�����ٴ���numObjects������
			for (int i = 0; i < this.numObjects; i++) {// ѭ������
				PoolObj obj = new PoolObj();
				this.objVector.addElement(obj);
			}
			System.out.println("������" + numObjects + "������");
			System.out.println("��ǰ����ص�����Ϊ��" + objVector.size());
		}
	}

	/**
	 * ���Ҷ�����еĿ��ö������û���򷵻ؿ�
	 * 
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	private synchronized Object findFreeObj() {
		Object obj = null;// ����ض����д洢�Ķ���
		PoolObj pObj = null;// ����ض���

		// ��ö�������������нڵ�
		Enumeration enumeration = objVector.elements();

		// �������нڵ�
		for (int i = 0; i < objVector.size(); i++) {
			pObj = (PoolObj) enumeration.nextElement();
			if (!pObj.getIsBusy()) {// ����ڵ�����򷵻ظýڵ�Ĵ洢�����øö���ΪIsBusy true
				obj = pObj.getObject(inObj);// �ӳض����л�ô洢����
				pObj.setIsBusy(true);
				return obj;// ���ش洢����
			}
		}
		return obj;
	}

	/**
	 * �������������أ��������˺ܶ��getObject����������ڶഴ���ܶ��������⣨ԭ��������Ϊ����ģʽ�������Գ����Ż�
	 * 
	 * @param Object
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void returnObj(Object obj) {
		if (objVector == null)// ��������δ����
		{
			System.out.println("�����ػ�δ������");
			return;
		}

		Enumeration enumeration = objVector.elements();

		while (enumeration.hasMoreElements()) {// ��������������ж���
			PoolObj item = (PoolObj) enumeration.nextElement();
			if (obj == item.getObject(inObj))// ��������Ķ����Ƕ�����еĶ���
			{
				item.setIsBusy(false);// ���ö����״̬��Ϊ����
				System.out.println("�û��黹��һ������");
				return;// ֱ�ӷ���
			}
		}
		System.out.println("�յ���һ�����󣬲��ڶ������");
	}

	/**
	 * �ȴ����ж��󶼱��黹��رյ�ǰ�����
	 * 
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void closeObjectPool() throws InterruptedException {
		if (objVector == null) {
			System.out.println("�����ػ�δ������");
			return;
		}
		Enumeration enumeration = objVector.elements();

		while (enumeration.hasMoreElements()) {// ������ǰ�����
			PoolObj pObj = (PoolObj) enumeration.nextElement();
			int count = 10;
			int time = 0;
			if (!pObj.getIsBusy()) {
				pObj.distroyObject();
				objVector.remove(pObj);
				pObj = null;
			} else {
				while (!pObj.getIsBusy() && time < count) {
					wait(500);
					time++;
					System.out.println("�ȴ�����黹....");
				}
				if (time == count)
					System.out.println("�涨�ȴ�ʱ��" + 500 * count
							+ "ms �ѵ���ǿ�����ٶ���أ�");
				pObj.distroyObject();
				objVector.remove(pObj);
				pObj = null;
			}
		}
		objVector = null;
		System.out.println("����������٣�");
	}

	/**
	 * ǿ�����ٶ����
	 * 
	 * @deprecated
	 * */
	@SuppressWarnings("rawtypes")
	public synchronized void forciblyCloseObjectPool() {
		if (objVector == null) {
			System.out.println("�����ػ�δ������");
			return;
		}
		Enumeration enumeration = objVector.elements();
		while (enumeration.hasMoreElements()) {// ������ǰ�����
			PoolObj pObj = (PoolObj) enumeration.nextElement();
			pObj.distroyObject();
			objVector.remove(pObj);
			pObj = null;
		}
		objVector = null;
		System.out.println("�������ǿ�����٣�");
	}

	/**
	 * ��ȡ��ǰ������ж��������
	 * 
	 * @return String
	 */
	public synchronized String getNowModleName() {
		return inObj.getClass().getName();
	}

	/**
	 * ��ȡ��ǰ������ж����ʵ��
	 * 
	 * @return Object
	 */
	public synchronized Object getNowModleObj() {
		return inObj;
	}

	/**
	 * ��ȡ��ǰһ�δ����Ķ�����Ŀ
	 * 
	 * @return int
	 */
	public int getNumObjects() {
		return numObjects;
	}

	/**
	 * ���õ�ǰһ�δ����Ķ�����Ŀ
	 * 
	 * @param int
	 */
	public void setNumObjects(int numObjects) {
		this.numObjects = numObjects;
	}

	/**
	 * ��ȡ��ǰ��������Ķ�����Ŀ
	 * 
	 * @return int
	 */
	public int getMaxObjects() {
		return maxObjects;
	}

	/**
	 * ���õ�ǰ����ص���������Ŀ
	 * 
	 * @param int
	 */
	public void setMaxObjects(int maxObjects) {
		this.maxObjects = maxObjects;
	}

}
