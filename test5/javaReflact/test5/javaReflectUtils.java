package test5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ͨ��java�����ȡ����
 * 
 * @author tantian
 */
public class javaReflectUtils {
	@SuppressWarnings("rawtypes")
	private Class thisClass;
	private Object thisObj;

	/**
	 * ͨ��ʵ����ʼ���ù���
	 * 
	 * @param Object
	 */
	public void initByObj(Object thisObj) {
		this.thisObj = thisObj;
		thisClass = this.thisObj.getClass();
	}

	/**
	 * ͨ��������ʼ���ù���
	 * 
	 * @param String
	 */
	public void initByName(String name) throws ClassNotFoundException {
		try {
			thisClass = Class.forName(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��õ�ǰ���ߵ�Class
	 * 
	 * @return Class
	 */
	@SuppressWarnings("rawtypes")
	public Class getThisClass() {
		return thisClass;
	}

	/**
	 * ��ȡ��ǰ���ߵ�Obj
	 * 
	 * @return Object
	 */
	public Object getThisObj() {
		return thisObj;
	}

	/**
	 * ��ȡ��ǰObj�������������
	 * 
	 * @return String
	 */
	public String getClassName() {
		return thisClass.getName();
	}

	/**
	 * ��ȡ��������Ӧ��������й��з�����getMethods
	 * 
	 * @return Method[]
	 */
	public Method[] getMethodInPublic() {
		return thisClass.getDeclaredMethods();
	}

	/**
	 * ��ȡ��������Ӧ��������з�����getDeclaredMethods
	 */
	public Method[] getAllMethod() {
		return thisClass.getMethods();
	}

	/**
	 * ��ȡ��������Ӧ����ĸ�������й��з���
	 * 
	 * @return Method[]
	 */
	public Method[] getSuperMethodInPublic() {
		if (thisClass.getSuperclass() != null) {
			return thisClass.getSuperclass().getDeclaredMethods();
		}
		return null;
	}

	/**
	 * ��ȡ��������Ӧ����ĸ�������з���
	 * 
	 * @return Method[]
	 */
	public Method[] getAllSuperMethod() {
		if (thisClass.getSuperclass() != null) {
			return thisClass.getSuperclass().getMethods();
		}
		return null;
	}

	/**
	 * ��ȡ��������Ӧ����ķ������������� Map����Ϊ��������Ӧ�����е�������ֵΪ��Ӧ�ı�������
	 * 
	 * @exception NoSuchMethodException
	 */
	public void getParameterTypes() throws NoSuchMethodException,
			SecurityException {
		Method[] methods = thisClass.getMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
			Class<?> pm[] = m.getParameterTypes();
			for (Class<?> p : pm) {
				System.out.println(p.getName());
			}
			System.out.println("*******************");
		}
	}

	/**
	 * ��ȡ��������Ӧ����ķ������������� Map����Ϊ��������Ӧ�����е�������ֵΪ��Ӧ�ı�������(δʵ��)
	 * 
	 * @exception NoSuchMethodException
	 *                ,SecurityException
	 */
	public void getParameterTypes1() throws NoSuchMethodException,
			SecurityException {
		Method[] methods = thisClass.getMethods();
		for (Method m : methods) {
			System.out.println(m.getName());
			Class<?> pm[] = m.getParameterTypes();
			for (Class<?> p : pm) {
				System.out.println(p.getName());
			}
			System.out.println("*******************");
		}
	}

	/**
	 * ����һ����������ʵ��
	 * 
	 * @return Object
	 * */
	public Object getMoreObject() {
		Object newObj = null;
		try {
			newObj = thisClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newObj;
	}

	/**
	 * �޸ķ�����Ӧ��obj
	 * */
	private void changeObj(Object thisObj) {
		this.thisObj = thisObj;
		thisClass = thisObj.getClass();
	}

	/**
	 * ͨ�����������ø÷��������ط����ķ���ֵ
	 * 
	 * */
	public Object invokeMethodByName(Object thisobj, String methodname) {
		changeObj(thisobj);
		Object obj = null;
		Method methods[] = thisClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodname)) {
				try {
					obj = method.invoke(thisObj);
					break;
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * ���ظö����Ӧ��������в���
	 * 
	 * @return Field[]
	 * 
	 * */
	public Field[] getAllField() {
		return thisClass.getDeclaredFields();
	}

	/**
	 * ���ظö����Ӧ����Ĺ��в���
	 * 
	 * @return Field[]
	 */
	public Field[] getPublicField() {
		return thisClass.getFields();
	}
	// /**
	// * Ϊ���ⱻ���޸ģ��˷������ɵ���
	// */
	// public void setThisClass(Class thisClass) {
	// this.thisClass = thisClass;
	// }

	// /**
	// * Ϊ���ⱻ���޸ģ��˷������ɵ���
	// */
	// public void setThisObj(Object thisObj) {
	// this.thisObj = thisObj;
	// }

}
