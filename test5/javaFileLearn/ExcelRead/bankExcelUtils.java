package ExcelRead;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bankExcelUtils {

	private readExcelUtils file = new readExcelUtils();
	private List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
	private String URL;
	private int rowNum;

	// @SuppressWarnings("unused")
	// private boolean isInit = false;

	/**
	 * 将Excel中的数据与bankModle进行绑定，需要先InitUtils
	 * 
	 * @param
	 * @return bankList
	 */
	public List<Map<String, String>> getBankList() {
		return dataMap;
	}

	/**
	 * 取出Excel中读取键值对
	 * 
	 * @url     :文件路径 
	 * @listNum        :读取列数
	 * 
	 */
	@SuppressWarnings("static-access")
	public void InitUtils(String url, int rowNum) {
		this.URL=url;
		this.rowNum=rowNum;
		String list[][] = null;
		// isInit = true;
		try {
			list = file.getData(url, 0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rowLength = list.length;
		for (int i = 1; i < rowLength; i++) {
			Map<String, String> mp = new HashMap<String, String>();
			for (int j = 0; j < rowNum; j++) {
				mp.put(list[0][j], list[i][j]);
			}
			dataMap.add(mp);
		}
	}

	/**
	 * 显示已经取出的所有数据与对应的键值对
	 */
	@SuppressWarnings("static-access")
	public void printAllBank() throws FileNotFoundException{
		String list[][] = null;
		// isInit = true;
		try {
			list = file.getData(URL, 0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("读取成功！");
		int rowLength = list.length;
		for (int i = 0; i < rowLength - 1; i++) {
			for (int j = 0; j < rowNum; j++) {
				System.out.printf(list[0][j] + ":"
						+ dataMap.get(i).get(list[0][j]) + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * 以List返回一个Map键值对，键值对对应文档的第行
	 */
	public List<Map<String, String>> getBankMap() {
		return dataMap;
	}
}
