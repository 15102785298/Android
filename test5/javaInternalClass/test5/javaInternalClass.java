package test5;

/**
 * java�ڲ���
 * 
 * �ڲ�����Ƕ�����һ�����ڲ����࣬��ôΪʲôҪʹ���ڲ����أ���Ҫԭ�������¼��㣺��һ���ڲ����ж���ķ����ܷ��ʵ��������ⲿ���˽�����Լ��������ڶ���
 * �ⲿ���޷�ʵ�ֶ�ͬһ���е����������أ����ڲ������������һ�㣻�����������ڲ���������ֻ��ʹ�ø����ʵ������ʱ������Ч�������ǵĴ�����
 */
public class javaInternalClass {

	private int OuterClassPI = 50;

	/**
	 * ���ⲿ���ж���һ���ڲ���ʵ�����������ڲ���ķ�����ע�⣬�ڲ��಻�����ŷ����Ľ���������
	 */
	public void OuterShow() {
		InternalClassToPrivate incc = this.new InternalClassToPrivate();// ��this.new��������
		// ��ʾ�Ľ��ڲ�����е���Χ������ָ����������Χ����󣬼���outerObject��һ��Outer��ʵ��
		// Outer.Inner inner = outerObject.new Inner();
		incc.showOuterPi();
	}

	/**
	 * Ҳ���Ե���jic��main�����������ջ���
	 * */
	public static void main(String[] args) {
		javaInternalClass jic = new javaInternalClass();

		String s[] = { "dd", "ddd" };
		jic.OuterShow();
	}

	/**
	 * �ڲ�������ⲿ���˽�з���
	 * */
	private class InternalClassToPrivate {
		public void showOuterPi() {
			System.out.println(javaInternalClass.this.OuterClassPI);// ʵ�����ڲ�����ʽ������һ���ⲿ������ã����ܻᵼ���ڴ�й©
		}
	}
}
