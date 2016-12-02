package wust.dayin1.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtils {

		public static final int REQUEST_CODE_FROM_CAMERA = 1001;
		public static final int REQUEST_CODE_FROM_ALBUM = 1002;
		
		/**
		 * 存放拍照图片的Uri地址
		 */
		public static Uri imageUriFromCamera;

		/**
		 * 获取图片选择对话框
		 * 
		 * @param activity
		 */
		public static void showImagePickDialog(final Activity activity) {
			String title = "选择获取图片方式";
			String[] item = new String[] { "拍照", "相册" };
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
		 * 打开相册获取图片
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
		 * 打开相机获取图片
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
		 * 创建一条Uri，保存拍照后的图片
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
		 * 删除图片
		 * @param context
		 * @param uri
		 */
		public static void deleteImageUri(Context context,Uri uri){
			context.getContentResolver().delete(imageUriFromCamera, null, null);
		}

	

}
