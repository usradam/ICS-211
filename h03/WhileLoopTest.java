package h03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WhileLoopTest {

	WhileLoop wl1;

    final static String[] test1 = {
         "while", "(", "x", "=", "5", ")", "{",
             "y", "=", "x", "+", "3", ";",
          "}" };
    
    @BeforeEach
	void setUp() throws Exception {
		wl1 = new WhileLoop(test1);
	}
    
	@Test
	void testWhileLoop() throws InvalidStatementException {
		String[] tokens = {"while", "(", "thing", ")", ";"};
		
		WhileLoop whileLoop = new WhileLoop(tokens);
		
		assertTrue(whileLoop.isCompound());
	
	}
	
	@Test
    void testIsCompound() throws InvalidStatementException {
        
        assertTrue(wl1.isCompound());

        
        String[] invalidTokens = {"while", "(", "thing", ")", ";"};
        WhileLoop invalidWhileLoop = new WhileLoop(invalidTokens);

       
        assertTrue(invalidWhileLoop.isCompound());
	}
	
	@Test
	void test() {
		assertTrue(wl1.isCompound());
		assertEquals(wl1.numberOfParts(), 1);
		
	}
}
