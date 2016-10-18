package wust.dayin1.adapter;
import java.util.List;

import wust.dayin1.view.JazzyViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends PagerAdapter{//多去尝试，代价。
	
	private List<View> mListViews;//距离不是问题，只要传参就可以了。
	private JazzyViewPager mJazzy;		
	//此处的适配器和view密切相关。所以用构造方法传入。上面每一个的内容是随机建立的。
	
	public MyViewPagerAdapter(List<View> mListViews,JazzyViewPager mJazzy) {
		this.mListViews = mListViews;//边做项目边思考。
		this.mJazzy=mJazzy;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) 	{	
		container.removeView(mListViews.get(position));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		 container.addView(mListViews.get(position), 0);
		 mJazzy.setObjectForPosition(mListViews.get(position), position);  //加上去。
		 return mListViews.get(position);	 
	}
	@Override
	public int getCount() {			
		return  mListViews.size();
	}//敢于去尝试
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {			
		return arg0==arg1;
	}
}

