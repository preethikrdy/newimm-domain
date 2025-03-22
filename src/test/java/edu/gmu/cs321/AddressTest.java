package edu.gmu.cs321;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class AddressTest {
    @Test
    public void testCreateAddress_ValidInput_ShouldReturnAddressId() {
        Address address = new Address("123 Main St", "Fairfax", "22030");
        String result = address.createAddress();
        assertTrue(result.length() > 0);
    }

    @Test
    public void testCreateAddress_InvalidInput_ShouldReturnError() {
        Address address = new Address("", "", "");
        String result = address.createAddress();
        assertEquals("ERROR", result);
    }

    @Test
    public void testUpdateAddress_ValidInput_ShouldReturnTrue() {
        Address address = new Address("123 Main St", "Fairfax", "22030");
        boolean result = address.updateAddress("456 New St", "22031");
        assertTrue(result);
    }

    @Test
    public void testUpdateAddress_InvalidZip_ShouldReturnFalse() {
        Address address = new Address("123 Main St", "Fairfax", "22030");
        boolean result = address.updateAddress("456 New St", "");
        assertFalse(result);
    }

    @Test
    public void testGetAddress_ValidId_ShouldReturnAddress() {
        Address address = new Address("123 Main St", "Fairfax", "22030");
        address.createAddress();
        Address found = address.getAddress("ADDR123");
        assertNotNull(found);
    }

    @Test
    public void testGetAddress_InvalidId_ShouldReturnNull() {
        Address address = new Address("123 Main St", "Fairfax", "22030");
        Address result = address.getAddress("InvalidID");
        assertNull(result);
    }
}
