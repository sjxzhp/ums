const MENU_MANAGEMENT = function ($) {
    //内部常量定义
    const global = {
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
                        }
                    }],
                responseHandler: function (result) {
                    console.info(result)
                    var temp = {
                        total: result.length,   // 总的数量
                        rows: result  // 数据
                    };
                    return temp;
                }
            });
        };
        oTableInit.queryParams = function (params) {
            var temp = {
                menuName: $('#menu-name').val(),
            };
            return temp;
        };
        return oTableInit;
    };

    // 初始化
    function init(){
        initTable();
    }

    return {
        init: init,
    };
}(jQuery);
$(document).ready(function () {
    MENU_MANAGEMENT.init();
});

