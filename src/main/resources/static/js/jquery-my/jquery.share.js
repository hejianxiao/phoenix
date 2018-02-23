$.namespace('$.share');

$.share.ajax = function (options) {
    var msg = options.msg === undefined ? false : options.msg;
    var conf = options.conf === undefined ? false : options.conf;
    options.url = options.url + (options.url.indexOf('?')>0?'&':'?') + ('rand'+new Date().getTime()+'=') + new Date().getTime();
    options.cache = false;

    options.dataType = options.dataType === undefined ? 'json' : options.dataType;

    var _success = options.success;
    options.success = function(data, status, xhr){
        if(xhr){
            if($.isFunction(_success) && data){
                if(msg){
                    $.confirm({
                        title: '温馨提示',
                        content: data.msg,
                        buttons: {
                            ok: {
                                text: '确认',
                                action: function () {
                                    _success.call(this, data, status, xhr);
                                }
                            }
                        }
                    });
                } else {
                    _success.call(this, data, status, xhr);
                }
            }
        }
    };

    if(conf){
        $.confirm({
            title: '温馨提示',
            content: '确定要执行此操作吗?',
            buttons: {
                ok: {
                    text: '确认',
                    btnClass: 'btn-primary',
                    action: function () {
                        $.ajax(options);
                    }
                },
                cancel: {
                    text: '取消'
                }
            }
        });
    }else{
        $.ajax(options);
    }
};

$.share.confirm = function (msg) {
    $.confirm({
        title: '温馨提示',
        content: msg,
        buttons: {
            ok: {
                text: '确认',
                btnClass: 'btn-primary'
            }
        }
    });
};

$.share.table = function (options) {
    //如果存在即销毁之前的table
    $(options.selector).bootstrapTable('destroy');
    var toolbar = options.toolbar === undefined ? '#toolbar' : options.toolbar;
    var showRefresh = options.showRefresh === undefined ? true : options.showRefresh;
    var showToggle = options.showToggle === undefined ? true : options.showToggle;
    var showColumns = options.showColumns === undefined ? true : options.showColumns;
    var pagination = options.pagination === undefined ? true : options.pagination;
    var detailView = options.detailView === undefined ? false : options.detailView;
    var onExpandRow = options.onExpandRow === undefined ? false : options.onExpandRow;

    var customParams = options.customParams === undefined ? {} : options.customParams;
    var selectItemName = options.selectItemName === undefined ? 'btSelectItem' : options.selectItemName;
    var checkboxHeader = options.checkboxHeader === undefined ? true : options.checkboxHeader;


    $(options.selector).bootstrapTable({
        url: options.url,         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: toolbar,                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: pagination,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: function queryParams(params) {   //设置查询参数
            customParams['page']=params.offset / params.limit + 1;
            customParams['size']= params.limit;
            return customParams;
        },//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 5,                       //每页的记录行数（*）
        pageList: [5,10,25,50],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: showColumns,                  //是否显示所有的列
        showRefresh: showRefresh,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: options.uid,                     //每一行的唯一标识，一般为主键列
        showToggle:showToggle,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: detailView,                   //是否显示父子表
        columns: options.columns,
        onExpandRow : onExpandRow,
        selectItemName : selectItemName,
        checkboxHeader: checkboxHeader,
        ajax : options.ajax
    });
};

$.share.toolbar = function (event) {
    var result = {};

    var rows = $(event.selector).bootstrapTable('getSelections');
    if (event.operate === 'update') {
        if (rows.length == 1) {
            result.status = true;
            result.data = rows[0];
        } else {
            $.share.confirm('请选择一行');
            result.status = false;
        }
    }
    if (event.operate === 'delete') {
        if (rows.length >= 1) {
            result.status = true;
            result.data = rows;
        } else {
            $.share.confirm('请至少选择一行');
            result.status = false;
        }
    }

    return result;

};



$.share.changeDateFormat = function(timestamp){
    var dateVal = timestamp + "";
    if (timestamp != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
    }
};