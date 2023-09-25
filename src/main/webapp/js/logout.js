function logout() {
    console.log(logout);
    $.ajax({
        cache: false,
        url: '/cartrade/logout.do',
        type: 'POST'
    }).done(function () {
        location.reload();
    }).fail(function () {
        console.log("Error");
    });
}