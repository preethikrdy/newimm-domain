package edu.gmu.cs321;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class DocumentTest {
    @Test
    public void testCreateDocument_ValidInput_ShouldReturnDocId() {
        Document document = new Document();
        String result = document.createDocument("uploads/id123.pdf", "PDF");
        assertTrue(result.length() > 0);
    }

    @Test
    public void testCreateDocument_InvalidInput_ShouldReturnError() {
        Document document = new Document();
        String result = document.createDocument("", "");
        assertEquals("ERROR", result);
    }

    @Test
    public void testUpdateDocument_ValidInput_ShouldReturnTrue() {
        Document document = new Document();
        boolean result = document.updateDocument("DOC123", "uploads/id456.pdf");
        assertTrue(result);
    }

    @Test
    public void testUpdateDocument_InvalidId_ShouldReturnFalse() {
        Document document = new Document();
        boolean result = document.updateDocument("", "uploads/id456.pdf");
        assertFalse(result);
    }

    @Test
    public void testGetDocument_ValidId_ShouldReturnDocument() {
        Document document = new Document();
        document.createDocument("uploads/id123.pdf", "PDF");
        Document found = document.getDocumentById("DOC123");
        assertNotNull(found);
    }

    @Test
    public void testGetDocument_InvalidId_ShouldReturnNull() {
        Document document = new Document();
        Document result = document.getDocumentById("InvalidID");
        assertNull(result);
    }
}
