package edu.gmu.cs321;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue()
    {
        Immigrant testImm = new Immigrant();
        boolean test = testImm.true1();
        assertTrue(test);
        
}
}
