// 삭제 양식 제출 이벤트 처리(bookmark-list-delete.html)

document.getElementById("bookmarkDeleteForm")
    .addEventListener("submit", function(e) {

        e.preventDefault();

        var urlParams = new URLSearchParams(window.location.search);
        var id = urlParams.get('id');

        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/bookmark-delete?id=${id}`);
        xhr.send();

        xhr.onload = function() {
            if (xhr.status == 204) {
                alert("북마크 정보를 삭제하였습니다.");
                window.location.href = "/bookmark-list";
            } else {
                alert("북마크 정보 삭제에 실패하였습니다.");
            }
        };

    });
