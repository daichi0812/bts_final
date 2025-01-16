package jp.ac.aoyama.it.it_lab_3.bts_final;

public class DailyAllowanceModel {
    // 申請者情報
    private String affiliation;   // 所属
    private String faculty;       // 学部
    private String department;    // 学科
    private String jobTitle;      // 職名
    private String name;          // 氏名

    // 出張者情報
    private String institutionTravel; // 所属機関名・部局
    private String travelJobTitle;
    private String travelName;        // 出張者氏名
    private String purpose;           // 出張目的
    private String location;          // 用務地
    private String destination;       // 用務先
    private String schedule;

    // 画面で計算した出張時間(時間数)
    private int tripHours;

    // 日当
    private int dailyAllowance;

    // ★追加: 泊数(nights) と 宿泊費(lodgingCost)
    private int nights;
    private int lodgingCost;

    public DailyAllowanceModel() {
        this.affiliation = "";
        this.faculty = "";
        this.department = "";
        this.jobTitle = "";
        this.name = "";

        this.institutionTravel = "";
        this.travelName = "";
        this.purpose = "";
        this.location = "";
        this.destination = "";
        this.schedule = "";

        this.tripHours = 0;
        this.dailyAllowance = 0;
    }

    // --- Getter / Setter ---
    public String getAffiliation() { return affiliation; }
    public void setAffiliation(String affiliation) { this.affiliation = affiliation; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getInstitutionTravel() { return institutionTravel; }
    public void setInstitutionTravel(String institutionTravel) { this.institutionTravel = institutionTravel; }

    public String getTravelJobTitle() { return travelJobTitle; }
    public void setTravelJobTitle(String travelJobTitle) { this.travelJobTitle = travelJobTitle; }

    public String getTravelName() { return travelName; }
    public void setTravelName(String travelName) { this.travelName = travelName; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getTripHours() { return tripHours; }
    public void setTripHours(int tripHours) { this.tripHours = tripHours; }

    public int getDailyAllowance() { return dailyAllowance; }
    public void setDailyAllowance(int dailyAllowance) { this.dailyAllowance = dailyAllowance; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public int getLodgingCost() { return lodgingCost; }
    public void setLodgingCost(int lodgingCost) { this.lodgingCost = lodgingCost; }

}