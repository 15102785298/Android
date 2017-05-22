package wust.tantian.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {

	public static final int REQUEST_CODE_FROM_CAMERA = 1001;
	public static final int REQUEST_CODE_FROM_ALBUM = 1002;

	/**
	 * �������ͼƬ��Uri��ַ
	 */
	public static Uri imageUriFromCamera;

	/**
	 * ��ȡͼƬѡ��Ի���
	 * 
	 * @param activity
	 */
	public static void showImagePickDialog(final Activity activity) {
		String title = "ѡ���ȡͼƬ��ʽ";
		String[] item = new String[] { "����", "���" };
		new AlertDialog.Builder(activity).setTitle(title)
				.setItems(item, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							PickImageFromCamera(activity);
							break;
						case 1:
							PickImageFromAlbum(activity);
							break;

						default:
							break;
						}
					}
				}).show();
	}

	/**
	 * ������ȡͼƬ
	 * 
	 * @param activity
	 */
	public static void PickImageFromAlbum(final Activity activity) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);
	}

	/**
	 * �������ȡͼƬ
	 * 
	 * @param activity
	 */
	public static void PickImageFromCamera(final Activity activity) {
		imageUriFromCamera = creatImageUri(activity);
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
		activity.startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
	}

	/**
	 * ����һ��Uri���������պ��ͼƬ
	 * 
	 * @param context
	 * @return
	 */
	public static Uri creatImageUri(Context context) {
		String name = "myImage" + System.currentTimeMillis();
		ContentValues cv = new ContentValues();
		cv.put(MediaStore.Images.Media.TITLE, name);
		cv.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
		cv.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		Uri uri = context.getContentResolver().insert(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
		return uri;
	}

	/**
	 * ɾ��ͼƬ
	 * 
	 * @param context
	 * @param uri
	 */
	public static void deleteImageUri(Context context, Uri uri) {
		context.getContentResolver().delete(imageUriFromCamera, null, null);
	}

	/**
	 * ͼƬת��ΪwebP��ʽ
	 * 
	 * @param orginfilePath
	 *            ͼƬ·��
	 * @param width_to
	 *            ��Ҫ��ͼƬ���
	 * @param hight_to
	 *            ��Ҫ��ͼƬ�߶�
	 * */
	public static void FomotImageToWebP(String orginfilePath, int width_to,
			int hight_to) throws FileNotFoundException {
		Bitmap bitMap = BitmapFactory.decodeFile(orginfilePath);
		int width = bitMap.getWidth();
		int height = bitMap.getHeight();
		// ������Ҫ�Ĵ�С
		int newWidth = width_to;
		int newHeight = hight_to;
		// �������ű���
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ȡ����Ҫ���ŵ�matrix����
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// �õ��µ�ͼƬ
		bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);

		File form_file = new File(orginfilePath);
		File to_file = new File("/sdcard/" + form_file.getName());
		FileOutputStream out = new FileOutputStream(to_file);
		// ��ʽת��
		bitMap.compress(Bitmap.CompressFormat.WEBP, 90, out);
	}

}
