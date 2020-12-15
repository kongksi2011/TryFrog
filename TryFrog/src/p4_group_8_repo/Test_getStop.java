package p4_group_8_repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Test_getStop {

	@Test
	public void test() {
		Animal test = new Animal();
		boolean output = test.getStop();
		assertEquals(false, output); 
	}

}
