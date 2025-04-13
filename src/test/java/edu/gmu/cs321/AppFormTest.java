package edu.gmu.cs321;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppFormTest {

    private Immigrant sampleImmigrant() {
        Address address = new Address("123 Main St", "Fairfax", "VA", "22030");
        return new Immigrant("001", "Test User", "1990-01-01", "123-45-6789", "CountryX", "Pending", address);
    }

    @Test
    public void testCreateValidApplicationForm() {
        Immigrant immigrant = sampleImmigrant();
        List<Document> docs = new ArrayList<>();
        docs.add(new Document("001", "doc1.pdf", "PDF", "applicant001", "Uploaded"));
        ApplicationForm form = new ApplicationForm("app001", immigrant, docs, 100.0);

        assertNotNull(form);
        assertEquals("Test User", form.getImmigrant().getFullName());
        assertTrue(form.isComplete());
    }

    @Test
    public void testCreateInvalidApplicationForm() {
        ApplicationForm form = new ApplicationForm("app002", null, null, null);
        assertFalse(form.isComplete());
    }

    @Test
    public void testAddDocument() {
        Immigrant immigrant = sampleImmigrant();
        ApplicationForm form = new ApplicationForm("app003", immigrant, null, 50.0);
        form.addDocument(new Document("002", "doc2.pdf", "PDF", "applicant002", "Uploaded"));

        assertTrue(form.toString().contains("documents=1"));
    }

    @Test
    public void testImmigrantDataAccess() {
        Immigrant immigrant = sampleImmigrant();
        ApplicationForm form = new ApplicationForm("app004", immigrant, null, 120.0);

        assertEquals("Fairfax", form.getImmigrant().getAddress().getCity());
        assertEquals("Pending", form.getImmigrant().getImmigrationStatus());
    }
}
