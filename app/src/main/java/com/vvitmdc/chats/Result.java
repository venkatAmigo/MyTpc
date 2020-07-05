
package com.vvitmdc.chats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("sno")
    @Expose
    private String sno;
    @SerializedName("companyt")
    @Expose
    private String companyt;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("drivetype")
    @Expose
    private String drivetype;
    @SerializedName("ce")
    @Expose
    private String ce;
    @SerializedName("eee")
    @Expose
    private String eee;
    @SerializedName("mech")
    @Expose
    private String mech;
    @SerializedName("cse")
    @Expose
    private String cse;
    @SerializedName("it")
    @Expose
    private String it;
    @SerializedName("mca")
    @Expose
    private String mca;
    @SerializedName("ece")
    @Expose
    private String ece;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("package in lakhs")
    @Expose
    private String packageInLakhs;
    @SerializedName("total lakhs")
    @Expose
    private String totalLakhs;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCompanyt() {
        return companyt;
    }

    public void setCompanyt(String companyt) {
        this.companyt = companyt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrivetype() {
        return drivetype;
    }

    public void setDrivetype(String drivetype) {
        this.drivetype = drivetype;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public String getEee() {
        return eee;
    }

    public void setEee(String eee) {
        this.eee = eee;
    }

    public String getMech() {
        return mech;
    }

    public void setMech(String mech) {
        this.mech = mech;
    }

    public String getCse() {
        return cse;
    }

    public void setCse(String cse) {
        this.cse = cse;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getMca() {
        return mca;
    }

    public void setMca(String mca) {
        this.mca = mca;
    }

    public String getEce() {
        return ece;
    }

    public void setEce(String ece) {
        this.ece = ece;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPackageInLakhs() {
        return packageInLakhs;
    }

    public void setPackageInLakhs(String packageInLakhs) {
        this.packageInLakhs = packageInLakhs;
    }

    public String getTotalLakhs() {
        return totalLakhs;
    }

    public void setTotalLakhs(String totalLakhs) {
        this.totalLakhs = totalLakhs;
    }

}
