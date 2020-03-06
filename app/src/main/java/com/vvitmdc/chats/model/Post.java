package com.vvitmdc.chats.model;

public class Post {
    public String id;
    public String company;
    public String jobrole;
    public String interview_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(String interview_date) {
        this.interview_date = interview_date;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String qualification;

    public Post(String id, String company, String jobrole, String interview_date, String qualification, String packages, String venue) {
        this.id = id;
        this.company = company;
        this.jobrole = jobrole;
        this.interview_date = interview_date;
        this.qualification = qualification;
        this.packages = packages;
        this.venue = venue;
    }

    public String packages;
    public String venue;


}
