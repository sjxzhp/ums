const USER_MANAGEMENT = function ($) {
    //内部常量定义
    const global = {
        elements :{
            name: $('#user-name'),
        },
        urls: {
        }
    }

    // 初始化
    function init(){
        // console.info(user)
        // console.info(auths)
        // global.elements.name = user.username;

    }

    return {
        init: init,
    };
}(jQuery);
$(document).ready(function () {
    USER_MANAGEMENT.init();
});

