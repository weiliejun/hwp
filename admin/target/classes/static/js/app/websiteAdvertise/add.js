layui.use(['form', 'layer', 'jquery', 'laydate', 'upload'], function () {
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;

    // laydate.render({
    //     elem: '#createTime',
    //     trigger: 'click',
    //     range: true //开启日期范围，默认使用“_”分割
    // });

    // if ($("#createTime").val()!=null && $("#createTime").val().length>0){
    //     alert("666");
    //     $("#createTime").val(layui.laytpl.toDateString($("#createTime").val()));
    // }

    //同时绑定多个
    lay('.test-item').each(function () {
        laydate.render({
            elem: this
            , format: 'yyyy-MM-dd HH:mm:ss'
            , type: 'datetime'
            , trigger: 'click'
        });
    });


    if ($("#id").val() != null) {
        $("#demo1").attr('src', $('#advertisePicture').val()); //图片链接（base64）
    }

    form.on('submit(add)', function (data) {
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/websiteAdvertise/addorupdate'),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data;
            }
        });
        //结果回应
        if (ajaxReturnData.flag == 'true') {
            top.layer.msg('保存成功', {icon: 1});
            //先得到当前iframe层的索引
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index); //再执行关闭
            //刷新父页面
            parent.location.reload();
        } else {
            top.layer.msg(ajaxReturnData.msg, {icon: 5});
        }
        return false;
    });


    //普通图片上传
    var uploadInst = upload.render({
        elem: '#advertiseImg'
        , url: '/upload/logoImg'
        , field: 'file'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                layer.msg('上传中')
            });
        }
        , done: function (res) {
            if (res.code == '0') {
                //上传成功
                layer.msg('上传成功');
                $('#demo1').attr('src', res.path); //图片链接（base64）
                $('#advertisePicture').val(res.path);
            } else {
                //如果上传失败
                return layer.msg('上传失败');
            }
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    // 日期格式处理
    // layui.laytpl.toDateString = function (d, format) {
    //     var date = new Date(d || new Date())
    //         , ymd = [
    //         this.digit(date.getFullYear(), 4)
    //         , this.digit(date.getMonth() + 1)
    //         , this.digit(date.getDate())
    //     ]
    //         , hms = [
    //         this.digit(date.getHours())
    //         , this.digit(date.getMinutes())
    //         , this.digit(date.getSeconds())
    //     ];
    //
    //     format = format || 'yyyy-MM-dd HH:mm:ss';
    //
    //     return format.replace(/yyyy/g, ymd[0])
    //         .replace(/MM/g, ymd[1])
    //         .replace(/dd/g, ymd[2])
    //         .replace(/HH/g, hms[0])
    //         .replace(/mm/g, hms[1])
    //         .replace(/ss/g, hms[2]);
    // };
});