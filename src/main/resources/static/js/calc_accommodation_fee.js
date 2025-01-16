// 情報テクノロジー学科 15822108 堀田大智
document.addEventListener("DOMContentLoaded", () => {
    function calcAccommodationFee(model){
        const params = {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(model)
        };

        fetch("/calc_accommodation_fee", params)
            .then(response => response.json())
            .then(model => {
                console.log(model);
                document.getElementById("display_name").innerText = model.name;
                document.getElementById("display_affiliation").innerText = model.affiliation;
                document.getElementById("display_job_title").innerText = model.jobTitle;
                document.getElementById("display_travel_category").innerText = model.travelCategory;
                document.getElementById("display_number_of_nights").innerText = model.numberOfNights;
                document.getElementById("display_city_type").innerText = model.cityType;
                document.getElementById("display_accommodation_fee").innerText = model.accommodationFee;
            })
            .catch(error => {
                console.error(error);
            });
    }

    document.getElementById("calc_accommodation_fee_button").addEventListener("click", () => {
        const name = document.getElementById("name").value;
        const affiliation = document.getElementById("affiliation").value;
        const jobTitle = document.getElementById("job_title").value;
        const travelCategory = document.getElementById("travel_category").value;
        const numberOfNights = parseInt(document.getElementById("number_of_nights").value);
        const cityType = document.getElementById("city_type").value;

        const model = new AccommodationFeeModel(name, affiliation, jobTitle, travelCategory, numberOfNights, cityType);
        calcAccommodationFee(model);
    });
});