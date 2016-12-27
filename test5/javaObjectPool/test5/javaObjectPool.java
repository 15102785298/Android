package test5;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author tantian
 * @see 此方法为可以在多线程情况下使用的对象池，默认大小为10，最大大小为50
 *      <p>
 *      count：等待的最大次数 time：已经等待的次数
 */
public class javaObjectPool {

	private int numObjects = 10;// 对象池的大小
	private int maxObjects = 50;// 对象池的最大大小
	private Object inObj = null;

	@SuppressWarnings("rawtypes")
	private Vector objVector = null;// 存放对象池中对象的向量,使用向量的原因是在多线程编程时用向量比用List要好

	/**
	 * 对象池的空参数构造函数
	 * */
	public javaObjectPool() {
	}

	/**
	 * 创建一个对象池，其中synchronized为线程同步声明
	 * 
	 * @param Object
	 *            :对象池中需要保存的数据对象类型
	 * 
	 * */
	@SuppressWarnings("rawtypes")
	public synchronized void createPool(Object obj) {
		if (objVector != null) {// 如果线程池已经创建过
			return;
		}
		this.inObj = obj;
		// 创建保存对象的向量，初始时有0个元素
		objVector = new Vector();
		System.out.println("对象池创建成功！");

		// 根据对象池大小，循环创建指定数目的对象
		/*
		 * for (int x = 0; x < numObjects; x++) { if ((this.objVector.size() ==
		 * 0)// 如果当前向量的尺寸为空 && (this.objVector.size() < this.maxObjects)) {//
		 * 如果当前向量的尺寸小于最大尺寸 PoolObj obj = new PoolObj();// 创建对象池中的对象
		 * System.out.println("对象池创建成功！"); objVector.addElement(obj);// 向向量中增加节点
		 * } }
		 */
	}

