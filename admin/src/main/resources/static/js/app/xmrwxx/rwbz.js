layui.use(['form', 'upload', 'layer', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        layer = layui.layer;

    form.on('submit(save)', function (data) {
        console.log(data.field);
        var fstz = $("input[name='fstz']");
        if(fstz.checked = true){
            data.field.fstz = '发送';
        }else{
            data.field.fstz = '不发送';
        }
        // alert(JSON.stringify(data.field));
        // data.field.fstz = (data.field.fstz && data.field.fstz == "on") ? '发送' : '不发送';
        layer.confirm('真的要"' + data.field.fstz + '"通知邮件吗？', function (index) {

            var ajaxReturnData;
            $.ajax({
                url: PageContext.getUrl('/rwbzjl/addOrUpdate'),
                contentType: "application/json",
                type: 'post',
                async: false,
                data: JSON.stringify(data.field),
                success: function (data) {
                    ajaxReturnData = data;
                }
            });
            //结果回应
            if (ajaxReturnData.flag == 'true') {
                top.layer.msg('保存成功', {icon: 1});
                // 保存成功后禁用掉保存按钮
                $('#saveButton').addClass('layui-btn-disabled').attr('disabled', "true");
                //先得到当前iframe层的索引
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index); //再执行关闭
                //刷新父页面
                parent.location.reload();
            } else {
                top.layer.msg(ajaxReturnData.msg, {icon: 5});
            }
            layer.close(index);
        });
        return false;
    });

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        cancel: function () {
            //点击取消关闭窗口
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    };

});