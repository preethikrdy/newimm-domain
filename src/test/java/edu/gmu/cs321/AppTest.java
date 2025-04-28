package edu.gmu.cs321;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        Immigrant testImm = new Immigrant(
            "", "", "", "", "",
            new Address("", "", "", "")
        );
        assertNotNull(testImm);
    }
}
