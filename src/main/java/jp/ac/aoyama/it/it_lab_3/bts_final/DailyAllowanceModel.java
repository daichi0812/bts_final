// 情報テクノロジー学科 15822108 堀田大智
package jp.ac.aoyama.it.it_lab_3.bts_final;

public class DailyAllowanceModel {
    private String name;
    private String affiliation;
    private int travelHours;
    private int dailyAllowance;

    public DailyAllowanceModel() {
        this.name = "";
        this.affiliation = "";
        this.travelHours = 0;
        this.dailyAllowance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation(){return affiliation;}

    public void setAffiliation(String affiliation){this.affiliation = affiliation;}

    public int getTravelHours() {
        return travelHours;
    }

    public void setTravelHours(int travelHours) {
        this.travelHours = travelHours;
    }

    public int getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(int dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
    }
}
