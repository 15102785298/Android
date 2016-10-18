package wust.dayin1.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogDemo
{
	// 错误消息对话框,这是你自己写的方法，传参，设置参数。穿进去参数
	public static void builder(Context context, String title, String message)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle(title);
			builder.setMessage(message);
			builder.setPositiveButton("确定", null);
			builder.create();
			builder.show();//学习方法
		}
	
	public static void builder2(final Activity context, String title, String message)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		builder.create();
		builder.show();//学习方法
	}
};      //方法是写在类里面的类在java里面很灵活...。

