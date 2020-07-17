layui.use(['form', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        layer = layui.layer;


    form.on('submit(add)', function (data) {
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/insuranceOrder/audit'),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data;
            }
        });
        //结果回应
        if (ajaxReturnData.flag == 'true') {
            top.layer.msg('审核成功', {icon: 1});
            // 保存成功后禁用掉保存按钮
            $('.cusBtn').addClass('layui-btn-disabled').attr('disabled', "true");
            //先得到当前iframe层的索引
            //var index = parent.layer.getFrameIndex(window.name);
            //parent.layer.close(index); //再执行关闭
            //刷新父页面
            //parent.location.reload();
        } else {
            top.layer.msg(ajaxReturnData.msg, {icon: 5});
        }
        return false;
    });

    $("#back").click(function () {
        window.history.go(-1);
    });


});