	/**
	 * 从线程池中取一个Object
	 * 
	 * @return Object
	 * @exception InterruptedException
	 * 
	 * */
	public synchronized Object getObject() throws InterruptedException {
		if (objVector == null) {// 如果当前向量未创建，则返回null
			System.out.println("向量池还未创建！");
			return null;
		}

		int count = 5;// 设置获取次数
		int time = 0;// 初始化次数
		Object obj = getFreeObj();// 获得空闲对象

		while (obj == null && time < count) {// 如果当前无空闲对象
			try {
				wait(50);// 每隔50ms重新获取
				System.out.println("当前无空闲对象！");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj = getFreeObj();
			time++;
		}
		if (obj != null) {
			System.out.println("对象获取成功！");
		}

		if (time == count) {// 如果获取超时
			System.out.println("获取次数超过" + count + "次，获取失败！");
			return null;
		}

		return obj;
	}

	/**
	 * 从对象池中获得一个可用对象
	 * 
	 * @return Object
	 * */
	private synchronized Object getFreeObj() {
		Object obj = findFreeObj();// 查找可用对象

		if (obj == null) {// 如果查找不到空闲对象
			tryCreateObj();// 尝试扩充对象池
			obj = findFreeObj();// 再次查找空闲对象
			if (obj == null) {// 如果对象池已满且没有空闲对象则返回null
				System.out.println("对象池已满！");
				return null;
			}
		}
		return obj;
	}

	/**
	 * 尝试扩充对象池，扩充大小由maxObjects和numObjects共同决定
	 */
	@SuppressWarnings("unchecked")
	private synchronized void tryCreateObj() {
		if (this.objVector == null) {
			// 如果还未执行create则返回空
			System.out.println("向量池还未创建！");
			return;
		}
		// 尝试扩充
		if (this.objVector.size() < maxObjects) {
			// 如果当前对象池大小小于设定的最大值，则再创建numObjects个对象
			for (int i = 0; i < this.numObjects; i++) {// 循环创建
				PoolObj obj = new PoolObj();
				this.objVector.addElement(obj);
			}
			System.out.println("创建了" + numObjects + "个对象！");
			System.out.println("当前对象池的容量为：" + objVector.size());
		}
	}

	/**
	 * 查找对象池中的可用对象，如果没有则返回空
	 * 
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	private synchronized Object findFreeObj() {
		Object obj = null;// 对象池对象中存储的对象
		PoolObj pObj = null;// 对象池对象

		// 获得对象池向量中所有节点
		Enumeration enumeration = objVector.elements();

		// 遍历所有节点
		for (int i = 0; i < objVector.size(); i++) {
			pObj = (PoolObj) enumeration.nextElement();
			if (!pObj.getIsBusy()) {// 如果节点空闲则返回该节点的存储对象，置该对象为IsBusy true
				obj = pObj.getObject(inObj);// 从池对象中获得存储对象
				pObj.setIsBusy(true);
				return obj;// 返回存储对象
			}
		}
		return obj;
	}

	/**
	 * 返还对象给对象池，其中用了很多次getObject方法，会存在多创建很多对象的问题（原创建方法为懒汉模式），可以尝试优化
	 * 
	 * @param Object
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void returnObj(Object obj) {
		if (objVector == null)// 如果对象池未创建
		{
			System.out.println("向量池还未创建！");
			return;
		}

		Enumeration enumeration = objVector.elements();

		while (enumeration.hasMoreElements()) {// 遍历对象池中所有对象
			PoolObj item = (PoolObj) enumeration.nextElement();
			if (obj == item.getObject(inObj))// 如果传来的对象是对象池中的对象
			{
				item.setIsBusy(false);// 将该对象的状态改为空闲
				System.out.println("用户归还了一个对象！");
				return;// 直接返回
			}
		}
		System.out.println("收到了一个对象，不在对象池中");
	}

	/**
	 * 等待所有对象都被归还后关闭当前对象池
	 * 
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	public synchronized void closeObjectPool() throws InterruptedException {
		if (objVector == null) {
			System.out.println("向量池还未创建！");
			return;
		}
		Enumeration enumeration = objVector.elements();

		while (enumeration.hasMoreElements()) {// 遍历当前对象池
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
					System.out.println("等待对象归还....");
				}
				if (time == count)
					System.out.println("规定等待时间" + 500 * count
							+ "ms 已到，强制销毁对象池！");
				pObj.distroyObject();
				objVector.remove(pObj);
				pObj = null;
			}
		}
		objVector = null;
		System.out.println("对象池已销毁！");
	}

	/**
	 * 强制销毁对象池
	 * 
	 * @deprecated
	 * */
	@SuppressWarnings("rawtypes")
	public synchronized void forciblyCloseObjectPool() {
		if (objVector == null) {
			System.out.println("向量池还未创建！");
			return;
		}
		Enumeration enumeration = objVector.elements();
		while (enumeration.hasMoreElements()) {// 遍历当前对象池
			PoolObj pObj = (PoolObj) enumeration.nextElement();
			pObj.distroyObject();
			objVector.remove(pObj);
			pObj = null;
		}
		objVector = null;
		System.out.println("对象池已强制销毁！");
	}

	/**
	 * 获取当前对象池中对象的类名
	 * 
	 * @return String
	 */
	public synchronized String getNowModleName() {
		return inObj.getClass().getName();
	}

	/**
	 * 获取当前对象池中对象的实例
	 * 
	 * @return Object
	 */
	public synchronized Object getNowModleObj() {
		return inObj;
	}

	/**
	 * 获取当前一次创建的对象数目
	 * 
	 * @return int
	 */
	public int getNumObjects() {
		return numObjects;
	}

	/**
	 * 设置当前一次创建的对象数目
	 * 
	 * @param int
	 */
	public void setNumObjects(int numObjects) {
		this.numObjects = numObjects;
	}

	/**
	 * 获取当前对象池最大的对象数目
	 * 
	 * @return int
	 */
	public int getMaxObjects() {
		return maxObjects;
	}

	/**
	 * 设置当前对象池的最大对象数目
	 * 
	 * @param int
	 */
	public void setMaxObjects(int maxObjects) {
		this.maxObjects = maxObjects;
	}

}
