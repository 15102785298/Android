package test5;

/**
 * �����ڲ���
 * 
 * �򵥵�˵�������ڲ������û�����ֵ��ڲ��ࡣʲô�������Ҫʹ�������ڲ��ࣿ������������һЩ������ʹ�������ڲ����ǱȽϺ��ʵģ� ��ֻ�õ����һ��ʵ����
 * �����ڶ���������õ��� ����ǳ�С��SUN�Ƽ�����4�д������£� ���������������ᵼ����Ĵ�������ױ���⡣ ��ʹ�������ڲ���ʱ��Ҫ��ס���¼���ԭ��
 * �������ڲ��಻���й��췽���� �������ڲ��಻�ܶ����κξ�̬��Ա���������ࡣ
 * �������ڲ��಻����public,protected,private,static�� ��ֻ�ܴ��������ڲ����һ��ʵ����
 * ��һ�������ڲ���һ������new�ĺ��棬��������ʵ��һ���ӿڻ�ʵ��һ���ࡣ ���������ڲ���Ϊ�ֲ��ڲ��࣬���Ծֲ��ڲ�����������ƶ�������Ч��
 * 
 * 
 * 
 * ��������ӿ������е���֣� //�ڷ����з���һ�������ڲ��� public class Parcel6 { public Contents cont() {
 * return new Contents() { private int i = 11.
 * 
 * public int value() { return i. } }. // ��������Ҫһ���ֺ� }
 * 
 * public static void main(String[] args) { Parcel6 p = new Parcel6(). Contents
 * c = p.cont(). } }
 * cont()�������������������ϲ���һ�𣺷���ֵ�����ɣ����ʾ�������ֵ����Ķ��壡��һ��˵��������������ģ���û������
 * ��������ǣ�������������Ҫ����һ��Contents���� return new Contents()
 * ���ǣ��ڵ����������ķֺ�֮ǰ����ȴ˵������һ�ȣ��������������һ����Ķ��塱: return new Contents() { private int
 * i = 11. public int value() { return i. } }.
 * ������ֵ��﷨ָ���ǣ�������һ���̳���Contents��������Ķ��󡣡�ͨ��new
 * ���ʽ���ص����ñ��Զ�����ת��Ϊ��Contents�����á������ڲ�����﷨���������ӵļ�����ʽ�� class MyContents implements
 * Contents { private int i = 11. public int value() { return i. } } return new
 * MyContents().
 * ����������ڲ����У�ʹ����ȱʡ�Ĺ�����������Contents������Ĵ���չʾ���ǣ������Ļ�����Ҫһ���в����Ĺ�������Ӧ����ô�죺 public
 * class Parcel7 { public Wrapping wrap(int x) { // Base constructor call:
 * return new Wrapping(x) { // Pass constructor argument. public int value() {
 * return super.value() * 47. } }. // Semicolon required } public static void
 * main(String[] args) { Parcel7 p = new Parcel7(). Wrapping w = p.wrap(10). } }
 */
public class AnonymousInClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
