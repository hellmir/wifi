// 수정 버튼 이벤트 처리(bookmark-group-edit.html)

document.getElementById("bookmarkGroupEditForm")
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
        xhr.open("PATCH", `/bookmark-group-edit?id=${id}`);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(data));

        xhr.onload = function() {
            if (xhr.status == 200) {
                alert("북마크 그룹 정보를 수정하였습니다.");
                window.location.href = "/bookmark-group";
            } else {
                alert("북마크 그룹 정보 수정에 실패하였습니다.");
            }
        };

    });
