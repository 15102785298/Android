package wust.tantian.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class upLoadClass implements Runnable {
	private String orginfilePath;
	private String filename;

	public static String username = "anonymous";
	public static String password = "dd";
	public static String path = "/pub";
	public static String url = "119.29.181.18";
	public static int port = 21;

	public void setUpLoad(String filename, // �ϴ���FTP�������ϵ��ļ���
			String orginfilePath // �������ļ���
	) {
		this.filename = filename;
		this.orginfilePath = orginfilePath;
	}

	public boolean uploadFile(String filename, // �ϴ���FTP�������ϵ��ļ���
			InputStream input // ������
	) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("GBK");
		try {
			int reply;
			ftp.connect(url, port);// ����FTP������
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login("anonymous", "dd");// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			// ftp.makeDirectory(path);
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	public static boolean saveBitmap2file(Bitmap bmp, String filename) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 100;
		OutputStream stream = null;
		try {
			stream = new FileOutputStream("/sdcard/" + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bmp.compress(format, quality, stream);
	}

	@Override
	public void run() {
		try {
			Bitmap bitMap = BitmapFactory.decodeFile(this.orginfilePath);
			int width = bitMap.getWidth();
			int height = bitMap.getHeight();
			// ������Ҫ�Ĵ�С
			int newWidth = 80;
			int newHeight = 80;
			// �������ű���
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			// ȡ����Ҫ���ŵ�matrix����
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			// �õ��µ�ͼƬ
			bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix,
					true);
			File file = new File(orginfilePath);
			saveBitmap2file(bitMap, file.getName());
			FileInputStream in = new FileInputStream(new File("/sdcard/"
					+ file.getName()));
			boolean flag = uploadFile(this.filename, in);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
