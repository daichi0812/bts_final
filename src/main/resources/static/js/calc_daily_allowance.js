// 情報テクノロジー学科 15822108 堀田大智
document.addEventListener("DOMContentLoaded", () => {

    function calcDailyAllowance(model) {
        const params = {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8" },
            body: JSON.stringify(model)
        };

        fetch("/calc_daily_allowance", params)
            .then((response) => response.json())
            .then((model) => {
                // 結果を画面に表示
                document.getElementById("result_name").innerText = model.name;
                document.getElementById("result_affiliation").innerText = model.affiliation;
                document.getElementById("result_travel_hours").innerText = model.tripHours;
                document.getElementById("result_daily_allowance").innerText = model.dailyAllowance;
            })
            .catch((error) => {
                console.log(error);
            });
    }

    // 「日当計算」ボタン押下時
    document.getElementById("calc_button").addEventListener("click", () => {
        // フォームの入力値を取得
        const affiliation = document.getElementById("affiliation").value;
        const faculty = document.getElementById("faculty").value;
        const department = document.getElementById("department").value;
        const jobTitle = document.getElementById("job_title").value;
        const name = document.getElementById("name").value;
        const institutionTravel = document.getElementById("institution_travel").value;
        const travelName = document.getElementById("travel_name").value;
        const purpose = document.getElementById("purpose").value;
        const location = document.getElementById("location").value;
        const destination = document.getElementById("destination").value;

        // はじめの日, 終わりの日
        const startStr = document.getElementById("schedule-start").value; // "YYYY-MM-DDThh:mm"
        const endStr = document.getElementById("schedule-end").value;     // "YYYY-MM-DDThh:mm"

        // 文字列をDateに変換
        const startDate = new Date(startStr);
        const endDate = new Date(endStr);

        // 差分(ミリ秒) → 時間に変換(小数を四捨五入する例)
        const diffMs = endDate - startDate;
        const tripHours = Math.round(diffMs / (1000 * 60 * 60));

        // DailyAllowanceModelに詰める
        const model = new DailyAllowanceModel(
            affiliation,
            faculty,
            department,
            jobTitle,
            name,
            institutionTravel,
            travelName,
            purpose,
            location,
            destination,
            tripHours
        );

        // 日当計算 API 呼び出し
        calcDailyAllowance(model);
    });
});