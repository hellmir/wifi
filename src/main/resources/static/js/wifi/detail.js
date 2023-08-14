// 북마크 추가하기 버튼 이벤트 처리(detail.html)
function addWifiInfoToBookmark() {

    var bookmarkGroupName = document.querySelector('#listName').value;
    var managementNo = new URLSearchParams(window.location.search).get('mgrNo');

    if (!bookmarkGroupName) {
        alert("북마크 그룹 이름을 선택해주세요.");
        return;
    }

    var data = {
        bookmarkGroupName: bookmarkGroupName,
        managementNo: managementNo
    };

        fetch('/bookmark-add', {

        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)

    })
        .then(response => {

            if (response.status === 201) {
                alert("북마크 정보를 추가하였습니다.");
                window.location.href = '/bookmark-list';
            } else {
                alert("북마크 정보 추가에 실패하였습니다.");
            }

        })
        .catch(error => {
            console.error("오류:", error);
            alert("오류가 발생하였습니다.");
        });

}
