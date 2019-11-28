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

	public void removeChild(Node<T> child) { // removes specific child node
		this.children.remove(child);
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public Node<T> indexOf(String s) { // finds index of a node with the string parameters using parameter or finds
										// using type (used for searching model specs)
		Node<T> grandChildren;

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