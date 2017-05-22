package wust.tantian.activity;

import wust.tantian.fragment.FoodFragment;
import wust.tantian.fragment.FunctionFragment;
import wust.tantian.fragment.PeopleFragment;
import wust.tantian.tools.ActivitySelector;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.haier_machine.R;
//Done is better than perfect
public class ClassifyActivity extends FragmentActivity {
	
	TextView tv_classify_back;
	Context context;
	FragmentManager manager;
	RadioButton mainMenuRB[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classify);
		context = getApplicationContext();
		manager = getSupportFragmentManager();	
		init();
	}
	
	private void init() {
		tv_classify_back=(TextView)findViewById(R.id.tv_classify_back);
		tv_classify_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivitySelector.closeActivity(ClassifyActivity.this);
			}
		});
		mainMenuRB = new RadioButton[4] ;
		mainMenuRB[0] = (RadioButton)findViewById(R.id.rb_function) ;
		mainMenuRB[1] = (RadioButton)findViewById(R.id.rb_people) ;
		mainMenuRB[2] = (RadioButton)findViewById(R.id.rb_food) ;
		mainMenuRB[3] = (RadioButton)findViewById(R.id.rb_more) ;
		mainMenuRB[0].setChecked(true) ;
		setRBEnable(0) ;
		
		MainMenuClickListener listener = new MainMenuClickListener() ;
		
		for(int i = 0 ; i < mainMenuRB.length ; i++){
			mainMenuRB[i].setOnClickListener(listener) ;
		}
		
	}
	
	/**
	 * 设置RadioButton的可用性，防止重复点击同一项
	 * @param n
	 */
	private void setRBEnable(int n){
		for(int i = 0 ; i < mainMenuRB.length ; i++){
			if(mainMenuRB[i].isChecked()){
				mainMenuRB[i].setEnabled(false) ;
			}
			else
				mainMenuRB[i].setEnabled(true) ;
		}
	}
	
	/**
	 * 按钮响应处理
	 * @author BanXia
	 *
	 */
	private class MainMenuClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {//模仿
			FragmentTransaction ft = manager.beginTransaction();
			ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			Fragment fragment = null ;
			switch(v.getId()){
				case R.id.rb_function :
					setRBEnable(0) ;
					fragment = new FunctionFragment();        
					break ;
					
				case R.id.rb_people :
					setRBEnable(1) ;
					fragment = new PeopleFragment();
					break ;
					
				case R.id.rb_food :
					setRBEnable(2) ;
					fragment = new FoodFragment();
					break ;
					
				case R.id.rb_more :
					setRBEnable(3) ;
					fragment = new FunctionFragment();
					break ;	
			}
			ft.replace(R.id.classify_main_content, fragment) ;
			ft.commit() ;
		}
	}
}
