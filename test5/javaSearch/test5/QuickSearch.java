package test5;

import org.junit.Test;

/**
 * ���ֲ���
 * 
 * @author cyxl
 * 
 */
public class QuickSearch {
	private int rCount = 0;
	private int lCount = 0;

	/**
	 * ��ȡ�ݹ�Ĵ���
	 * 
	 * @return
	 */
	public int getrCount() {
		return rCount;
	}

	/**
	 * ��ȡѭ���Ĵ���
	 * 
	 * @return
	 */
	public int getlCount() {
		return lCount;
	}

	/**
	 * ִ�еݹ���ֲ��ң����ص�һ�γ��ָ�ֵ��λ��
	 * 
	 * @param sortedData
	 *            �����������
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��
	 * @param findValue
	 *            ��Ҫ�ҵ�ֵ
	 * @return ֵ�������е�λ�ã���0��ʼ���Ҳ�������-1
	 */
	public int searchRecursive(int[] sortedData, int start, int end,
			int findValue) {
		rCount++;
		if (start <= end) {
			// �м�λ��
			int middle = (start + end) >> 1; // �൱��(start+end)/2
			// ��ֵ
			int middleValue = sortedData[middle];

			if (findValue == middleValue) {
				// ������ֱֵ�ӷ���
				return middle;
			} else if (findValue < middleValue) {
				// С����ֵʱ����ֵǰ����
				return searchRecursive(sortedData, start, middle - 1, findValue);
			} else {
				// ������ֵ����ֵ������
				return searchRecursive(sortedData, middle + 1, end, findValue);
			}
		} else {
			// �Ҳ���
			return -1;
		}
	}

	/**
	 * ѭ�����ֲ��ң����ص�һ�γ��ָ�ֵ��λ��
	 * 
	 * @param sortedData
	 *            �����������
	 * @param findValue
	 *            ��Ҫ�ҵ�ֵ
	 * @return ֵ�������е�λ�ã���0��ʼ���Ҳ�������-1
	 */
	public int searchLoop(int[] sortedData, int findValue) {
		int start = 0;
		int end = sortedData.length - 1;

		while (start <= end) {
			lCount++;
			// �м�λ��
			int middle = (start + end) >> 1; // �൱��(start+end)/2
			// ��ֵ
			int middleValue = sortedData[middle];

			if (findValue == middleValue) {
				// ������ֱֵ�ӷ���
				return middle;
			} else if (findValue < middleValue) {
				// С����ֵʱ����ֵǰ����
				end = middle - 1;
			} else {
				// ������ֵ����ֵ������
				start = middle + 1;
			}
		}
		// �Ҳ���
		return -1;
	}
	
	
    public class BinarySearchTest {  
        @Test  
        public void testSearch()  
        {  
        	QuickSearch bs=new QuickSearch();  
              
            int[] sortedData={1,2,3,4,5,6,6,7,8,8,9,10};  
            int findValue=9;  
            int length=sortedData.length;  
              
            int pos=bs.searchRecursive(sortedData, 0, length-1, findValue);  
            System.out.println("Recursice:"+findValue+" found in pos "+pos+";count:"+bs.getrCount());  
            int pos2=bs.searchLoop(sortedData, findValue);  
              
            System.out.println("Loop:"+findValue+" found in pos "+pos+";count:"+bs.getlCount());  
        }  
    }  
}
