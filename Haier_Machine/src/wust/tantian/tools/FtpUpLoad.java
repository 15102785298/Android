package wust.tantian.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUpLoad {
	/**
	 * 将本地文件上传到FTP服务器上 *
	 */
	public void upLoadFromProduction(String filename, // 上传到FTP服务器上的文件名
			String orginfilePath // 输入流文件名
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
