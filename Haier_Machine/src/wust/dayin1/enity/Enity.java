package wust.dayin1.enity;

import java.io.Serializable;

import cn.bmob.v3.datatype.BmobFile;

public class Enity implements Serializable{
	private String food_name;
	private BmobFile food_pic;
	private String contents;
	private String levels;
	private String effects;
	private String steps;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public BmobFile getFood_pic() {
		return food_pic;
	}

	public void setFood_pic(BmobFile food_pic) {
		this.food_pic = food_pic;
	}
}
