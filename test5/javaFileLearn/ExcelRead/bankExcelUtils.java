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
	 * ��Excel�е�������bankModle���а󶨣���Ҫ��InitUtils
	 * 
	 * @param
	 * @return bankList
	 */
	public List<Map<String, String>> getBankList() {
		return dataMap;
	}

	/**
	 * ȡ��Excel�ж�ȡ��ֵ��
	 * 
	 * @url     :�ļ�·�� 
	 * @listNum        :��ȡ����
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
	 * ��ʾ�Ѿ�ȡ���������������Ӧ�ļ�ֵ��
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
		System.out.println("��ȡ�ɹ���");
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
	 * ��List����һ��Map��ֵ�ԣ���ֵ�Զ�Ӧ�ĵ��ĵ���
	 */
	public List<Map<String, String>> getBankMap() {
		return dataMap;
	}
}
