$.namespace('$.login');
$.login.load = function () {
    $('#sub').click(function () {
        $.share.ajax({
            url : '/login',
            type : 'post',
            data : {
                userName : $('#userName').val(),
                password : $('#password').val()
            },
            msg : true,
            success : function (data) {
                if(data.code === 0){
                    location.href = data.data;
                }
            }
        });
    });
};