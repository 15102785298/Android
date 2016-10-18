package wust.dayin1.adapter;
import java.util.List;

import wust.dayin1.view.JazzyViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends PagerAdapter{//��ȥ���ԣ����ۡ�
	
	private List<View> mListViews;//���벻�����⣬ֻҪ���ξͿ����ˡ�
	private JazzyViewPager mJazzy;		
	//�˴�����������view������ء������ù��췽�����롣����ÿһ������������������ġ�
	
	public MyViewPagerAdapter(List<View> mListViews,JazzyViewPager mJazzy) {
		this.mListViews = mListViews;//������Ŀ��˼����
		this.mJazzy=mJazzy;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) 	{	
		container.removeView(mListViews.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		 container.addView(mListViews.get(position), 0);
		 mJazzy.setObjectForPosition(mListViews.get(position), position);  //����ȥ��
		 return mListViews.get(position);	 
	}
	@Override
	public int getCount() {			
		return  mListViews.size();
	}//����ȥ����
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {			
		return arg0==arg1;
	}
}

