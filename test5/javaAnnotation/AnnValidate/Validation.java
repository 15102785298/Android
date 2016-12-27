package AnnValidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target��
 * 
 *          ������@Target˵����Annotation�����εĶ���Χ��Annotation�ɱ�����
 *          packages��types���ࡢ�ӿڡ�ö��
 *          ��Annotation���ͣ������ͳ�Ա�����������췽������Ա������ö��ֵ�������������ͱ��ر���
 *          ����ѭ��������catch����������Annotation���͵�������ʹ����target�ɸ������������ε�Ŀ�ꡣ
 *          <p>
 * 
 *          �������ã���������ע���ʹ�÷�Χ��������������ע���������ʲô�ط���</br>
 * 
 *          ����ȡֵ(ElementType)�У�</br>
 * 
 *          1.CONSTRUCTOR:��������������</br>2.FIELD:����������</br>
 *          3.LOCAL_VARIABLE:���������ֲ�����</br>4.METHOD:������������</br> 5.PACKAGE:����������
 *          </br>6.PARAMETER:������������</br> 7.TYPE:���������ࡢ�ӿ�(����ע������) ��enum����</br>
 *          
 *          {<a> http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html}
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {

}
