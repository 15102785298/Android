package test5;

public class testException extends Throwable {
	public testException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "ddddd";
	}
	
	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return new Exception();
	}
	
	public enum LuoErrorCode {
		NULL_OBJ("LUO001","����Ϊ��"),
	    ERROR_ADD_USER("LUO002","����û�ʧ��"),
	    UNKNOWN_ERROR("LUO999","ϵͳ��æ�����Ժ�����....");

	    private String value;
	    private String desc;

	    private LuoErrorCode(String value, String desc) {
	        this.setValue(value);
	        this.setDesc(desc);
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    @Override
	    public String toString() {
	        return "[" + this.value + "]" + this.desc;
	    }}
}
