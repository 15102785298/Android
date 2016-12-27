package test5;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tantian
 * @see 如果使用该模型需要在模型类中定义getId方法，返回值为构建堆的参数
 */
public class HeadHeapObj implements Serializable {

	private int HeapObjId;// 每一个大根堆对象的id
	private Object ContentObj;// 包含在大根堆里的对象
	private javaReflectUtils jru = null;// 反射工具

	/**
	 * 获取大根堆对象的id
	 * 
	 * @param int
	 * */
	public int getHeapObjId() {
		return HeapObjId;
	}

	/**
	 * 设置大根堆的id,该id将用于大根堆排序
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
	 * 获取大根堆里的数据对象
	 * 
	 * @return Object
	 */
	public Object getContentObj() {
		return ContentObj;
	}

	/**
	 * 设置大根堆里的对象并设置id
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
	 * 初始化反射工具
	 * 
	 * @param Object
	 * */
	private void initJru(Object thisObj) {
		jru = new javaReflectUtils();
		jru.initByObj(thisObj);
	}
}
