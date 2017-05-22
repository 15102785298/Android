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
	ImageView iv_start;// ��
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
						"��" + thisStep + "��" + "\n" + step[thisStep - 1],Toast.LENGTH_LONG).show();
			
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
		tv_step.setText("��" + thisStep + "��/��" + step.length + "��");
		iv_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				restore_stop();
				time_tool.stop();
				tv_temperature.setText("��������");
				tv_fire_length.setText("��������");
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
				// ��ʱstart�Ĺ��ܰ����䵱����������
				if (isStop) {
					time_tool = new Timerup((tempSaved) * 1000, 1000);// �����ķ�ʽ,ѭ�򽥽�
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
			// if()//���ƶ˵����ݽ����жϣ����һ��{
			// {
			//
			// }//��ô��ʾ��ɣ����ý�����һ����������������ʾ���������
			MediaPlayer mMediaPlayer = MediaPlayer.create(ControlActivity.this,
					R.raw.ing);
			mMediaPlayer.start();

			Toast.makeText(
					ControlActivity.this,
					"<" + tv_food.getText().toString() + ">" + "���׵ĵ�"
							+ thisStep + "���Ѿ����,�����������Ҳ࣬������һ������", 1).show();
			Animation animation = AnimationUtils.loadAnimation(
					ControlActivity.this, R.anim.anim_move);
			animation.setDuration(500);
			// tv_once_time.setAnimation(animation);
			tv_once_time.setText("00:00:00");
			tv_step.setAnimation(animation);
			thisStep++;// ���²������衣
			tv_step.setText("��" + thisStep + "��/��" + step.length + "��");
			restore_stop();
			isStop = false;
			tv_temperature.setText("��������");
			tv_fire_length.setText("��������");
		}

		public void stop() {
			super.cancel();
			isStop = true;
		}

		@Override
		public void onTick(long seconds) {
			long true_time = (seconds / 1000);// ������Ӱ��
			tempSaved = true_time;
			long minute = (true_time / 60);
			long second = (true_time % 60);
			String tempS = "";
			String tempM = "";

			if (true_time > (2 * cloud_seconds) / 3
					&& true_time < cloud_seconds) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[0]));
				tv_temperature.setText("180 ��");
				tv_fire_length.setText("�߻�");
			} else if (true_time > cloud_seconds / 3
					&& true_time < (2 * cloud_seconds) / 3) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[1]));
				tv_temperature.setText("150 ��");
				tv_fire_length.setText("�л�");
			} else if (true_time < cloud_seconds / 3) {
				relav_back.setBackgroundColor(getResources()
						.getColor(colors[2]));
				tv_temperature.setText("100 ��");
				tv_fire_length.setText("�ͻ�");
			}
			if (second < 10) { // ע���߼���ʱ��
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
