package wust.dayin1.enity;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;
    private String password;
    private String email;

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
