var ORGANIZATION=function () {
    var global={
        url:{
            getOrganization:"/ums/api/organization/getOrganization"
        }
    }
    function initOrganization() {
        $.get(global.url.getOrganization,function (resp) {
            console.info(resp);
        })
    }
    return{
        initOrganization:initOrganization
    }
}();
$(document).ready(function () {
    ORGANIZATION.initOrganization();
});