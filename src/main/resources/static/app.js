document.getElementById('getMyLocation').addEventListener('click', function () {

    if ("geolocation" in navigator) {

        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            document.getElementById('latitude').value = latitude;
            document.getElementById('longitude').value = longitude;

            $.post("/wifis/locations", {latitude: latitude, longitude: longitude}, function (response) {
                console.log("서버 응답: " + response);
            });
        }, function (error) {
            alert("위치 정보를 가져올 수 없습니다. 에러 코드: " + error.code);
        });

    } else {
        alert("사용 중인 브라우저에서 Geolocation을 지원하지 않습니다.");
    }

});

document.getElementById('getNearWifiInformation').addEventListener('click', function () {

    $.get("/wifis", function (response) {
        var wifiDataResponseDtoList = response;
        var tableBodyHtml = '';

        if (wifiDataResponseDtoList.length === 0) {
            tableBodyHtml = '<tr><td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td></tr>';
        } else {
            wifiDataResponseDtoList.forEach(function (wifi) {
                tableBodyHtml += '<tr><td>' + wifi.distance + '</td><td>' + wifi.managementNo + '</td><td>' + wifi.wardOffice + '</td><td>' + wifi.mainName + '</td><td>' + wifi.address1 + '</td><td>' + wifi.address2 + '</td><td>' + wifi.installationFloor + '</td><td>' + wifi.installationType + '</td><td>' + wifi.installationManufacturedBy + '</td><td>' + wifi.serviceSeparatedEntry + '</td><td>' + wifi.CMCWR + '</td><td>' + wifi.constructionYear + '</td><td>' + wifi.inoutDoor + '</td><td>' + wifi.REMARS3 + '</td><td>' + wifi.latitude + '</td><td>' + wifi.longitude + '</td><td>' + wifi.workedDateTime + '</td></tr>';
            });
        }

        document.getElementById('wifiTableBody').innerHTML = tableBodyHtml;
    });

});
