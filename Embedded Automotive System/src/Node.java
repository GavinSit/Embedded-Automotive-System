import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	private T data; // the data
	private String type; // the type of data (company, Type of Car, Model, Price, Year)
	private Node<T> parent;
	private List<Node<T>> children;

	public Node(T data, String type) {
		this.data = data;
		this.type = type;
		children = new ArrayList<Node<T>>(); // initialize children so children can be added later
	}

	public void addChild(Node<T> child) { // adds a child node of type String
		this.children.add(child);
		child.setParent(this); // adds the child but also sets this node as the parent
	}

	// when deleting something, you would have to delete everything under it
	public void removeChild(Node<T> child) { // removes specific child node as well as all the children under it
		child.getChildren().clear(); // removes all the children associated
		this.children.remove(child); // removes this node
	}

	public void remove() { // remove this Node
		this.getParent().removeChild(this);
	}

	public List<Node<T>> getChildren() { // returns children of this node
		return children;
	}

	public Node<T> getParent() { // returns the parent of this node
		return parent;
	}

	public void setParent(Node<T> parent) { // change category Note: This shouldn't need to be used in this context
		this.parent = parent;
	}

	public T getData() { // returns key value
		if (data != null) {
			return data;
		} else {
			return null;
		}
	}

	public void setData(T data) { // change key value
		this.data = data;
	}

	public String getType() { // return key
		return type;
	}

	public Node<T> indexOf(String s) { // finds index of a node with the string parameters using parameter or finds
										// using type (used for searching model specs)
		if (s.equalsIgnoreCase((String) this.data) || s.equalsIgnoreCase(this.getType())) { // if the current Node holds
																							// the info searching for,
																							// then just return this
			return this;
		}
		Node<T> grandChildren;
		// traverses the trees searching from the left nodes to right nodes recursively,
		// halts when found
		for (Node<T> n : children) {
			if (s.equalsIgnoreCase((String) n.getData()) || s.equalsIgnoreCase(n.getType())) { // if the children
																								// contain data needed
				return n;
			}
			grandChildren = n.indexOf(s);
			if (grandChildren != null) { // not null means found
				return grandChildren;
			}
		}
		return null; // null if not found
	}

	// TODO: cannot search itself, can only search children
	public List<Node<T>> indexOfAll(String s) { // same as indexOf() but finds all instances of occurance and returns it
												// in
		// arrayList
		List<Node<T>> found = new ArrayList<Node<T>>();

		for (Node<T> n : children) {
			if (s.equalsIgnoreCase((String) n.getData()) || s.equalsIgnoreCase(n.getType())) { // checks children
				found.add(n);
			}
			found.addAll(n.indexOfAll(s)); // recursively checks all the branches under the children
		}

		return found;
	}

	// Note:
	// if searching for price or year or corresponding data(), then return parent
	// if searching for type of car or Model, then return nodes with corresponding
	// key
	// if seaching for type of car data() or car model data(), then return children

	public List<Node<T>> Search(String s) { // will return search results based on the type of key (could be returning
											// itself, parents, or children)
		List<Node<T>> results = new ArrayList<Node<T>>();
		// check type it is searching
		if (this.indexOf(s) == null) { // check if null
			return null;
		} else if (this.indexOf(s).getType().equals("Year") || this.indexOf(s).getType().equals("Price")) { // return
																											// parent
			for (Node<T> n : this.indexOfAll(s)) {
				results.add(n.getParent());
			}
		} else if (s.equalsIgnoreCase("Type of Car") || s.equalsIgnoreCase("Model")) { // return all nodes of key 'Type
																						// of Car' or 'Model'
			for (Node<T> n : this.indexOfAll(s)) {
				results.add(n);
			}
		} else if (this.indexOf(s).getType().equals("Type of Car") || this.indexOf(s).getType().equals("Model")) { // return
																													// children
																													// of
																													// key
			for (Node<T> n : this.indexOfAll(s)) {
				results.addAll(n.getChildren());
			}
		}

		return results;
	}

	
	//shows the nodes as well as the parent nodes in a table
	public void display() { // displays all the nodes
		System.out.println(this + "\t" + this.getData() + "\t\t" + this.getType() + "\t\t" + this.getParent()); //print this node
		
		if (this.getChildren().size() > 0) { // if there are children nodes
			for (Node<T> n : this.getChildren()) { //goes through all the children nodes
				//calls recursively to print children
				n.display();
			}
		}
	}
} // end of Node<T> class