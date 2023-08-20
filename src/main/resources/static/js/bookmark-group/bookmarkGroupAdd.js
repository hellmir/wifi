// 추가 양식 제출 이벤트 처리(bookmark-group-add.html)

document.getElementById('bookmarkGroupAddForm')
    .addEventListener('submit', function (e) {

        e.preventDefault();

        var name = document.getElementById('name').value;
        var sequence = document.getElementById('sequence').value;

        if (!name || !sequence) {
            alert("북마크 이름과 순서를 모두 입력해야 합니다.");
            return;
        }

        fetch('/bookmark-group-add', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: name, sequence: sequence})

        })
            .then(response => {

                if (response.status === 201) {

                    alert("북마크 그룹 정보를 추가하였습니다.");
                    window.location.href = '/bookmark-group';

                } else if (response.status === 409) {
                    return response.text().then(message => {
                        alert(message);
                    }); // 북마크 그룹 이름이 중복된 경우 Http 상태 코드 409와 메시지를 반환

                } else {
                    alert("북마크 그룹 정보 추가에 실패하였습니다.");
                }

            })
            .catch(error => {
                console.error("오류:", error);
                alert("오류가 발생하였습니다.");
            });

    });
