package ExcelRead;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bankTest {

	/**
	 *  @author TT
	 *  @see 本版本仅支持Excel 97-2003格式（用到了poi.jar包）
	 **/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bankExcelUtils u=new bankExcelUtils();
	    @SuppressWarnings("unused")
		List<Map<String,String>> m=new ArrayList<Map<String,String>>();
	    System.out.println("数据读取中......");
		u.InitUtils("C:/Users/TT/Desktop/test.xls",4);//传入文件路径，初始化工具类
		try {
			u.printAllBank();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//打印Excel中所有银行信息
	}
}
