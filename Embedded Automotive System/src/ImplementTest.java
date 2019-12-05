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
	  
		Implement.put("Sedan", "IS");
	    Implement.put("SUV", "NX");
	    Implement.put("Performance", "LC");

	    
	    
	    assertNotNull(Implement);
	  
	    
	    assertEquals("Sedan", Implement.get("IS"));
	    assertEquals("SUV", Implement.get("NX"));
	    assertEquals("Performance", Implement.get("LC"));
	   
	}
}
