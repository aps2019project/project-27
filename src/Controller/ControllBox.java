package Controller;
public class ControllBox {
	private String type;
	private String region;
	public ControllBox(String region,String type){
	    this.region = region;
	    this.type = type;
    }
	public String getRegion () {
		return region;
	}
	public void setRegion ( String region ) {
		this.region = region;
	}
	public String getType () {
		return type;
	}
}
