package test5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过java反射获取数据
 * 
 * @author tantian
 */
public class javaReflectUtils {
	@SuppressWarnings("rawtypes")
	private Class thisClass;
	private Object thisObj;

	/**
	 * 通过实例初始化该工具
	 * 
	 * @param Object
	 */
	public void initByObj(Object thisObj) {
		this.thisObj = thisObj;
		thisClass = this.thisObj.getClass();
	}

	/**
	 * 通过类名初始化该工具
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
	 * 获得当前工具的Class
	 * 
	 * @return Class
	 */
	@SuppressWarnings("rawtypes")
	public Class getThisClass() {
		return thisClass;
	}

	/**
	 * 获取当前工具的Obj
	 * 
	 * @return Object
	 */
	public Object getThisObj() {
		return thisObj;
	}

	/**
	 * 获取当前Obj所属的类的类名
	 * 
	 * @return String
	 */
	public String getClassName() {
		return thisClass.getName();
	}

	/**
	 * 获取该类对象对应的类的所有公有方法用getMethods
	 * 
	 * @return Method[]
	 */
	public Method[] getMethodInPublic() {
		return thisClass.getDeclaredMethods();
	}

	/**
	 * 获取该类对象对应的类的所有方法用getDeclaredMethods
	 */
	public Method[] getAllMethod() {
		return thisClass.getMethods();
	}

	/**
	 * 获取该类对象对应的类的父类的所有公有方法
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
	 * 获取该类对象对应的类的父类的所有方法
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
	 * 获取该类对象对应的类的方法的所有属性 Map中名为变量在相应方法中的命名，值为对应的变量类型
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
	 * 获取该类对象对应的类的方法的所有属性 Map中名为变量在相应方法中的命名，值为对应的变量类型(未实现)
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
	 * 返回一个该类对象的实例
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
	 * 修改方法对应的obj
	 * */
	private void changeObj(Object thisObj) {
		this.thisObj = thisObj;
		thisClass = thisObj.getClass();
	}

	/**
	 * 通过方法名调用该方法并返回方法的返回值
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
	 * 返回该对象对应的类的所有参数
	 * 
	 * @return Field[]
	 * 
	 * */
	public Field[] getAllField() {
		return thisClass.getDeclaredFields();
	}

	/**
	 * 返回该对象对应的类的公有参数
	 * 
	 * @return Field[]
	 */
	public Field[] getPublicField() {
		return thisClass.getFields();
	}
	// /**
	// * 为避免被乱修改，此方法不可调用
	// */
	// public void setThisClass(Class thisClass) {
	// this.thisClass = thisClass;
	// }

	// /**
	// * 为避免被乱修改，此方法不可调用
	// */
	// public void setThisObj(Object thisObj) {
	// this.thisObj = thisObj;
	// }

}
