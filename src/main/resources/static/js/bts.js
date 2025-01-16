// 情報テクノロジー学科　15822108 堀田大智
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("calc_button").addEventListener("click", () => {
        const name = document.getElementById("name").value;
        const affiliation = document.getElementById("affiliation").value;
        const departureValue = document.getElementById("departure_time").value;
        const arrivalValue = document.getElementById("arrival_time").value;

        const departure = new Date(departureValue);
        const arrival = new Date(arrivalValue);

        const msDiff = arrival.getTime() - departure.getTime();
        const hours = msDiff / (1000 * 60 * 60);

        // BusinessTripModel と同じフィールドを持つJSONをPOST
        const btm = {
            name: name,
            affiliation: affiliation,
            travelHours: hours,
            dailyAllowance: 0  // 初期値
        };

        fetch("/calc_daily_allowance2", {
            method: "POST",
            headers: { "Content-Type": "application/json; charset=utf-8" },
            body: JSON.stringify(btm)
        })
        .then(response => response.json())
        .then(data => {
            console.log("[Response]", data);
            // data.dailyAllowance にルール計算後の値が格納されている
            document.getElementById("result_name").innerText = data.name;
            document.getElementById("result_affiliation").innerText = data.affiliation;
            document.getElementById("result_travel_hours").innerText = data.travelHours;
            document.getElementById("result_daily_allowance").innerText = data.dailyAllowance;
        })
        .catch(err => console.error(err));
    });
});