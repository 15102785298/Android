package wust.dayin1.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogDemo
{
	// ������Ϣ�Ի���,�������Լ�д�ķ��������Σ����ò���������ȥ����
	public static void builder(Context context, String title, String message)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle(title);
			builder.setMessage(message);
			builder.setPositiveButton("ȷ��", null);
			builder.create();
			builder.show();//ѧϰ����
		}
	
	public static void builder2(final Activity context, String title, String message)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		builder.create();
		builder.show();//ѧϰ����
	}
};      //������д�������������java��������...��

