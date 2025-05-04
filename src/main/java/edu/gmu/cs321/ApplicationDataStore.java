package edu.gmu.cs321;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDataStore {
    private static ApplicationDataStore instance;
    private List<ApplicationForm> applications;

    private ApplicationDataStore() {
        applications = new ArrayList<>();
    }

    public static synchronized ApplicationDataStore getInstance() {
        if (instance == null) {
            instance = new ApplicationDataStore();
        }
        return instance;
    }

    public void addApplication(ApplicationForm form) {
        applications.add(form);
    }

    public List<ApplicationForm> getApplications() {
        return new ArrayList<>(applications);
    }
}