package test5;

import AnnValidate.Length;
import AnnValidate.Required;
import AnnValidate.Validation;

public class sortModle {

	@Validation
	@Length(maxLength = 9, minLength = 0)
	public String sortId = "sadfasd";
	@Validation
	@Required
	public String msg = "ddd";
	@Validation
	@Length(maxLength = 9, minLength = 0)
	public String msg2 = "ddd";
	@Validation
	@Length(maxLength = 9, minLength = 0)
	public String msg3 = "ddd";
	@Validation
	@Length(maxLength = 9, minLength = 0)
	public String msg4 = "ddd";
	/*
	 * public double getSortId() { return sortId; }
	 * 
	 * public void setSortId(double sortid) { sortId = sortid; }
	 * 
	 * public String getMsg() { return msg; }
	 * 
	 * public void setMsg(String msg) { this.msg = msg; }
	 * 
	 * @Override public int compareTo(sortModle o1) { // TODO Auto-generated
	 * method stub if (o1.sortId > sortId) { return -1; } else if (o1.sortId <
	 * sortId) { return 1; } return 0; }
	 * 
	 * @Override public String toString() { // TODO Auto-generated method stub
	 * return String.format("People [no=%d, name=%s]", sortId, msg); }
	 */

}
