package edu.gmu.cs321;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppFormTest {

    private Immigrant sampleImmigrant() {
        Address address = new Address("123 Main St", "Fairfax", "VA", "22030");
        return new Immigrant("Test User", "1990-01-01", "123-45-6789", "CountryX", "Pending", address);
    }

    @Test
    public void testCreateValidApplicationForm() {
        Immigrant immigrant = new Immigrant("1", "John Doe", "1990-01-01", "123-45-6789", "USA", "Pending", new Address("123 Main St", "Fairfax", "VA", "22030"));
        List<Document> documents = new ArrayList<>();
        ApplicationForm form = new ApplicationForm("APP-001", immigrant, documents, null);

        assertEquals("APP-001", form.getApplicationId());
        assertEquals(immigrant, form.getImmigrant());
        assertEquals(0, form.getDocuments().size()); // Check size instead of object equality
        assertTrue(form.getDocuments().isEmpty()); // Alternative check
        assertNull(form.getProcessingFee());
        assertFalse(form.isComplete());
    }

    @Test
    public void testAddDocument() {
        Immigrant immigrant = new Immigrant("1", "John Doe", "1990-01-01", "123-45-6789", "USA", "Pending", new Address("123 Main St", "Fairfax", "VA", "22030"));
        ApplicationForm form = new ApplicationForm("APP-001", immigrant, new ArrayList<>(), 100.0);
        Document doc = new Document("DOC-001", "Passport.pdf", "PDF", "1", "Uploaded");

        form.addDocument(doc);
        List<Document> retrievedDocs = form.getDocuments();
        assertEquals(1, retrievedDocs.size()); // Check size
        assertEquals("Passport.pdf", retrievedDocs.get(0).getFileName()); // Check content
    }

}