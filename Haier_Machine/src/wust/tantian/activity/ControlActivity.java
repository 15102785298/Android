package wust.tantian.activity;

import wust.tantian.DAO.DBhelper;
import wust.tantian.enity.Enity;
import wust.tantian.view.MultiDirectionSlidingDrawer;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haier_machine.R;

public class ControlActivity extends Activity implements OnClickListener {

	long tempSaved;
	boolean isStop = false;
	int cloud_seconds;
	int thisStep = 1;
	ImageView iv_start;// ℃
	ImageView iv_lighter;
	ImageView iv_stop;
	MultiDirectionSlidingDrawer drawer;
	TextView tv_food;
	TextView tv_temperature;
	TextView tv_once_time;
	TextView tv_fire_length;
	TextView tv_step;
	RelativeLayout relav_back;
	Timerup time_tool;
	int[] colors = { R.color.blue_menu, R.color.pink_menu, R.color.orange_menu };
	String steps;
	String[] step;
	Enity enity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		init();
		tv_step.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ControlActivity.this,
						"第" + thisStep + "步" + "\n" + step[thisStep - 1],Toast.LENGTH_LONG).show();
			
			}
		});
	}

	private void init() {
		Intent intent = getIntent();
		enity = (Enity) intent.getSerializableExtra("menu");
		cloud_seconds = Integer.parseInt(enity.getTimes());
		steps = enity.getSteps();
		step = steps.split(" ");
		relav_back = (RelativeLayout) findViewById(R.id.relav_color);
		iv_lighter = (ImageView) findViewById(R.id.iv_lighter);
		iv_start = (ImageView) findViewById(R.id.iv_start);
		iv_stop = (ImageView) findViewById(R.id.iv_stop);
		tv_food = (TextView) findViewById(R.id.tv_food);
		tv_food.setText(enity.getFood_name());
		tv_food.setOnClickListener(this);
		tv_temperature = (TextView) findViewById(R.id.tv_now_temperature);
		tv_once_time = (TextView) findViewById(R.id.tv_once_timerup);
		tv_fire_length = (TextView) findViewById(R.id.tv_fire_length);
		tv_step = (TextView) findViewById(R.id.tv_step);
		tv_step.setText("第" + thisStep + "步/共" + step.length + "步");
		iv_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				restore_stop();
				time_tool.stop();
				tv_temperature.setText("暂无数据");
				tv_fire_length.setText("暂无数据");
			}
		});
		iv_start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation scale = AnimationUtils.loadAnimation(
						ControlActivity.this, R.anim.anim_scale);
				scale.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						iv_start.setVisibility(View.INVISIBLE);
						iv_stop.setVisibility(View.VISIBLE);
						shadower_lighter();
					}

					private void shadower_lighter() {
						Animation scale_lighter = AnimationUtils
								.loadAnimation(ControlActivity.this,
										R.anim.anim_scale_lighter);
						iv_lighter.setVisibility(View.VISIBLE);
						iv_lighter.startAnimation(scale_lighter);
					}
				});
				iv_start.startAnimation(scale);
				// 此时start的功能按键充当了两个作用
				if (isStop) {
					time_tool = new Timerup((tempSaved) * 1000, 1000);// 触发的方式,循序渐进
				} else { 
					time_tool = new Timerup((cloud_seconds + 1) * 1000, 1000);
					relav_back.setBackgroundColor(getResources().getColor(
							colors[0]));
				}
				time_tool.start();
			}
		});

	}

	public void restore_stop() {
		Animation scale = AnimationUtils.loadAnimation(ControlActivity.this,
				R.anim.anim_scale);
		scale.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				iv_stop.setVisibility(View.INVISIBLE);
				iv_start.setVisibility(View.VISIBLE);
				if (iv_lighter.getVisibility() == View.VISIBLE) {
					iv_lighter.setVisibility(View.INVISIBLE);
					iv_lighter.clearAnimation();
				}
			}
		});
		iv_stop.startAnimation(scale);
	}

	public class Timerup extends CountDownTimer {

		public Timerup(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// if()//和云端的数据进行判断，如果一致{
			// {
			//
			// }//那么显示完成，不用进行下一步操作，否则还是提示下面的内容
			MediaPlayer mMediaPlayer = MediaPlayer.create(ControlActivity.this,
					R.raw.ing);
			mMediaPlayer.start();

			Toast.makeText(
					ControlActivity.this,
					"<" + tv_food.getText().toString() + ">" + "菜谱的第"
							+ thisStep + "步已经完成,请点击步骤栏右侧，进行下一步操作", 1).show();
			Animation animation = AnimationUtils.loadAnimation(
					ControlActivity.this, R.anim.anim_move);
			animation.setDuration(500);
			// tv_once_time.setAnimation(animation);
			tv_once_time.setText("00:00:00");
			tv_step.setAnimation(animation);
			thisStep++;// 更新操作步骤。
			tv_step.setText("第" + thisStep + "步/共" + step.length + "步");
			restore_stop();
			isStop = false;
			tv_temperature.setText("暂无数据");
			tv_fire_length.setText("暂无数据");
		}

		public void stop() {
			super.cancel();
			isStop = true;
		}

		@Override
		public void onTick(long seconds) {
			long true_time = (seconds / 1000);// 命名不影响
			tempSaved = true_time;
			long minute = (true_time / 60);
			long second = (true_time % 60);
			String tempS = "";
			String tempM = "";

			if (true_time > (2 * cloud_seconds) / 3
					&& true_time < cloud_seconds) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[0]));
				tv_temperature.setText("180 ℃");
				tv_fire_length.setText("高火");
			} else if (true_time > cloud_seconds / 3
					&& true_time < (2 * cloud_seconds) / 3) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[1]));
				tv_temperature.setText("150 ℃");
				tv_fire_length.setText("中火");
			} else if (true_time < cloud_seconds / 3) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[2]));
				tv_temperature.setText("100 ℃");
				tv_fire_length.setText("低火");
			}
			if (second < 10) { // 注意逻辑和时间
				tempS = "0" + second;
			} else {
				tempS = "" + second;
			}
			if (minute < 10) {
				tempM = "0" + minute;
			} else {
				tempM = "" + minute;
			}
			Animation animation = new AlphaAnimation(0, 1);
			animation.setDuration(500);
			tv_once_time.setAnimation(animation);
			tv_once_time.setText("00:" + tempM + ":" + tempS);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_food:
			Intent i = new Intent(ControlActivity.this,
					FoodDetailActivity.class);
			i.putExtra("menu", enity);
			startActivity(i);
			break;
		default:
			break;
		}
	}
}
