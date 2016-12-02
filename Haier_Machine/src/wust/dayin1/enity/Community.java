package wust.dayin1.enity;

import cn.bmob.v3.BmobObject;

public class Community extends BmobObject {

    private String title;
    private String content;
    private String userName;
    private String time;
    
    public String getTime(){
    	return time;
    }
    public void setTime(String time){
    	this.time = time;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

	public void setUserName(String username) {
		this.userName = username;
	}


}
