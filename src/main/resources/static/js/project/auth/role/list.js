$.namespace('$.role.list');
$.role.list.load = function () {

    $.share.table({
        selector : '#table',
        url : '/role/data',
        columns : [{
            checkbox: true
        }, {
            field: 'roleName',
            title: '角色名称'
        }, {
            field: 'createTime',
            title: '创建时间',
            formatter: function (value) {
                return $.share.changeDateFormat(value);
            }
        }, {
            field: 'updateTime',
            title: '最后修改',
            formatter: function (value) {
                return $.share.changeDateFormat(value);
            }
        } ]
    });

    //新增操作
    $('#btn_add').click(function () {

    });
    //修改操作
    $('#btn_edit').click(function () {
        var result = $.share.toolbar({operate:'update',selector:'#table'});
        if (result.status) {
        }
    });
    //删除操作
    $('#btn_delete').click(function () {
        var result = $.share.toolbar({operate:'delete',selector:'#table'});
        if (result.status) {
        }
    });
    //赋予权限
    $('#modal-827294').click(function () {
        var result = $.share.toolbar({operate:'update',selector:'#table'});
        if (result.status) {
            var row = result.data;
            $('#modalLabel').text('设置**'+ row.roleName +'**菜单权限');
            const pid = 0;
            var roleId = row.roleId;
            $('#roleId').val(roleId);
            $.share.table({
                selector : '#menuTable',
                url : '/role/roleMenuList',
                toolbar : '',
                showRefresh: false,
                showToggle: false,
                showColumns: false,
                pagination: false,
                detailView: true,
                customParams : {
                    roleId: roleId,
                    pid: pid
                },
                columns : [{
                    field: 'permissionName',
                    title: '菜单名称'
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    formatter: function (value) {
                        return $.share.changeDateFormat(value);
                    }
                } ],
                onExpandRow: function (index, row, $detail) {
                    var parentId = row.permissionId;

                    $detail.html('<table class="childTableCls" id="childMenuTable_'+parentId+'"></table>');
                    $.share.table({
                        selector : '#childMenuTable_'+parentId,
                        url : '/role/roleMenuList',
                        toolbar : '',
                        showRefresh: false,
                        showToggle: false,
                        showColumns: false,
                        pagination: false,
                        customParams : {
                            roleId: roleId,
                            pid: parentId
                        },
                        checkboxHeader: false,
                        columns : [{
                            checkbox: true,
                            formatter: function (value, row) {
                                if (row.flag === '0') {
                                    return {
                                        checked : true//设置选中
                                    };
                                }
                                return value;
                            }
                        }, {
                            field: 'permissionName',
                            title: '子菜单名称'
                        }, {
                            field: 'createTime',
                            title: '创建时间',
                            formatter: function (value) {
                                return $.share.changeDateFormat(value);
                            }
                        } ]
                    });


                }
            });
            $("#modal-container-827294").modal('show');  //手动开启
        }
    });

    $('#btn_save').click(function () {
        var childTable = $('.childTableCls');
        var childArr = [];

        $.each(childTable, function () {
            var option = $(this).bootstrapTable('getOptions');
            $.each(option.data, function (i,ele) {
                ele[0] = ele[0] === undefined ? false : ele[0];
                childArr.push(ele);
            });
        });

        if (childArr.length>0) {
            $.share.ajax({
                url : '/role/modifyRoleMenu',
                type : 'post',
                data : {
                    roleId:$('#roleId').val(),
                    jsonData:JSON.stringify(childArr)
                },
                msg : true,
                success : function (data) {
                    if(data.code === 0){
                        $("#modal-container-827294").modal('hide');
                    }
                }
            });
        }

    });

};

