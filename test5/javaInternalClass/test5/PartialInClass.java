package test5;

/**
 * java�ֲ���
 * 
 * �ֲ����������ͱ������ڶ������ķ����ķ������У������������public��private�������η������Ρ�
 * �볣���ڲ���Ƚϣ��ֲ������һ�����ƣ����Է��ʾֲ�����
 * ����������һ�����ƣ����������ʵľֲ��������뱻����Ϊfinal���򵥵�˵�����ǳ���һ���ԵĿ��ǡ���Ϊ�ֲ������������������ŷ��������н���Ҳ��֮������
 * �����ֲ������������ȴ�������ŷ����Ľ������������ڷ���������󣬾ֲ���Ϊ���ܹ��������ʾֲ���������Ҫ�Ծֲ��������б��ݡ�
 * ʵ���ϣ��ڴ����ֲ���Ķ���ʱ������������ʽ�޸ľ߱���Ĺ�����
 * �������ֲ���Ҫ���ʵġ��ⲿ��������Ϊ�������ݸ����������߱�����������ڲ�����һ���������洢���Լ���ʵ������
 * �������������������final�ģ������ǿ����ھ߱�����������޸�
 * �������ɻ��ƻ����ݵ�һ���ԣ��ֲ����������ھֲ����ڲ��Ŀ����汾��һ�������������þֲ�����ʵı����������final���η���
 */
public class PartialInClass {
	private int testInP = 5;

	// ����һ������
	private void testPatrInClass() {
		int aa = 555;
		class InClass {// �ڷ����ж���һ���ֲ���
			public int a = 55;

			public void show() {
				System.out.println(a);// ��ʾ����Ĳ���
				System.out.println(aa);// ��ʾ�����ж���Ĳ���
				System.out.println(testInP);// ��ʾ�ⲿ���ж����˽�в���
				// aa=40;
				// �˾䲻ͨ��ԭ�����ھֲ����У�ʵ�������aa���Բ�������ʽ�����ֲ���ģ�
				// ����Ϊ�˱�֤������һ���ԣ�aa���붨��Ϊfinal����ʱ����ʽ�����
				
				System.out.println(this.hashCode());

			}
		}
		InClass ic = new InClass();
		ic.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartialInClass pic = new PartialInClass();
		pic.testPatrInClass();
	}

}
