package test5;

/**
 * 对象池中的容器
 * 
 * @author tantian
 * */
public class PoolObj {

	private Object object = null;// 对象池中放的对象
	private boolean isBusy = false;// 对象池的对象是否正在被使用
	javaReflectUtils jru = null;// 反射工具

	/**
	 * 获取当前池对象的使用状态
	 * 
	 * @return boolean
	 * */
	public boolean getIsBusy() {
		return isBusy;
	}

	/**
	 * 设置当前池对象的使用状态
	 * 
	 * @param boolean
	 * */
	public void setIsBusy(boolean busy) {
		this.isBusy = busy;
	}

	/**
	 * 得到池对象中的对象
	 * 
	 * @param Object
	 *            ：需要创建的数据对象的一个实例
	 * @return Object
	 * */
	public Object getObject(Object obj) {
		if (object == null) {
			setModle(obj);
			object = jru.getMoreObject();
			return object;
		} else {
			return object;
		}
	}

	/*
	 * public Object getObject() { return object; }
	 */

	/**
	 * 将对象放入池对象中
	 * 
	 * @param Object
	 * */
	@SuppressWarnings("unused")
	private void setObject(Object object) {
		this.object = object;
	}

	/**
	 * 销毁当前对象
	 */
	public void distroyObject() {
		this.object = null;
	}

	/**
	 * 通过对象的实例初始化反射工具
	 * 
	 * @param Object
	 *            ：需要使用的对象的实例
	 */
	private void setModle(Object obj) {
		this.jru = new javaReflectUtils();
		jru.initByObj(obj);
	}

}
