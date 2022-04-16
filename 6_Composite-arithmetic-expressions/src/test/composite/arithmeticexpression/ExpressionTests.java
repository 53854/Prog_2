package test.composite.arithmeticexpression;

import main.composite.arithmeticexpression.Addition;
import main.composite.arithmeticexpression.Constant;
import main.composite.arithmeticexpression.Expression;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpressionTests {

	private static final Logger LOGGER = Logger.getLogger(ExpressionTests.class.getName());

	@Test
	void testAddition(){
		Expression a = new Constant(5);
		Expression b = new Constant(5);
		Expression c = new Constant(8);
		Expression d = new Addition(b,c);
		Expression addiTest = new Addition(a,d);

		long expectedResult = 10;
		long actualResult = addiTest.evaluate();

		assertEquals(expectedResult,actualResult);
	}


	/*@Test
	void test() {
		fail("Nothing implemented yet");
	}*/
}
