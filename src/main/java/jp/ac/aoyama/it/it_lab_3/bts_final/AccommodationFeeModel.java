// 情報テクノロジー学科 15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;

public class AccommodationFeeModel {
    private String name;
    private String affiliation;
    private String jobTitle;
    private String travelCategory;
    private int numberOfNights;
    private String cityType;
    private int accommodationFee;

    public AccommodationFeeModel(){
        this.name = "";
        this.affiliation = "";
        this.jobTitle = "";
        this.travelCategory = "";
        this.numberOfNights = 0;
        this.cityType = "";
        this.accommodationFee = 0;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getTravelCategory() {
        return travelCategory;
    }

    public void setTravelCategory(String travelCategory) {
        this.travelCategory = travelCategory;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    public int getAccommodationFee() {
        return accommodationFee;
    }

    public void setAccommodationFee(int accommodationFee) {
        this.accommodationFee = accommodationFee;
    }
}
