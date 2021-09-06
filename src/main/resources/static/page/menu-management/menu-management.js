const MENU_MANAGEMENT = function ($) {
    //内部常量定义
    const global = {
        searchMenuList: [],
        elements :{
            menuTable: $('#menu-table'),
        },
        urls: {
            findMenuList: '../api/menu/findMenuList'
        }
    };

    function initTable() {
        var oTable = new tableInit();
        oTable.Init();
    }
    // 获取随访列表
    var tableInit = function () {
        let height = $('#menu-table-container').height();
        if (height < 120) {
            height = 120;
        }
        const pageSize = ((height - 80) / 40).toFixed(0);
        //查询参数
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            global.elements.menuTable.bootstrapTable('destroy').bootstrapTable({
                url: global.urls.findMenuList, // 请求后台的URL（*）
                method: 'post', // 请求方式（*）
                height:height,
                cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: false, // 是否显示分页（*）
                sortable: false, // 是否启用排序
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                queryParams: oTableInit.queryParams,// 传递参数（*）
                sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1, // 初始化加载第一页，默认第一页
                pageSize: pageSize, // 每页的记录行数（*）
                pageList: [10, 25, 50, 100], // 可供选择的每页的行数（*）
                search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                uniqueId: "id", // 每一行的唯一标识，一般为主键列
                clickToSelect: true,
                singleSelect: true,
                columns: [
                    {
                        field : 'check',
                        checkbox : true
                    },
                    {
                        field: 'menuName',
                        title: '菜单名称'
                    },
                    {
                        field: 'menuUrl',
                        title: '菜单地址'
                    },
                    {
                        field: 'menuCode',
                        title: '菜单代码'
                    },
                    {
                        field: 'orderNumber',
                        title: '排序'
                    },
                    {
                        field: 'opt',
                        title: '操作',
                        width:'200px',
                        formatter: function (value, row) {
                            const btn = [];
                            btn.push(`<a class='pointer btn-edit' title='编辑' style='cursor:pointer;color: #0278FE;'>编辑</a>&nbsp;&nbsp;`);
                            return btn.join('')
                        },
                        events:{
                            'click a.btn-edit'(e, v, msg, i) {
                              console.info(msg)
                            },
                        }
                    }],
                    responseHandler: function (result) {
                        // global.searchMenuList = result;
                        var temp = {
                            total: result.length,   // 总的数量
                            rows: result  // 数据
                        };
                    return temp;
                }
            });
        };
        oTableInit.queryParams = function (params) {
            console.info($('#menu-name').val())
            var temp = {
                menuName: $('#menu-name').val(),
            };
            return temp;
        };
        return oTableInit;
    };
    
    function searchMenu() {
        $('.searchMenuDiv').hide();
        findSearchMenuList();
        $('#search-menu-list').empty();
        $('#search-menu-list').append(pushMenuLi());
        //绑定li点击事件
        $('.menu_ul > li').bind('click',function () {
            $('#menu-name').val(this.innerHTML);
            searchMenu();
            initTable();
        });
        $('.searchMenuDiv').show();
    }

    function pushMenuLi() {
        var list = [];
        list.push('<ul class="menu_ul">');
        for (let i = 0; i < global.searchMenuList.length; i++) {
            let name = global.searchMenuList[i].menuName;
            list.push('<li id="menu_li_'+i+'" role="option">'+name+'</li>');
        }
        list.push('</ul>');
        return list.join('');
    }


    function findSearchMenuList(){
        var data = {}
        data.menuName = $('#menu-name').val();
        $.ajax({
            url: global.urls.findMenuList,
            type: "post",
            data: data,
            dataType: 'json',
            async:false,
            success: function (res) {
                global.searchMenuList = res;
            }
        });
    }




    // 初始化
    function init(){
        findSearchMenuList();
        initTable();
        $("#menu-name").bind('input propertychange',function(){
            if ($('#menu-name').val() == ""){
                initTable();
            }
            searchMenu();
        });
        //点击div外面div隐藏
        $(document).bind("click",function(e){
            var target  = $(e.target);
            if(target.closest(".searchMenuDiv").length == 0 && target.closest("#menu-name").length == 0){
                $('.searchMenuDiv').hide();
            }
        })
    }

    return {
        init: init,
        searchMenu:searchMenu,
    };
}(jQuery);
$(document).ready(function () {
    MENU_MANAGEMENT.init();
});

