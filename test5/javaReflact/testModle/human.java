package testModle;

public class human {

	private String eyes;
	private String foot="foot";

	@SuppressWarnings("unused")
	private String getEyes() {
		return eyes;
	}

	public void setEyes(String eyes,String sdf) {
		this.eyes = eyes;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

}
