package edu.gmu.cs321;

import static org.junit.Assert.*;
import org.junit.Test;

public class ApplicantTest {

    private Immigrant sampleImmigrant() {
        Address address = new Address("123 Main St", "Fairfax", "VA", "22030");
        return new Immigrant("John Doe", "1990-01-01", "111-22-3333", "USA", "Pending", address);
    }
    

    @Test
    public void testCreateApplicant_ValidData_ShouldInstantiate() {
        Immigrant immigrant = sampleImmigrant();
        Applicant applicant = new Applicant("A001", "john@example.com", immigrant);

        assertNotNull(applicant);
        assertEquals("John Doe", applicant.getFullName());
        assertEquals("Fairfax", applicant.getAddress().getCity());
        assertEquals("john@example.com", applicant.getEmail());
    }

    @Test
    public void testGetFullName_ShouldMatchImmigrantData() {
        Applicant applicant = new Applicant("A002", "jane@example.com", sampleImmigrant());
        assertEquals("John Doe", applicant.getFullName());
    }

    @Test
    public void testGetAddress_NotNullAndValidCity() {
        Applicant applicant = new Applicant("A003", "tester@example.com", sampleImmigrant());
        Address address = applicant.getAddress();
        assertNotNull(address);
        assertEquals("Fairfax", address.getCity());
    }

    @Test
    public void testGetSSN_ShouldMatchImmigrantData() {
        Applicant applicant = new Applicant("A004", "idcheck@example.com", sampleImmigrant());
        assertEquals("111-22-3333", applicant.getSsn());
    }

    @Test
    public void testToString_IncludesIdAndName() {
        Applicant applicant = new Applicant("A005", "test@example.com", sampleImmigrant());
        String text = applicant.toString();
        assertTrue(text.contains("A005"));
        assertTrue(text.contains("John Doe"));
    }
}
