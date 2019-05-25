$(function () {
    var loginEl = $("#login");
    loginEl.keyup(function () {
        var login = loginEl.val().toString();
        $.ajax({
            type: 'POST',
            contentType: 'text/plain',
            url: '/user/checkLogin',
            data: login
        }).done(function (data) {
            if (data) {
                loginEl.addClass("invalid");
            } else {
                loginEl.removeClass("invalid");
            }
        });
    });
});