package edu.gmu.cs321;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {

    private Address address;

    @Before
    public void setUp() {
        address = new Address("123 Main St", "Fairfax", "VA", "22030");
    }

    @Test
    public void testUpdateAddress_ValidInput_ShouldReturnTrue() {
        // Test updating with valid input
        boolean result = address.updateAddress("456 Elm St", "Arlington", "VA", "22201");
        assertTrue("Update should return true for valid input", result);
        assertEquals("Street should be updated", "456 Elm St", address.getStreet());
        assertEquals("City should be updated", "Arlington", address.getCity());
        assertEquals("State should be updated", "VA", address.getState());
        assertEquals("Zip should be updated", "22201", address.getZip());
    }

    @Test
    public void testUpdateAddress_NullInput_ShouldReturnFalse() {
        // Test updating with null input
        boolean result = address.updateAddress(null, "Arlington", "VA", "22201");
        assertFalse("Update should return false for null input", result);
    }

    @Test
    public void testGetAddress_ValidId_ShouldReturnAddress() {
        // Test retrieving address with a valid ID
        Address retrievedAddress = Address.getAddress("valid-id");
        assertNotNull("Address should be retrieved for valid ID", retrievedAddress);
        assertEquals("Street should match", "789 Oak St", retrievedAddress.getStreet());
    }

    @Test
    public void testGetAddress_InvalidId_ShouldReturnNull() {
        // Test retrieving address with an invalid ID
        Address retrievedAddress = Address.getAddress("invalid-id");
        assertNull("Address should be null for invalid ID", retrievedAddress);
    }
}