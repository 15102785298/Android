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
	 * 部分activity的class组成的静态数组
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
	 * 根据传入的activity下标，开启相应的Activity
	 * 
	 * @param actyFrom
	 *            (Activity) 当前Activity
	 * @param to
	 *            (int) 要跳转到的Activity在ActyArr[]中的下标  // 0主菜单// 1设置// 2图书馆// 3教务// 4卡务// 5微聊// 6天气// 7地图// 8风景// 9电话
	 */
	public static void openActivity(Activity actyFrom, int to) {
		Intent intent = new Intent(actyFrom.getApplicationContext(),
				ActyArr[to]);
		actyFrom.startActivity(intent);
		// 界面切换样式，必须放在startActivity()之后，否则不起作用
		actyFrom.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	
	/**
	 * 根据传入的activity下标，开启相应的Activity
	 * @param actyFrom （Activity）当前Activity
	 * @param to (int)要跳转到的Activity在ActyArr[]中的下标  // 0主菜单// 1设置// 2图书馆// 3教务// 4卡务// 5微聊// 6天气// 7地图// 8风景// 9电话
	 * @param bundle 要携带的数据
	 */
	public static void openActivity(Activity actyFrom, int to , Bundle bundle) {
		Intent intent = new Intent(actyFrom.getApplicationContext(), ActyArr[to]);
		intent.putExtras(bundle) ;
		actyFrom.startActivity(intent);

		// 界面切换样式，必须放在startActivity()之后，否则不起作用
		actyFrom.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	/**
	 * 关闭activity
	 * 
	 * @param acty
	 *            (Activity) 当前Activity，即要关闭的Activity
	 */
	public static void closeActivity(Activity acty) {
		acty.finish();
		// 界面切换样式，必须放在startActivity()之后，否则不起作用
		acty.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}
	
}
