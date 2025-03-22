package edu.gmu.cs321;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class ApplicantTest {

    @Test
    public void testCreateApplicant_ValidInput_ShouldReturnId() {
        Applicant applicant = new Applicant();
        Address address = new Address("123 main St", "Fairfax", "22030");
        String result = applicant.createApplicant("John Doe", address);
        assertTrue(result.length() > 0); // Should fail for now
    }

    @Test
    public void testCreateApplicant_InvalidInput_ShouldReturnError() {
        Applicant applicant = new Applicant();
        String result = applicant.createApplicant("", null);
        assertEquals("ERROR", result); // Expecting "ERROR" string
    }

    @Test
    public void testUpdateApplicant_ValidInput_ShouldReturnTrue() {
        Applicant applicant = new Applicant();
        boolean result = applicant.updateApplicant("A12345", "Jane Doe");
        assertTrue(result);
    }

    @Test
    public void testUpdateApplicant_InvalidId_ShouldReturnFalse() {
        Applicant applicant = new Applicant();
        boolean result = applicant.updateApplicant("", "Jane Doe");
        assertFalse(result);
    }

    @Test
    public void testGetApplicant_ValidId_ShouldReturnObject() {
        Applicant applicant = new Applicant();
        applicant.createApplicant("John Doe", new Address("123 Main", "Fairfax", "22030"));
        Applicant found = applicant.getApplicantById("A12345");
        assertNotNull(found);
    }

    @Test
    public void testGetApplicant_InvalidId_ShouldReturnNull() {
        Applicant applicant = new Applicant();
        Applicant result = applicant.getApplicantById("InvalidId");
        assertNull(result);
    }
}
