package edu.gmu.cs321;

public class Applicant {
    private String applicantId;
    private String email;
    private Immigrant immigrantInfo;  //  shared object

    public Applicant(String applicantId, String email, Immigrant immigrantInfo) {
        this.applicantId = applicantId;
        this.email = email;
        this.immigrantInfo = immigrantInfo;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getEmail() {
        return email;
    }

    public Immigrant getImmigrantInfo() {
        return immigrantInfo;
    }

    public String getSsn() {
        return immigrantInfo.getSsn();
    }

    public Address getAddress() {
        return immigrantInfo.getAddress();
    }

    public String getFullName() {
        return immigrantInfo.getFullName();
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId='" + applicantId + '\'' +
                ", email='" + email + '\'' +
                ", immigrantInfo=" + immigrantInfo +
                '}';
    }
}
