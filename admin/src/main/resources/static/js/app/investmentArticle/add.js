layui.use(['form', 'upload', 'layer', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        layer = layui.layer;
    $(function () {
        console.log("ready执行");
        var ue = UE.getEditor('editorContent');
        ue.autoFloatEnabled = false;
        ue.focus();

        ue.ready(function () {
            console.log("回显数据js执行");
            ue.setContent($("#content").val());


        });


        if ($("#logo").val().length > 0) {
            // 回显提示文字
            $("#isUpload").text("上传成功");
            // 回显图片
            $(".backImg img").attr("src", $("#logo").val());
        }

    });
    $('#saveButton').click(function () {
        $("#content").val(ue.getContent());
        console.log(ue.getContent());
    });

    // 上传图片
    upload.render({
        elem: '#upLogo',
        url: PageContext.getUrl("/investmentArticle/uploadFile"),
        accept: 'images', // 图片文件
        field: 'file',
        multiple: true,
        done: function (res) {
            if (res.code == "0") {
                // 提示文字
                $("#isUpload").text("上传成功");
                // 给input赋值
                $("#logo").val(res.logo);
                // 回显图片
                $(".backImg img").attr("src", res.logo);
            } else {
                $("#isUpload").text("上传失败，请重新上传");
            }

        }
    });


    // 添加取消图片上传监听


    // 自定义图片上传验证规则
    form.verify({
        isUpload: function (value) {
            if (value == "" || value.length <= 0) {
                return "投资观点Logo未上传";
            }
        }
    });


    form.on('submit(add)', function (data) {
        console.log(data.field);
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/investmentArticle/addOrUpdate'),
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
            //var index = parent.layer.getFrameIndex(window.name);
            //parent.layer.close(index); //再执行关闭
            //刷新父页面
            //parent.location.reload();
        } else {
            top.layer.msg(ajaxReturnData.msg, {icon: 5});
        }
        return false;
    });

});