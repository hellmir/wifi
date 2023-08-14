// 내 위치 가져오기 버튼 이벤트 처리(index.html)
document.getElementById('getMyLocation').addEventListener('click', function () {

    if ("geolocation" in navigator) {

        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            document.getElementById('latitude').value = latitude;
            document.getElementById('longitude').value = longitude;

        }, function (error) {
            alert("위치 정보를 가져올 수 없습니다. 에러 코드: " + error.code);
        });

    } else {
        alert("현재 이용 중인 브라우저에서 Geolocation을 지원하지 않습니다.");
    }

});

// 근처 WIFI 정보 보기 버튼 이벤트 처리(index.html)
document.getElementById('getNearWifiInformation').addEventListener('click', function () {

    var latitude = document.getElementById('latitude').value;
    var longitude = document.getElementById('longitude').value;

    var url = new URL("/wifi", window.location.origin);
    url.searchParams.append('latitude', latitude);
    url.searchParams.append('longitude', longitude);

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(wifiDataResponseDtoList => {

            var tableBodyHtml = '';

                wifiDataResponseDtoList.forEach(function(wifi) {
                    var distance = parseFloat(wifi.distance).toFixed(4);

                    tableBodyHtml += '<tr>' +
                        '<td>' + distance + '</td>' +
                        '<td>' + wifi.managementNo + '</td>' +
                        '<td>' + wifi.wardOffice + '</td>' +
                        '<td><a href="/detail?mgrNo=' + wifi.managementNo + '">' + wifi.mainName + '</a></td>' +
                        '<td>' + wifi.address1 + '</td>' +
                        '<td>' + wifi.address2 + '</td>' +
                        '<td>' + wifi.installationFloor + '</td>' +
                        '<td>' + wifi.installationType + '</td>' +
                        '<td>' + wifi.installationManufacturedBy + '</td>' +
                        '<td>' + wifi.serviceSeparatedEntry + '</td>' +
                        '<td>' + wifi.CMCWR + '</td>' +
                        '<td>' + wifi.constructionYear + '</td>' +
                        '<td>' + wifi.inoutDoor + '</td>' +
                        '<td>' + wifi.REMARS3 + '</td>' +
                        '<td>' + wifi.longitude + '</td>' +
                        '<td>' + wifi.latitude + '</td>' +
                        '<td>' + wifi.workedDateTime + '</td>' +
                        '</tr>';
                });

            document.getElementById('wifiTableBody').innerHTML = tableBodyHtml;

        })
        .catch(error => {
            console.error("오류:", error);
            var tableBodyHtml = '<tr><td colspan="17" style="text-align: center;">' +
                '와이파이 정보를 받아오지 못했습니다. Open API의 와이파이 정보를 가져온 후 다시 시도해 주세요.</td></tr>';
            document.getElementById('wifiTableBody').innerHTML = tableBodyHtml;
        });

});


