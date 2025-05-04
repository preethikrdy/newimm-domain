package edu.gmu.cs321;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthServiceTest {

    private AuthenticationService authService;
    private Address address; // Declare as a field to fix Error 3

    @Before
    public void setUp() {
        authService = new AuthenticationService();
        address = new Address("123 Main St", "Fairfax", "VA", "22030");
    }

    @Test
    public void testAuthenticationService() {
        // Fix Error 1: Remove incorrect call to updateAuthService
        // Instead, test the authenticate method on authService
        // Fix Error 2: Pass Strings instead of a boolean
        boolean result = authService.authenticate("user", "pass");
        assertTrue("Authentication should succeed with valid credentials", result);
        assertTrue("Authenticated flag should be true", authService.isAuthenticated());
    }

    @Test
    public void testAuthenticationServiceFailure() {
        boolean result = authService.authenticate("", ""); // Invalid credentials
        assertFalse("Authentication should fail with invalid credentials", result);
        assertFalse("Authenticated flag should be false", authService.isAuthenticated());
    }

    @Test
    public void testAddress() {
        // Fix Error 3: address is now a field, so this works
        assertNotNull("Address should not be null", address);
        assertEquals("Street should match", "123 Main St", address.getStreet());
    }

    @Test
    public void testInstanceOf() {
        // Fix Error 4: Use instanceof operator correctly
        assertTrue("authService should be an instance of AuthenticationService", authService instanceof AuthenticationService);
    }
}