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
                // 計算後のモデルを画面に反映
                document.getElementById("result_name").innerText = model.name;
                document.getElementById("result_affiliation").innerText = model.affiliation;
                document.getElementById("result_travel_hours").innerText = model.tripHours;
                document.getElementById("result_daily_allowance").innerText = model.dailyAllowance;
            })
            .catch((error) => {
                console.log(error);
            });
    }

    document.getElementById("calc_button").addEventListener("click", () => {
        // フォームの入力値を取得
        const affiliation = document.getElementById("affiliation").value;
        const faculty = document.getElementById("faculty").value;
        const department = document.getElementById("department").value;
        const jobTitle = document.getElementById("job_title").value;
        const name = document.getElementById("name").value;

        const institutionTravel = document.getElementById("institution_travel").value;
        const travelJobTitle = document.getElementById("travel_job_title").value;
        const travelName = document.getElementById("travel_name").value;
        const purpose = document.getElementById("purpose").value;
        const location = document.getElementById("location").value;
        const destination = document.getElementById("destination").value;

        // はじめの日、終わりの日
        const startStr = document.getElementById("schedule-start").value;
        const endStr   = document.getElementById("schedule-end").value;

        const tripHoursInput = parseInt(document.getElementById("trip_hours").value) || 0;

        // Dateオブジェクトを作成
        const startDate = new Date(startStr);
        const endDate   = new Date(endStr);

        // 差分(ミリ秒)
        const diffMs = endDate - startDate;

        // 何泊何日か(差分を「日」で計算)
        const diffDays = Math.ceil(diffMs / (1000 * 60 * 60 * 24));
        // 泊数 = 日数 - 1 (最低0)
        const nights = Math.max(diffDays - 1, 0);

        // "YYYY年M月D日 ~ YYYY年M月D日 (X泊Y日)" 表示用
        function formatDateJP(date) {
            const y = date.getFullYear();
            const m = date.getMonth() + 1;
            const d = date.getDate();
            return `${y}年${m}月${d}日`;
        }
        const scheduleDisplay = `${formatDateJP(startDate)} ～ ${formatDateJP(endDate)}（${nights}泊${diffDays}日）`;

        // 時間差(時間単位)
        const autoTripHours = Math.round(diffMs / (1000 * 60 * 60));
        // 日帰りとしてユーザが入力した場合だけ上書き
        const finalTripHours = (autoTripHours > 0) ? autoTripHours : tripHoursInput;

        // モデル生成
        // ※ nights も送るようにする(サーバで宿泊費計算)
        const model = new DailyAllowanceModel(
            affiliation, faculty, department, jobTitle, name,
            institutionTravel, travelJobTitle, travelName,
            purpose, location, destination,
            scheduleDisplay,
            finalTripHours
        );
        model.nights = nights;  // ★追加: 泊数をセット

        // サーバへ送信 → 日当計算
        calcDailyAllowance(model);

        // BTSで作ったBusinessTripModelと同じフィールドを持つJSONをPOST
        // こんな感じ？
//        const btm = {
//            name: travelName,
//            affiliation: institutionTravel,
//            travelHours: finalTripHours,
//            dailyAllowance: 0; //初期値
//        }
    });
});