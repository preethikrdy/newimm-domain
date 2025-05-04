package edu.gmu.cs321;

import static org.junit.Assert.*;
import org.junit.Test;

public class DocumentTest {

    @Test
    public void testCreateDocument_ValidInput_ShouldReturnNullForNow() {
        Document doc = Document.createDocument("doc1.pdf", "PDF", "applicant001");
        assertNull(doc); // hardcoded to fail for now
    }

    @Test
    public void testCreateDocument_InvalidInput_ShouldReturnNull() {
        Document doc = Document.createDocument("", "", "");
        assertNull(doc); // still hardcoded to fail
    }

    @Test
    public void testUpdateDocument_AlwaysFails() {
        Document doc = new Document("001", "doc.pdf", "PDF", "applicant001", "Uploaded");
        boolean result = doc.updateFileName("newdoc.pdf");
        assertFalse(result); // hardcoded to fail
    }

    @Test
    public void testGetDocumentById_ReturnsNull() {
        Document found = Document.getDocumentById("DOC123");
        assertNull(found); // static method is stubbed
    }

    @Test
    public void testDocumentFields_AccessorsReturnCorrectValues() {
        Document doc = new Document("002", "file2.pdf", "PDF", "applicant002", "Uploaded");
        assertEquals("file2.pdf", doc.getFileName());
        assertEquals("Uploaded", doc.getUploadStatus());
        assertEquals("applicant002", doc.getAssociatedApplicantId());
    }
}

