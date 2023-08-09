document.getElementById('getLocation').addEventListener('click', function () {

    if ("geolocation" in navigator) {

        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            document.getElementById('latitude').value = latitude;
            document.getElementById('longitude').value = longitude;

            $.post("/save_location", {latitude: latitude, longitude: longitude}, function (response) {
                console.log("서버 응답: " + response);
            });
        }, function (error) {
            alert("위치 정보를 가져올 수 없습니다. 에러 코드: " + error.code);
        });

    } else {
        alert("사용 중인 브라우저에서 Geolocation을 지원하지 않습니다.");
    }

});

document.getElementById('loadWifi').addEventListener
('click', function (event) {

    event.preventDefault();

    $.post("/wifis", function (wifiCount) {
        document.getElementById('result').textContent = wifiCount + "개의 WIFI 정보를 정상적으로 저장하였습니다.";
    }).fail(function () {
        document.getElementById('result').textContent = "와이파이 정보를 불러오는 데 실패했습니다.";
    });

});