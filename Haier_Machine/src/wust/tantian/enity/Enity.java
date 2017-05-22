package wust.tantian.enity;

import java.io.Serializable;

import android.R.integer;
import cn.bmob.v3.datatype.BmobFile;

public class Enity implements Serializable {
	private String food_name;
	private String food_pic;
	private String contents;
	private String levels;
	private String effects;
	private String steps;
	private String times;

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	private Integer id = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getEffects() {
		return effects;
	}

	public void setEffects(String effects) {
		this.effects = effects;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_pic() {
		return food_pic;
	}

	public void setFood_pic(String food_pic) {
		this.food_pic = food_pic;
	}
}
