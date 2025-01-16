// 情報テクノロジー学科 15822108 堀田大智
document.addEventListener("DOMContentLoaded", () => {

    function calcDailyAllowance(model) {
        const params = {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(model)
        };
        fetch("/calc_daily_allowance", params)
            .then((response) => {
                return response.json();
            })
            .then((model) => {
                console.log(model);
                // 取得したmodelの値を表示用のスパンに反映
                document.getElementById("display_name").innerText = model.name;
                document.getElementById("display_affiliation").innerText = model.affiliation;
                document.getElementById("display_travel_hours").innerText = model.travelHours;
                document.getElementById("display_daily_allowance").innerText = model.dailyAllowance;
            })
            .catch((error) => {
                console.log(error);
            });
    }

    document.getElementById("calc_daily_allowance_button").addEventListener("click", () => {
        const name = document.getElementById("name").value;
        const affiliation = document.getElementById("affiliation").value;
        const travelHours = parseInt(document.getElementById("travel_hours").value);
        //const jsonData = {"name": name, "travelHours": travelHours};
        //calcDailyAllowance(jsonData);
        const model = new DailyAllowanceModel(name, affiliation, travelHours);
        calcDailyAllowance(model);
    });
});