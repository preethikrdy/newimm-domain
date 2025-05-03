package edu.gmu.cs321;
import java.util.ArrayList;
import java.util.List;

public class MockApplication {
    public static ApplicationForm createSampleApp(){
        Address address = new Address("1385 Mushroom Drive", 
        "Toad's Palace", "CA", "58147");
        Immigrant imm = new Immigrant("APP-004", "Yoshi Tezuka", 
        "1990-12-04", "444-92--7820", "Japan", "In Review", 
            address);
        List<Document> docs = new ArrayList<>();

        return new ApplicationForm(imm.getID(), imm, docs, 200.0);
    }
}
