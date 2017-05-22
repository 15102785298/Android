package wust.tantian.view;

import com.example.haier_machine.R;

import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RefreshListView extends ListView implements OnScrollListener{

	View header;//����ˢ��ʱ���ֵĶ�������View
	int headerHeight;//header�ĸ߶�
	int firstVisibleItem;//��ǰ�����һ���ɼ���item��λ��
	
	boolean isFlag;//��־������listView��˰���
	int startY;//���µ�Yֵ
	
	int state;//��ǰ״̬
	final int NONE = 0;//����״̬
	final int PULL = 1;//��ʾ����״̬
	final int RELEASE = 2;//��ʾ�ͷ�״̬
	final int REFRESH = 3;//��ʾ����ˢ��״̬
	
	final String TAG = "RefreshListView";
	
	int scrollState;//��ǰ����״̬
	
	IRefreshListener iRefreshlistener;//ˢ�����ݵĽӿ�
	
	int updateTime;
	int time;//����ˢ��ʱ����
	//RefreshListView�Ĺ��캯��
	public RefreshListView(Context context) {
		super(context);
		initView(context);
	}
	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}
	
	/**
	 * ��ʼ�����棬��Ӷ������ֵ�ListView
	 */
	private void initView(Context context){
		//LayoutInflater�����Ǽ��ز���
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.header_layout, null);
	    measureView(header);
		headerHeight = header.getMeasuredHeight();
		Log.i("headerHeight", ""+headerHeight);
	    topPadding(-headerHeight);
		this.addHeaderView(header);
		this.setOnScrollListener(this);
		
		Time t = new Time();
		t.setToNow();
		updateTime = t.hour*60+t.minute;
		Log.i(TAG, "init--->"+updateTime);
	}
	/**
	 * ���ö������ֵ��ϱ߾�
	 * @param topPadding
	 */
	private void topPadding(int topPadding){
		//���ö�����ʾ�ı߾�
		//���˶����ò���ֵtopPadding�⣬����������headerĬ�ϵ�ֵ
		header.setPadding(header.getPaddingLeft(), topPadding, 
				header.getPaddingRight(), header.getPaddingBottom());
		//ʹheader��Ч����������onDraw()�ػ�View
	    header.invalidate();
	}
	/**
	 * ֪ͨ�����֣�ռ�õĿ�͸�
	 */
	private void measureView(View view){
		//LayoutParams are used by views to tell their parents 
		//how they want to be laid out.
		//LayoutParams��view�����������ǵĸ���������Ӧ�ñ���������
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if(p==null){
			//��������:width,height
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		//getChildMeasureSpec:��ȡ��View��widthMeasureSpec��heightMeasureSpecֵ
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if(tempHeight>0){
			height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
		}else{
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		this.scrollState = scrollState;
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.firstVisibleItem = firstVisibleItem;
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(firstVisibleItem == 0){
				isFlag = true;//ListView��˰��£���־ֵ��Ϊ��
				startY = (int)ev.getY();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			break;
		case MotionEvent.ACTION_UP:
			if(state == RELEASE){
				state = REFRESH;
				//��������
				refreshViewByState();
				iRefreshlistener.onRefresh();
			}else if(state == PULL){
				state = NONE;
				isFlag = false;
				refreshViewByState();
			}
			break;
		}
		return super.onTouchEvent(ev);
	}
	/**
	 * �ж������ƶ����̵Ĳ���
	 * @param ev
	 */
	private void onMove(MotionEvent ev){
		//���������˰��£���ֱ�ӷ���
		if(!isFlag){
			return;
		}
		int currentY = (int)ev.getY();//��ǰ��Yֵ
		int space = currentY - startY;//�û��������ľ���
		int topPadding = space - headerHeight;//������ʾView�ඥ���ľ���ֵ
		switch (state) {
		//����״̬
		case NONE:
			if(space>0){
				state = PULL;//�����ľ������0����״̬��ΪPULL(��ʾ��������)
				refreshViewByState();//����״̬�Ĳ�ͬ����View
			}
			break;
        case PULL:
        	topPadding(topPadding);
			if(space>headerHeight+30//�����ľ������header�ĸ߶ȼ�30���û�������Ļ����ָ������Ļ��
					&&scrollState == SCROLL_STATE_TOUCH_SCROLL ){
				state = RELEASE;//��״̬��ΪRELEASE(��ʾ�ɿ�����)
				refreshViewByState();
			}
			break;
        case RELEASE:
        	topPadding(topPadding);
        	if(space<headerHeight+30){//�û������ľ��벻��
				state = PULL;         //��״̬��ΪPULL
				refreshViewByState();
			}else if(space<=0){  //�û������ľ���Ϊ����ֵ
				state = NONE;    //��״̬��ΪNONE
				isFlag = false;  //��־��Ϊfalse
				refreshViewByState();
			}
			break;
		}
	}
	/**
	 * ���ݵ�ǰ״̬state,�ı������ʾ
	 * state:
	 *      NONE���޲���
	 *      PULL����������ˢ��
	 *      RELEASE���ɿ�����ˢ��
	 *      REFREASH������ˢ��
	 */
	private void refreshViewByState(){
		//��ʾ
		TextView tips = (TextView)header.findViewById(R.id.tips);
		//��ͷ
		ImageView arrow = (ImageView)header.findViewById(R.id.arrow);
		//������
		ProgressBar progress = (ProgressBar)header.findViewById(R.id.progress);
		//��ͷ�Ķ���Ч��1����0��ת��180�ȣ�����ͷ�ɳ���תΪ����
		RotateAnimation animation1 = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF,0.5f,
				RotateAnimation.RELATIVE_TO_SELF,0.5f);
		animation1.setDuration(500);
		animation1.setFillAfter(true);
		//��ͷ�Ķ���Ч��2����180��ת��0�ȣ�����ͷ�ɳ���תΪ����
		RotateAnimation animation2 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF,0.5f,
				RotateAnimation.RELATIVE_TO_SELF,0.5f);
		animation2.setDuration(500);
		animation2.setFillAfter(true);
		
		switch (state) {
		case NONE:                     //����״̬
			arrow.clearAnimation();    //�����ͷ����Ч��
			topPadding(-headerHeight); //����header���붥���ľ���
			break;

		case PULL:                                //����״̬
			arrow.setVisibility(View.VISIBLE);    //��ͷ��Ϊ�ɼ�
			progress.setVisibility(View.GONE);    //��������Ϊ���ɼ�
			tips.setText("��������ˢ��");              //��ʾ������Ϊ"��������ˢ��"
			arrow.clearAnimation();               //���֮ǰ�Ķ���Ч��������������Ϊ����Ч��2
			arrow.setAnimation(animation2);
			break;
 
		case RELEASE:                            //����״̬
			arrow.setVisibility(View.VISIBLE);   //��ͷ��Ϊ�ɼ�
			progress.setVisibility(View.GONE);   //��������Ϊ���ɼ�
			tips.setText("�ɿ�����ˢ��");             //��ʾ������Ϊ"�ɿ�����ˢ��"
			arrow.clearAnimation();              //���֮ǰ�Ķ���Ч��������������Ϊ����Ч��2
			arrow.setAnimation(animation1);
			break;

		case REFRESH:                             //����״̬
			topPadding(50);                       //���붥���ľ�������Ϊ50
			arrow.setVisibility(View.GONE);       //��ͷ��Ϊ���ɼ�
			progress.setVisibility(View.VISIBLE); //��������Ϊ�ɼ�
			tips.setText("����ˢ��...");             //��ʾ������Ϊ""����ˢ��..."
			arrow.clearAnimation();                //�������Ч��
			break;

		}
	}
	/**
	 * ˢ�����
	 */
	public void refreshComplete(){
		state = NONE;   //״̬��Ϊ����״̬
		isFlag = false; //��־��Ϊfalse
		refreshViewByState();
		//������ʾ����ʱ����
		Time t = new Time();
		t.setToNow();
		time = t.hour*60+t.minute-updateTime;
		updateTime = t.hour*60+t.minute;
	    TextView lastUpdateTime = (TextView)findViewById(R.id.time);
	    lastUpdateTime.setText(time+"����ǰ����");
	}
	public void setInterface(IRefreshListener listener){
		this.iRefreshlistener = listener;
	}
	/**
	 * ˢ�����ݽӿ�
	 * @author lenovo
	 *
	 */
	public interface IRefreshListener{
		public void onRefresh();
	}
}
