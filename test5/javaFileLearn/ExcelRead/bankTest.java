package ExcelRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bankTest {

	/**
	 *  @author TT
	 *  @see ���汾��֧��Excel 97-2003��ʽ���õ���poi.jar����
	 **/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bankExcelUtils u=new bankExcelUtils();
	    @SuppressWarnings("unused")
		List<Map<String,String>> m=new ArrayList<Map<String,String>>();
	    System.out.println("���ݶ�ȡ��......");
		u.InitUtils("C:/Users/TT/Desktop/test.xls",4);//�����ļ�·������ʼ��������
		try {
			u.printAllBank();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ӡExcel������������Ϣ
	}
}
