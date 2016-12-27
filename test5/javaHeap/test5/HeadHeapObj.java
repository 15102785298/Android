package test5;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tantian
 * @see ���ʹ�ø�ģ����Ҫ��ģ�����ж���getId����������ֵΪ�����ѵĲ���
 */
public class HeadHeapObj implements Serializable {

	private int HeapObjId;// ÿһ������Ѷ����id
	private Object ContentObj;// �����ڴ������Ķ���
	private javaReflectUtils jru = null;// ���乤��

	/**
	 * ��ȡ����Ѷ����id
	 * 
	 * @param int
	 * */
	public int getHeapObjId() {
		return HeapObjId;
	}

	/**
	 * ���ô���ѵ�id,��id�����ڴ��������
	 * 
	 * @param Object
	 * */
	private void setHeapObjId(Object obj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		initJru(obj);
		Method allMethod[] = jru.getAllMethod();
		HeapObjId = 0;
		for (Method method : allMethod) {
			if (method.getName() == "getId") {
				try {
					HeapObjId = (int) method.invoke(obj);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��ȡ�����������ݶ���
	 * 
	 * @return Object
	 */
	public Object getContentObj() {
		return ContentObj;
	}

	/**
	 * ���ô������Ķ�������id
	 * 
	 * @param Object
	 */
	public void setContentObj(Object contentObj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		try {
			setHeapObjId(contentObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ContentObj = contentObj;
	}

	public void show() {
		System.out.println("sss");
	}

	/**
	 * ��ʼ�����乤��
	 * 
	 * @param Object
	 * */
	private void initJru(Object thisObj) {
		jru = new javaReflectUtils();
		jru.initByObj(thisObj);
	}
}
