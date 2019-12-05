import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImplementTest {
	

	@BeforeEach
	void setUp() throws Exception {

	}
	@Test
	void test1() {
		HashMap<String, String> Implement = new HashMap<>();
	  
		Implement.put("USA", "Washington DC");
	    Implement.put("Nepal", "Kathmandu");
	    Implement.put("India", "New Delhi");
	    Implement.put("Australia", "Sydney");
	    assertNotNull(Implement);
	  
	    
	    assertEquals("Sedan", Implement.get("IS"));
	    assertEquals("SUV", Implement.get("NX"));
	    assertEquals("Performance", Implement.get("LC"));
	   
	}
}
