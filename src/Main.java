import java.util.List;
import java.util.Scanner;

public class Main {
	static Node<String> root; // used for tree system

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		TreeSystem();
	}

	static void TreeSystem() {
		CreateTreeSystem(); // create all the entries
		Scanner s = new Scanner(System.in); // used to get user input
		String input = ""; // stores user input
		String input2; // stores user input

		while (!input.equalsIgnoreCase("e")) {
			System.out.println("Lexus Automotive System\nSearch (S)\tCreate Entry (C)\tDelete (D)\tExit(E)");
			input = s.next();

			switch (input.toUpperCase()) {
			case "S": // search for an item and display the parent (e.g. search a price and find the
						// car associated with that price
				List<Node<String>> searchResult;
				System.out.print("Enter Item to search for: ");
				s.nextLine();
				input = s.nextLine();
			
				searchResult = root.Search(input); // run search method

				if (searchResult == null) { // nothing found
					System.out.println("No results found");
				} else {
					System.out.println("Here are the results found for '" + input + "'. There are "
							+ root.indexOfAll(input).size() + " results.");
					for (Node<String> n : searchResult) {
						System.out.print(n.getType() + ": " + n.getData() + "\t");
					}
					System.out.println("\n");
				}

				// System.out.print("Enter Item to search for: ");
				// input = s.next();
				// if (root.indexOfAll(input).size() == 1) { // 1 item found
				//
				// } else if (root.indexOfAll(input).size() > 1) { // multiple items found
				//
				// System.out.println("Here are the results found for " + input + ". There are "
				// + root.indexOfAll(input).size() + " results.");
				// for (Node<String> n : root.indexOfAll(input)) { // output
				// if (input.equalsIgnoreCase("Price") || input.equalsIgnoreCase("Year")) { //
				// if searching by year or price, then display parent node
				// System.out.print(n.getParent().getData() + "\t");
				// }else { //display search result children
				// System.out.println(n.getData() + "\t");
				// }
				// }
				// System.out.println("\n");
				//
				// } else { // not found
				// System.out.println("Item not found");
				// }
				break;
			case "C": // create entry
				System.out.println("Which item do you want to create?\nCategory (C)\tCar Model (M)");
				input = s.next();
				if (input.equalsIgnoreCase("C")) { // create new category
					System.out.println("What is the name of the new category?");
					s.nextLine();
					input = s.nextLine();
					root.addChild(new Node<String>(input, "Type of Car")); // create new category under root
				} else if (input.equalsIgnoreCase("M")) {// create new model
					if (root.getChildren().size() > 0) { // if there is at least one category then user can choose one
						System.out.println(
								"Which category would you like to create it in? (Select the corresponding number)");
						int count = 1;
						for (Node<String> n : root.getChildren()) { // print all the categories
							System.out.print(count + ". " + n.getData() + "     ");
							count++;
						}
						input = s.next(); // choose category first

						// if size is in range
						if (Integer.parseInt(input) <= root.getChildren().size() && Integer.parseInt(input) >= 1
								&& isInteger(input)) {
							System.out.println("What model do you want to create?");
							s.nextLine();
							input2 = s.nextLine();
							System.out.println("\n");

							Node<String> newCar = new Node<String>(input2, "Model");
							root.getChildren().get(Integer.parseInt(input) - 1).addChild(newCar);
							do { // create year node
								System.out.println("What Year is " + newCar.getData() + "?");
								input = s.next();
								if (!isInteger(input)) {
									invalid();
								}
							} while (!isInteger(input));
							newCar.addChild(new Node<String>(input, "Year"));

							do { // create cost node
								System.out.println("What is the price of " + newCar.getData() + "?");
								input = s.next();
								if (!isInteger(input)) {
									invalid();
								}
							} while (!isInteger(input));
							newCar.addChild(new Node<String>(input, "Price"));
						} else { // incorrect input
							invalid();
						}
					} else {
						System.out.println("No Categories, please create one first");
					}
				}
				break;
			case "D": // delete entry
				System.out.println(
						"Which entry would you like to delete? Keep in mind, deleting an entry will delete all the children of that node.");
				s.nextLine();
				input2 = s.nextLine();

				if (root.indexOfAll(input2).size() == 1) {// only one entry found that matcshes
					root.indexOf(input2).remove();
				} else if (root.indexOfAll(input2).size() > 1) {
					// if there are more than one entry found
					System.out.println("Do you want to delete it all? Y/N");
					input = s.next();

					switch (input.toUpperCase()) { // delete all?
					case "Y": // delete all nodes
						for (Node<String> n : root.indexOfAll(input2)) { // delete all nodes with that entry
							n.remove();
						}
						break;
					case "N": // dont delete all nodes
						// TODO: dont delete all nodes
						break;
					default: // incorrect input
						invalid();
						break;
					}
				} else { // nothing found to delete
					System.out.println("Nothing found to delete");
				}

				break;
			case "E": // exit
				System.out.println("Bye.");
				break;
			default: // incorrect input
				invalid();
				break;
			}

		}
		s.close();
	}

	static void CreateTreeSystem() { // creates the default tree system values
		root = new Node<String>("Lexus", "Company");

		// create the category of car
		root.addChild(new Node<String>("Sedan", "Type of Car"));
		root.addChild(new Node<String>("SUV", "Type of Car"));
		root.addChild(new Node<String>("Performance", "Type of Car"));
		root.addChild(new Node<String>("Used", "Type of Car"));

		// add cars to each category as well as their properties
		Node<String> sed = root.indexOf("Sedan");
		Node<String> suv = root.indexOf("SUV");
		Node<String> pfm = root.indexOf("Performance");
		Node<String> usd = root.indexOf("Used");

		// add sedans
		sed.addChild(new Node<String>("IS", "Model"));
		sed.indexOf("IS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("IS").addChild(new Node<String>("41250", "Price"));
		sed.addChild(new Node<String>("GS", "Model"));
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

		// add used
		usd.addChild(new Node<String>("LC", "Model"));
		usd.indexOf("LC").addChild(new Node<String>("2003", "Year"));
		usd.indexOf("LC").addChild(new Node<String>("4000", "Price"));
	}

	static void invalid() { // prints invalid input
		System.out.println("Invalid Input");
	}

	static boolean isInteger(String s) { // check if something is an integer
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
