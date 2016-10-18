package wust.dayin1.activity;

import wust.dayin1.tools.ActivitySelector;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.haier_machine.R;


/**
 * 启动界面
 * @author tantian
 *
 */
public class LauncherActivity extends Activity {
	private Handler handler=new Handler();//new一个handler一定要开启loop，主线程默认开启
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		Runnable runnable=new Runnable() {	//要做的事情,封装在里面
			@Override
			public void run() {
				ActivitySelector.openActivity(LauncherActivity.this, 3) ;
				LauncherActivity.this.finish();//技巧.调用destroy。
			}
		};
			
		handler.postDelayed(runnable, 2500);
	}
}
