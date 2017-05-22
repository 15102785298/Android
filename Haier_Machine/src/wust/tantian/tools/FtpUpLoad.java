package wust.tantian.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUpLoad {
	/**
	 * �������ļ��ϴ���FTP�������� *
	 */
	public void upLoadFromProduction(String filename, // �ϴ���FTP�������ϵ��ļ���
			String orginfilePath // �������ļ���
	) {
		upLoadClass runnable = new upLoadClass();
		runnable.setUpLoad(filename, orginfilePath);
		new Thread(runnable).start();
	}

	public void upLoadWebpPic(String filename, String orginfilepath) {
		upLoadPicClass runnable = new upLoadPicClass();
		runnable.setUpLoad(filename, orginfilepath);
		new Thread(runnable).start();
	}
}
