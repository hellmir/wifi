// 삭제 버튼 이벤트 처리(bookmark-group-delete.html)

document.getElementById("bookmarkGroupDeleteForm")
    .addEventListener("submit", function(e) {

        e.preventDefault();

        var urlParams = new URLSearchParams(window.location.search);
        var id = urlParams.get('id');

        var name = document.getElementById("name").value;
        var sequence = document.getElementById("sequence").value;

        var data = {
            name: name,
            sequence: sequence
        };

        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/bookmark-group-delete?id=${id}`);
        xhr.send();

        xhr.onload = function() {
            if (xhr.status == 204) {
                alert("북마크 그룹 정보를 삭제하였습니다.");
                window.location.href = "/bookmark-group";
            } else {
                alert("북마크 그룹 정보 삭제에 실패하였습니다.");
            }
        };

    });
