import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Application form.
 */
class AppFormTest {

    // Tests for creating object.
    @Test
    @SuppressWarnings("deprecation")
    void CreateNewTest() { 
        // For valid object
        ApplcationForm sample = sample.createNewAppForm("158940981","Toad","Ette",null,"000-000-0000","immigrant",null,0.0001);
        assertEquals (sample.getFirstName(), "Toad");
        assertNotNull (sample);
        assertThat (sample, instanceOf (ApplcationForm.class));
    }

    @Test
    void CreateNewInvalidTest(){
        // Invalid first name 
        ApplcationForm sample = sample.createNewApp("","","Ette",null,"000-000-0000","",null,0.0001);
        assertNull(sample);
    }

    // Tests for updating object.
    @Test
    void UpdateFormTest() { 
        // For valid object
        ApplcationForm sample = sample.createNewAppForm("58998","King","Boo",null,"800-800-0000","immigrant king",null,10000.0);
        sample.updateFirstName("Queen");
        assertEquals ("Queen", sample.getFirstName());
    }

    @Test
    void UpdateFormInvalidTest(){
        // Invalid first name 
        ApplcationForm sample = sample.createNewAppForm("58998","King","Boo",null,"800-800-0000","immigrant king",null,10000.0);
        boolean result = updateFirstName("");
        assertFalse(result);
    }


    // Tests getting from object.
    @Test
    @SuppressWarnings("deprecation")
    void GetterTest() { 
        // For valid object
        ApplcationForm sample = sample.createNewAppForm("44444444","Petey","Piranha",null,"444-444-4444","",null,10000.0);
        assertEquals (sample.getFirstName(), "Petey");
        assertNotNull (sample);
        assertThat (sample, instanceOf (ApplcationForm.class));
    }

    @Test
    void GetterInvalidTest(){
        // Invalid first name 
        ApplcationForm sample = sample.createNewBO ("158940981",null,"Ette",null,"000-000-0000","immigrant",null,0.0001);
        assertEquals (sample.getFirstName(), null);
        assertNull (sample);
    }
}