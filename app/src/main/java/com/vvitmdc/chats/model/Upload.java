package com.vvitmdc.chats.model;

public class Upload {
  public String company;
  public String packages;

    public Upload(String company, String packages, String number) {
        this.company = company;
        this.packages = packages;
        this.number = number;
    }

    public String number;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
