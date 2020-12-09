

public enum ObjectType {
	PLAYER("images/happyCrab.png"), HOSTAGE("images/BlueCrab.png"), FOOD1("images/level1Button.png"), 
		FOOD2("images/level2Button.png"), FOOD3("images/level3Button.png"), NET("images/net.png"), 
		SKIMMER("images/bucket.png"), SCISSORS("images/scissors.png"), TRASH("images/trashbag.png"),
		PURPLECAN("images/purpleCan.png"),ORANGECAN("images/orangeCan.png"),SHOE("images/shoe.png"),
		OIL("images/oil_spill.png"),NETONLY("images/NetOnlyNoSelection.png"),EMPTY("images/EmptyToolBar.png"),
		NETSELECTED1("images/NetOnlyNet.png"),NETSELECTED2("images/NetSkimmerNet.png"),
		SKIMMERSELECTED2("images/netSkimmerSkimmer1.png"),NETSELECTED3("images/FullToolBarNet.png"),
		SKIMMERSELECTED3("images/FullToolBarSkimmer.png"),
		SCISSORSSELECTED3("images/FullToolBarScissors.png"),FISHFOOD("images/fishFood.png"),
		TUTORIALBAR("images/forkAndKnifeNoSelection.png"),TUTORIALSELECTED("images/forkAndKnifeSelected.png");
	
	private String extension = null;
	
	private ObjectType(String s){
		extension = s;
	}

	public String getName() {
		return extension;
	}
};

