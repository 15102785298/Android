package wust.dayin1.activity;

import wust.dayin1.tools.ActivitySelector;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.haier_machine.R;


/**
 * ��������
 * @author tantian
 *
 */
public class LauncherActivity extends Activity {
	private Handler handler=new Handler();//newһ��handlerһ��Ҫ����loop�����߳�Ĭ�Ͽ���
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		Runnable runnable=new Runnable() {	//Ҫ��������,��װ������
			@Override
			public void run() {
				ActivitySelector.openActivity(LauncherActivity.this, 3) ;
				LauncherActivity.this.finish();//����.����destroy��
			}
		};
			
		handler.postDelayed(runnable, 2500);
	}
}
