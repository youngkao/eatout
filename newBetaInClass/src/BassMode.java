
public enum BassMode {

    DEFAULT("ironFish"), CONFUSE("pointer"), ATTAC("blackMinnow");

	private String name = null;
	
	private BassMode(String s){
		name = s;
	}

	public String getName() {
		return name;
	}
};