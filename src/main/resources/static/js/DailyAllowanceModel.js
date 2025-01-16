// 情報テクノロジー学科 15822108 堀田大智
class DailyAllowanceModel {
    constructor(
        affiliation, faculty, department, jobTitle, name,
        institutionTravel, travelName, purpose, location, destination,
        tripHours
    ) {
        // 申請者情報
        this.affiliation = affiliation;
        this.faculty = faculty;
        this.department = department;
        this.jobTitle = jobTitle;
        this.name = name;

        // 出張者情報
        this.institutionTravel = institutionTravel;
        this.travelName = travelName;
        this.purpose = purpose;
        this.location = location;
        this.destination = destination;

        // はじめの日と終わりの日の差(時間)
        this.tripHours = tripHours;

        // 日当(サーバ側計算後に入る)
        this.dailyAllowance = 0;
    }
}