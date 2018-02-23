$.namespace('$.menu.index');
$.menu.index.load = function () {
    $('#sub').click(function () {
        $.share.ajax({
            url : '/menu/save',
            type : 'post',
            data : $('#form').serialize(),
            msg : true,
            success : function (data) {
                if(data.code === 0){
                    location.href = data.data;
                }
            }
        });
    });
};