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
		if(data != null) {
		return data;
		}else {
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
			if (s.equalsIgnoreCase((String) n.getData()) || s.equalsIgnoreCase(n.getType())) {
				return n;
			}
			grandChildren = n.indexOf(s);
			if (grandChildren != null) { // not null means found
				return grandChildren;
			}
		}
		return null; // null if not found
	}
} // end of Node<T> class