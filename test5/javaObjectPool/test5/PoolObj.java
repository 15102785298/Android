package test5;

/**
 * ������е�����
 * 
 * @author tantian
 * */
public class PoolObj {

	private Object object = null;// ������зŵĶ���
	private boolean isBusy = false;// ����صĶ����Ƿ����ڱ�ʹ��
	javaReflectUtils jru = null;// ���乤��

	/**
	 * ��ȡ��ǰ�ض����ʹ��״̬
	 * 
	 * @return boolean
	 * */
	public boolean getIsBusy() {
		return isBusy;
	}

	/**
	 * ���õ�ǰ�ض����ʹ��״̬
	 * 
	 * @param boolean
	 * */
	public void setIsBusy(boolean busy) {
		this.isBusy = busy;
	}

	/**
	 * �õ��ض����еĶ���
	 * 
	 * @param Object
	 *            ����Ҫ���������ݶ����һ��ʵ��
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
	 * ���������ض�����
	 * 
	 * @param Object
	 * */
	@SuppressWarnings("unused")
	private void setObject(Object object) {
		this.object = object;
	}

	/**
	 * ���ٵ�ǰ����
	 */
	public void distroyObject() {
		this.object = null;
	}

	/**
	 * ͨ�������ʵ����ʼ�����乤��
	 * 
	 * @param Object
	 *            ����Ҫʹ�õĶ����ʵ��
	 */
	private void setModle(Object obj) {
		this.jru = new javaReflectUtils();
		jru.initByObj(obj);
	}

}
