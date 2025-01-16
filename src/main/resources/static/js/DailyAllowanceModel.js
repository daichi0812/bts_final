// 情報テクノロジー学科 15822108 堀田大智
class DailyAllowanceModel {
    constructor(
        affiliation, faculty, department, jobTitle, name,
        institutionTravel, travelJobTitle, travelName, purpose, location, destination,
        schedule, tripHours
    ) {
        // 申請者情報
        this.affiliation = affiliation;
        this.faculty = faculty;
        this.department = department;
        this.jobTitle = jobTitle;
        this.name = name;

        // 出張者情報
        this.institutionTravel = institutionTravel;
        this.travelJobTitle = travelJobTitle;
        this.travelName = travelName;
        this.purpose = purpose;
        this.location = location;
        this.destination = destination;
        this.schedule = schedule;

        // 出張時間
        this.tripHours = tripHours;

        // 日当(サーバ側計算後に格納)
        this.dailyAllowance = 0;

        // 泊数(クライアントで算出しサーバへ送る)
        this.nights = 0;

        // 宿泊費(サーバ側計算後に格納)
        this.lodgingCost = 0;
    }
}