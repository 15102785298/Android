package wust.tantian.enity;

import java.util.Date;

import cn.bmob.v3.BmobObject;

public class CommunityToFood extends BmobObject{
	private String content;
	private String userName;
	private String aim_id;
	private Integer id;
	private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAim_id() {
		return aim_id;
	}

	public void setAim_id(String aim_id) {
		this.aim_id = aim_id;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
