
public class Main {
	static Node<String> root; // used for tree system

	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

	static void CreateTreeSystem() { // creates the default tree system values
		root = new Node<String>("Lexus", "Company");

		// create the category of car
		root.addChild(new Node<String>("Sedan", "Type of Car"));
		root.addChild(new Node<String>("SUV", "Type of Car"));
		root.addChild(new Node<String>("Performance", "Type of Car"));
		root.addChild(new Node<String>("Hybrid", "Type of Car"));

		// add cars to each category as well as their properties
		Node<String> sed = root.indexOf("Sedan");
		Node<String> suv = root.indexOf("SUV");
		Node<String> pfm = root.indexOf("Performance");
		Node<String> usd = root.indexOf("Used");

		// add sedans
		sed.addChild(new Node<String>("IS", "Model"));
		sed.indexOf("IS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("IS").addChild(new Node<String>("41250", "Price"));
		sed.addChild(new Node<String>("RC", "Model"));
		sed.indexOf("GS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("GS").addChild(new Node<String>("64150", "Price"));
		sed.addChild(new Node<String>("ES", "Model"));
		sed.indexOf("ES").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("ES").addChild(new Node<String>("45100", "Price"));

		// add SUVs
		suv.addChild(new Node<String>("NX", "Model"));
		suv.indexOf("NX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("NX").addChild(new Node<String>("44150", "Price"));
		suv.addChild(new Node<String>("RX", "Model"));
		suv.indexOf("RX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("RX").addChild(new Node<String>("56050", "Price"));

		// add Performance
		pfm.addChild(new Node<String>("GS F", "Model"));
		pfm.indexOf("GS F").addChild(new Node<String>("2020", "Year"));
		pfm.indexOf("GS F").addChild(new Node<String>("99400", "Price"));
		pfm.addChild(new Node<String>("LC", "Model"));
		pfm.indexOf("LC").addChild(new Node<String>("2020", "Year"));
		pfm.indexOf("LC").addChild(new Node<String>("103050", "Price"));
		
		//add used
		usd.addChild(new Node<String>("GX", "Model"));
		pfm.indexOf("LC").addChild(new Node<String>("2003", "Year"));
		pfm.indexOf("LC").addChild(new Node<String>("4000", "Price"));
	}

}
