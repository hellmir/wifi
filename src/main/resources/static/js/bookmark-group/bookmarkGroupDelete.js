// 삭제 양식 제출 이벤트 처리(bookmark-group-delete.html)

document.getElementById("bookmarkGroupDeleteForm")
    .addEventListener("submit", function (e) {

        e.preventDefault();

        var bookmarkCount = document.getElementById("bookmarkCount").value;

        if (bookmarkCount > 0) {
            alert("북마크 그룹에 북마크가 남아 있어 삭제할 수 없습니다. 북마크를 모두 삭제 후 다시 시도해 주세요.");
            return;
        }

        var urlParams = new URLSearchParams(window.location.search);
        var id = urlParams.get('id');

        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/bookmark-group-delete?id=${id}`);
        xhr.send();

        xhr.onload = function () {
            if (xhr.status == 204) {
                alert("북마크 그룹 정보를 삭제하였습니다.");
                window.location.href = "/bookmark-group";
            } else {
                alert("북마크 그룹 정보 삭제에 실패하였습니다.");
            }
        };

    });
