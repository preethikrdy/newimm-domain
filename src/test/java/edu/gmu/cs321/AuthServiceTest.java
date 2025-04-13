package edu.gmu.cs321;

import static org.junit.Assert.*;
import org.junit.Test;

public class AuthServiceTest {

    // Tests for creating authservice
    @Test
    public void createAuthServiceTest() {
        AuthenticationService service = new AuthenticationService();
        boolean result = service.createAuthService();
        assertTrue(result);
    }

    @Test
    public void createInvalidAuthServiceTest(){
        AuthenticationService service = new AuthenticationService();
        Address address = new Address("","","","");
        boolean result = address.updateAuthService(true);
        assertTrue(result, "AuthService should have a status of false when not implemented.");
    }

    // Tests for updating authservices status
    @Test
    public void updateAuthServiceTest() {
        AuthenticationService service = new AuthenticationService();
        boolean result = service.updateAuthService(true);
        assertTrue(result);
    }

    @Test
    public void updateInvalidAuthServiceTest(){
        AuthenticationService service = new AuthenticationService();
        boolean result = address.updateAuthService(null);
        assertNull(result);
    }

    // Tests for getting the authservice by the employee id its authenticating/authenticated
    @Test
    public void getAuthServiceTest() {
        AuthenticationService service = new AuthenticationService();
        AuthenticationService result = service.getAuthenticationServiceByID("ExistingID");
        assertNotNull(result);
        assertThat(result, instanceOf(AuthenticationService.class));
    }

    @Test
    public void getInvalidAuthServiceTest(){
        AuthenticationService service = new AuthenticationService();
        AuthenticationService result = service.getAuthenticationServiceByID("NON-EXISTENT_ID");
        assertNull(result);
    }
}
