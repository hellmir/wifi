function deleteHistory(id) {
    if (confirm('정말 삭제하시겠습니까?')) {
        fetch('/history?id=' + id, {
            method: 'DELETE'
        }).then(function (response) {
            if (response.status === 204) {
                alert('히스토리 정보를 삭제하였습니다.');
                location.reload();
            } else {
                alert('삭제에 실패하였습니다. 다시 시도해주세요.');
            }
        });
    }
}

document.querySelectorAll('.delete-button').forEach(function(button) {
    button.addEventListener('click', function() {
        deleteHistory(this.dataset.id);
    });
});