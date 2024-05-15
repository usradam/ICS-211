package h03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicStatementTest {

	BasicStatement basic;
	
	@Test
	void testIsCompound() throws Exception {
		String[] tokens = {"6", "=", "2", "+", "3", ";"};
		
		basic = new BasicStatement(tokens);
		
		assertFalse(basic.isCompound());
	}
	
	@Test
	void testIsEqual() throws Exception {
		String[] tokens = {"a", "=", "b", "+", "c", ";"};
		
		basic = new BasicStatement(tokens);
		
		assertTrue(basic.equals(new BasicStatement(tokens)));
		
	}
	
	@Test
	void testIsJavaIdentifier() {
		assertTrue(BasicStatement.isJavaIdentifier("test"));
	}
	
	@Test
	void testGetToken() {
		String[] tokens = {"hello", "sorry", "goodbye"};
		BasicStatement basic = new BasicStatement(tokens);
		
		assertEquals("hello", basic.getToken(0));
        assertEquals("sorry", basic.getToken(1));
        assertEquals("goodbye", basic.getToken(2));
		
	}
}
