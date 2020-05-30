layui.use(['form', 'upload', 'layer', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        layer = layui.layer;
   /* $(function () {
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

    });*/

    // 上传图片
    /* upload.render({
         elem: '#upLogo',
         url: PageContext.getUrl("/xmxxgl/uploadFile"),
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
     });*/

//多文件多次上传-layui不支持一次传输文件
    //定义一个字符串存取attachFile异步请求数据
    var map = {};
    var fileName = null;
    //多文件上传
    upload.render({
        elem: '#test2'
        , url: PageContext.getUrl("/xmxxgl/uploadFiles")
        , accept: 'file'
        , exts: 'jpg|jpeg|gif|png|doc|docx|pdf|xlsx|xls|pptx|ppt'
        , multiple: true
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                // fileName=file.name;
                // map[file.name]='';
            });
        }
        , done: function (res) {
            if (res.code == '0') {
                // var i=0;
                // for(var prop in map){
                //   if(map.hasOwnProperty(prop)){
                map[res.oldFileName] = eval("(" + res.attachFile + ")")[0].path;
                //     i++;
                //   }
                // }
                var ht = $('#demo2').html();
                $('#demo2').html(ht + '<a href="' + eval("(" + res.attachFile + ")")[0].path + '" target="_blank"><i class="layui-icon layui-icon-close"></i>' + res.oldFileName + '</a>');
                //将获取的文件放入map中
                $("#attachFile").val(JSON.stringify(map));
                layer.msg("文件" + res.tmpFileName + "上传成功");
                //上传完毕
            } else if (res.code == '1') {
                layer.msg("文件" + res.tmpFileName + "不能为空");
                $('#demo2').html(" ");
            } else if (res.code = 'false') {
                layer.msg(res.message);
                $('#demo2').html(" ");
            } else if (res.code = 'lose') {
                layer.msg("文件" + res.tmpFileName + "上传失败");
                $('#demo2').html(" ");
            } else if (res.code = 'big') {
                layer.msg("文件" + res.tmpFileName + "大小不能超过1M");
                $('#demo2').html(" ");
            } else {
                layer.msg("文件上传时发生错误,请稍后重试");
                $('#demo2').html(" ");
            }
        }
    });

    //多文件列表示例
    var demoListView = $('#demoList')
        , uploadListIns = upload.render({
        elem: '#testList'
        , url: 'https://httpbin.org/post' //改成您自己的上传接口
        , accept: 'file'
        , multiple: true
        , auto: false
        , bindAction: '#testListAction'
        , choose: function (obj) {
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function (index, file, result) {
                var tr = $(['<tr id="upload-' + index + '">'
                    , '<td>' + file.name + '</td>'
                    , '<td>' + (file.size / 1024).toFixed(1) + 'kb</td>'
                    , '<td>等待上传</td>'
                    , '<td>'
                    , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    , '</td>'
                    , '</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function () {
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function () {
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        , done: function (res, index, upload) {
            if (res.files.file) { //上传成功
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        , error: function (index, upload) {
            var tr = demoListView.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });

    //点击事件
    $(document).on('click', '.pdfList i', function () {
        delete(map[$(this)[0].parentNode.text]);
        $("#attachFile").val(JSON.stringify(map));
        $(this).parent('a').remove();
    });

    // 添加取消图片上传监听


    // 自定义图片上传验证规则
    form.verify({
        isUpload: function (value) {
            if (value == "" || value.length <= 0) {
                return "附件未上传";
            }
        }
    });


    form.on('submit(save)', function (data) {
        console.log(data.field);
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/xmxxgl/addOrUpdate2/jsxm'),
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

    window.selectJsxmSprCallback = function (selectJsxmSpr) { 
        // layer.alert("selectJsxmSpr");
        var jsxmSprId = '';
        var jsxmSprName = '';
        for (var p in selectJsxmSpr) {
            jsxmSprId += selectJsxmSpr[p].id + ',';
            jsxmSprName += selectJsxmSpr[p].name + ',';
        }
        $("#jsxmSprId").val(jsxmSprId);
        $("#jsxmSprName").val(jsxmSprName);
        $("#jsxmSpr").val(JSON.stringify(selectJsxmSpr));
    }

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        selectJsxmSpr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectJsxmSpr", "选择审批人", '1000px', '600px');
        }
    };

});