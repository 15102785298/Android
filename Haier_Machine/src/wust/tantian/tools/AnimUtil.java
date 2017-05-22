package wust.tantian.tools;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.haier_machine.R;

public class AnimUtil {
	public static Animation getAnimation1(Context context){	
		Animation scale =
					AnimationUtils.loadAnimation(context,
	    				 R.anim.anim_scale);
		return scale;
	}	
	
	public static Animation getAnimation2(Context context){	
		Animation scale =
					AnimationUtils.loadAnimation(context,
	    				 R.anim.anim_scale_step);
		return scale;
	}	
	//´ý¶¨
}
