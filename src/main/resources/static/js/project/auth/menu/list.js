$.namespace('$.menu.list');
$.menu.list.load = function () {
    $('[data-toggle="tooltip]').tooltip();
};

function del(id) {
    $.share.ajax({
        url : '/menu/del',
        type : 'post',
        data : {id:id},
        msg : true,
        conf : true,
        success : function (data) {
            if(data.code === 0){
                location.href = data.data;
            }
        }
    });
}