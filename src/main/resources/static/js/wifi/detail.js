document.getElementById('menuButton').addEventListener('click', function() {
    var menuList = document.getElementById('menuList');
    if (menuList.classList.contains('hidden')) {
        menuList.classList.remove('hidden');
    } else {
        menuList.classList.add('hidden');
    }
});
