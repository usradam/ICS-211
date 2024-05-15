package h03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodCallTest {

	MethodCall mc1;
	
	@BeforeEach
	void setUp() throws Exception {
                String[] tokens = { "getMethodName", "(", "", ")", ";" };
		mc1 = new MethodCall(tokens);
	}

	@Test
	void test() {
		assertFalse(mc1.isCompound());
		assertEquals(mc1.getMethodName(), "getMethodName");
	}
	
	@Test
	void testToString() throws InvalidStatementException {
		String[] tokens = {"f", "(", "x", ")", ";"};
		MethodCall methodCall = new MethodCall(tokens);
		
		assertEquals("f ( x ) ;", methodCall.toString());
		
	}
	
	@Test
	void testIsEqual() throws InvalidStatementException {
		String[] tokens1 = {"f", "(", "x", ")", "=", "log", "(", "x", ")", ";"};
		String[] tokens2 = {"f", "(", "x", ")", "=", "log", "(", "x", ")", ";"};
		
		MethodCall methodCall1 = new MethodCall(tokens1);
        MethodCall methodCall2 = new MethodCall(tokens2);
        
        assertTrue(methodCall1.equals(methodCall2));
        
	}
	
}