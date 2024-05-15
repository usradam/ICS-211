package h03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConditionalTest {

	Conditional thing1;
    Conditional thing2;

    final static String[] test = {
        "if", "(", "yes", ")", "{", "good", "}", "else", "{", "bad", "}" };
    
    @BeforeEach
    void setUp() throws Exception {
        thing1 = new Conditional(test);
        
    }
    
    @Test
    void test() {
		assertTrue(thing1.isCompound());
		assertEquals(thing1.numberOfParts(), 2);
   
    }
    
    @Test
    void testIsEqual() throws InvalidStatementException {
    	Conditional thing2 = new Conditional(test);
    	
    	assertTrue(thing1.equals(thing2));
    	
    	String[] tokens = {
                "if", "(", "no", ")", "{", "good", "}", "else", "{", "bad", "}" };
    	
        Conditional thing3 = new Conditional(tokens);
            
        assertFalse(thing1.equals(thing3));
    	
    }
    
    @Test
    void testToString() {
    	String expected = "if ( yes ) { good } else { bad }";
        assertEquals(expected, thing1.toString());
        
    }

}
