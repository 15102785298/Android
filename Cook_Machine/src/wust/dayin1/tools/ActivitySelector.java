package wust.dayin1.tools;

import wust.dayin1.activity.CloudActivity;
import wust.dayin1.activity.ComentFoodActivity;
import wust.dayin1.activity.ControlActivity;
import wust.dayin1.activity.FoodDetailActivity;
import wust.dayin1.activity.LauncherActivity;
import wust.dayin1.activity.OrderActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.haier_machine.R;


public class ActivitySelector {
	/**
	 * ����activity��class��ɵľ�̬����
	 */
	private static Class[] ActyArr = { 
		LauncherActivity.class,  //0
		ControlActivity.class,   //1
		ControlActivity.class,      //2
		CloudActivity.class,     //3
		LauncherActivity.class,  //4
		LauncherActivity.class,    //5
		FoodDetailActivity.class,//6
		LauncherActivity.class, //7
		ComentFoodActivity.class,//8
		OrderActivity.class      //9
	};
	
	/**
	 * ���ݴ����activity�±꣬������Ӧ��Activity
	 * 
	 * @param actyFrom
	 *            (Activity) ��ǰActivity
	 * @param to
	 *            (int) Ҫ��ת����Activity��ActyArr[]�е��±�  // 0���˵�// 1����// 2ͼ���// 3����// 4����// 5΢��// 6����// 7��ͼ// 8�羰// 9�绰
	 */
	public static void openActivity(Activity actyFrom, int to) {
		Intent intent = new Intent(actyFrom.getApplicationContext(),
				ActyArr[to]);
		actyFrom.startActivity(intent);
		// �����л���ʽ���������startActivity()֮�󣬷���������
		actyFrom.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	
	/**
	 * ���ݴ����activity�±꣬������Ӧ��Activity
	 * @param actyFrom ��Activity����ǰActivity
	 * @param to (int)Ҫ��ת����Activity��ActyArr[]�е��±�  // 0���˵�// 1����// 2ͼ���// 3����// 4����// 5΢��// 6����// 7��ͼ// 8�羰// 9�绰
	 * @param bundle ҪЯ��������
	 */
	public static void openActivity(Activity actyFrom, int to , Bundle bundle) {
		Intent intent = new Intent(actyFrom.getApplicationContext(), ActyArr[to]);
		intent.putExtras(bundle) ;
		actyFrom.startActivity(intent);

		// �����л���ʽ���������startActivity()֮�󣬷���������
		actyFrom.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	/**
	 * �ر�activity
	 * 
	 * @param acty
	 *            (Activity) ��ǰActivity����Ҫ�رյ�Activity
	 */
	public static void closeActivity(Activity acty) {
		acty.finish();
		// �����л���ʽ���������startActivity()֮�󣬷���������
		acty.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	
}
