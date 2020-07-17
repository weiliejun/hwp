layui.use(['form', 'upload', 'layer', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        layer = layui.layer;

    //点击事件
    $('#saveButton').click(function () {
        if ($("#tzlx").val()=='审批') {
            $("#spyj").val("同意");
        }else{
            $("#spyj").val("已知晓");
        }
    });
    $('#saveButton2').click(function () {
        $("#spyj").val("拒绝");
    });

    form.on('submit(save2)', function (data) {
        console.log(JSON.stringify(data.field));
        // alert(JSON.stringify(data.field));
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/spsqxx/addOrUpdate'),
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
            // $('#saveButton').addClass('layui-btn-disabled').attr('disabled', "true");
            //先得到当前iframe层的索引
            //var index = parent.layer.getFrameIndex(window.name);
            //parent.layer.close(index); //再执行关闭
            //刷新父页面
            // parent.location.reload();
            window.location.href = PageContext.getUrl("/spsqxx/list"+"?cxmk="+$("#cxmk").val());
        } else {
            top.layer.msg(ajaxReturnData.msg, {icon: 5});
        }
        return false;
    });

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        selectJsxmSpr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectJsxmSpr&ryid=" + $("#jsxmSprId").val(), "选择审批人", '1000px', '600px');
        },
        cancel: function () {
            parent.location.reload();
        }
    };

});