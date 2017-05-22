package wust.tantian.tools;

import wust.tantian.DAO.DBhelper;
import wust.tantian.activity.BuyFoodActivity;
import wust.tantian.activity.FoodDetailActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
	/**
	 * ������ת������ʳ�Ľ���ĵ���
	 * */
	public static void builder_buyfood(final Activity context, String title, final String message,final String foodname)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("����ʳ�İ�", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent=new Intent(context.getApplicationContext(),BuyFoodActivity.class);
				intent.putExtra(DBhelper.CONTENT, message);
				intent.putExtra(DBhelper.NAME, foodname);
				context.startActivity(intent);
			}
		});
		builder.setNegativeButton("�ر�", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.create();
		builder.show();//ѧϰ����
	}
};      //������д�������������java��������...��

