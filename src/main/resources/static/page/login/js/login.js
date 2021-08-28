var Login = function () {
    var global = {
        element: {
            username: $('#username'),
            password: $('#password'),
            token: $('#token'),
            tokenText: $('#tokenText'),
            userForm: $('#userForm'),
        }
    };

    function setToken() {
        var token = '';
        var tokens = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
        for (var i = 0; i < 4; i++) {
            token += tokens[Math.floor(Math.random() * 62)];
        }
        global.element.token.html(token);
    }

    function checkToken() {
        var token = global.element.token.html().toLowerCase();
        var tokenText = global.element.tokenText.val().toLowerCase();
        if (tokenText==''){
            alert('请输入验证码');
            return false;
        }
        if (tokenText === token) {
            return true;
        }
        alert('验证码错误，请重新输入');
        global.element.tokenText.empty();
        setToken();
        return false;
    }

    function checkUser() {
        var user=false;
        var username = global.element.username.val();
        var password = global.element.password.val();
        if (username==''){
            alert('用户名不能为空');
            return false;
        }
        if (password==''){
            alert('密码不能为空');
            return false;
        }
        $.ajaxSettings.async = false;
        $.post('../user/checkUser', {'username': username, "password": password}, function (resp) {
            if (!resp) {
                alert('用户名或密码错误');
            }
            user=resp;
        })
        $.ajaxSettings.async = true;
        return user;
    }

    function login() {
        if (!checkUser()) {
            return;
        }
        if (!checkToken()) {
            return;
        }
        global.element.userForm.submit();
    }

    function init() {
        setToken();
    }

    return {
        init: init,
        login: login,
        setToken: setToken,
    }
}();
$(document).ready(function () {
    Login.init();
})