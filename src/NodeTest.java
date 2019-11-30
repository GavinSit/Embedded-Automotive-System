import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	/*
	 * Tree will things in the following order Level 0: (root) company name, Level
	 * 1: types of cars, Level 2: Model, Level 3: (Car Properties) Cost, Year
	 */

	// test that everything is setup properly and that searching works
	@Test
	void test1() {
		// create root
		Node<String> root = new Node<String>("Lexus", "Company");
		root.setParent(null);// no parent because root
		assertEquals(null, root.getParent());
		assertEquals("Lexus", root.getData());

		// add a type of car node
		Node<String> carType1 = new Node<String>("Performance", "Type of Car");
		root.addChild(carType1);
		assertEquals(root, carType1.getParent()); // check that the parent has been set correctlys

		// add a car to that type as well as its properties
		carType1.addChild(new Node<String>("LC 500", "Model"));
		assertEquals(1, carType1.getChildren().size(), "size is incorrect");
		Node<String> car1 = carType1.indexOf("LC 500");
		car1.addChild(new Node<String>("103050", "Cost"));
		car1.addChild(new Node<String>("2020", "Year"));
		assertEquals("2020", car1.indexOf("2020").getData(), "wrong year"); // checks correct year for that model
		assertEquals(carType1, car1.getParent()); // checks it has correct parent
		assertEquals("2020", root.indexOf("Performance").indexOf("LC 500").indexOf("Year").getData());// checks that it
																										// can search
																										// for LC 500
																										// year

		// add another car
		carType1.addChild(new Node<String>("GS F", "Model"));
		carType1.indexOf("GS F").addChild(new Node<String>("99400", "Price"));
		carType1.indexOf("GS F").addChild(new Node<String>("2020", "Year"));
		assertEquals("99400", root.indexOf("Performance").indexOf("GS F").indexOf("Price").getData());
	}

	// test the search function from root. searching for a specific price and find
	// the car associated with that price
	@Test
	void test2() {
		Node<String> root = new Node<String>("Lexus", "Company");
		root.setParent(null);// no parent because root

		// add a type of car node (performance)
		Node<String> carType1 = new Node<String>("Performance", "Type of Car");
		root.addChild(carType1);

		// add a car to that type as well as its properties
		carType1.addChild(new Node<String>("LC 500", "Model"));
		assertEquals(1, carType1.getChildren().size(), "size is incorrect");
		Node<String> car1 = carType1.indexOf("LC 500");
		car1.addChild(new Node<String>("103050", "Cost"));
		car1.addChild(new Node<String>("2020", "Year"));

		// add another car in performance category
		carType1.addChild(new Node<String>("GS F", "Model"));
		carType1.indexOf("GS F").addChild(new Node<String>("99400", "Price"));
		carType1.indexOf("GS F").addChild(new Node<String>("2020", "Year"));

		// add SUV category
		root.addChild(new Node<String>("SUV", "Type Of Car"));
		root.indexOf("SUV").addChild(new Node<String>("RX", "Model"));
		root.indexOf("SUV").indexOf("RX").addChild(new Node<String>("56050", "Price"));
		root.indexOf("SUV").indexOf("RX").addChild(new Node<String>("2019", "Year"));
		assertEquals("56050", root.indexOf("56050").getData(), "price not found from root");
		assertEquals("Lexus", root.indexOf("Lexus").getData(), "(this) search not found");
		assertEquals("SUV", root.indexOf("SUV").getData());
		assertNull(root.indexOf("Performance").indexOf("SUV"), "should be null but isn't");
	}

	@Test
	void test3() {
		Node<String> root = new Node<String>("Lexus", "Company");

		// create the category of car
		root.addChild(new Node<String>("Sedan", "Type of Car"));
		root.addChild(new Node<String>("SUV", "Type of Car"));

		// add cars to each category as well as their properties
		Node<String> sed = root.indexOf("Sedan");
		Node<String> suv = root.indexOf("SUV");

		// add sedans
		sed.addChild(new Node<String>("IS", "Model"));
		sed.indexOf("IS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("IS").addChild(new Node<String>("41250", "Price"));
		sed.addChild(new Node<String>("GS", "Model"));
		sed.indexOf("GS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("GS").addChild(new Node<String>("64150", "Price"));

		// add SUVs
		suv.addChild(new Node<String>("NX", "Model"));
		suv.indexOf("NX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("NX").addChild(new Node<String>("44150", "Price"));
		suv.addChild(new Node<String>("RX", "Model"));
		suv.indexOf("RX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("RX").addChild(new Node<String>("56050", "Price"));
		assertEquals(4, root.indexOfAll("Year").size(), "size of arraylist wrong"); // check size is correct
		// assertEquals(1, root.indexOfAll("Lexus").size(), "size of arraylist wrong");

		// checks the correct nodes are there
		List<Node<String>> exp = new ArrayList<Node<String>>(); // will store expected results
		// add values to expected results
		exp.add(root.indexOf("IS").indexOf("Price"));
		exp.add(root.indexOf("GS").indexOf("Price"));
		exp.add(root.indexOf("NX").indexOf("Price"));
		exp.add(root.indexOf("RX").indexOf("Price"));
		assertFalse(exp == root.indexOfAll("peepee in my pants"), "doesn't fail as expected");
		assertEquals(exp, root.indexOfAll("Price"), "incorrect nodes in arraylist"); // check that the nodes obj are the
																						// same as expected

		// remove a node
		exp.remove(2);
		root.indexOf("NX").indexOf("Price").remove();
		assertEquals(exp, root.indexOfAll("Price")); // checks if removed itself correctly

		// remove all nodes of a type
		for (Node<String> n : root.indexOfAll("Year")) {
			n.remove();
		}
	}

	// test for fixing the bugs encountered in the code thus far
	@Test
	void test4() {
		Node<String> root = new Node<String>("Lexus", "Company");

		// create the category of car
		root.addChild(new Node<String>("Sedan", "Type of Car"));
		root.addChild(new Node<String>("SUV", "Type of Car"));

		// add cars to each category as well as their properties
		Node<String> sed = root.indexOf("Sedan");
		Node<String> suv = root.indexOf("SUV");

		// add sedans
		sed.addChild(new Node<String>("IS", "Model"));
		sed.indexOf("IS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("IS").addChild(new Node<String>("41250", "Price"));
		sed.addChild(new Node<String>("GS", "Model"));
		sed.indexOf("GS").addChild(new Node<String>("2020", "Year"));
		sed.indexOf("GS").addChild(new Node<String>("64150", "Price"));

		// add SUVs
		suv.addChild(new Node<String>("NX", "Model"));
		suv.indexOf("NX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("NX").addChild(new Node<String>("44150", "Price"));
		suv.addChild(new Node<String>("RX", "Model"));
		suv.indexOf("RX").addChild(new Node<String>("2020", "Year"));
		suv.indexOf("RX").addChild(new Node<String>("56050", "Price"));

		assertEquals(4, root.indexOfAll("Price").size(), "incorrect number of elements found");

		// check that search for multiple items display parent nodes correctly
		List<Node<String>> exp = new ArrayList<Node<String>>(); // expected values when searching for price
		exp.add(root.indexOf("41250"));
		exp.add(root.indexOf("64150"));
		exp.add(root.indexOf("44150"));
		exp.add(root.indexOf("56050"));
		assertEquals(exp, root.indexOfAll("Price"), "exp list incorrect");

	}
}
