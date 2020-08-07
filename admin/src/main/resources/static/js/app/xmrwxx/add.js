layui.use(['form', 'layer', 'laydate', 'jquery', 'laytpl', 'upload'], function () {
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        laytpl = layui.laytpl,
        upload = layui.upload,
        laydate = layui.laydate;

    // 初始化日期选择器
    laydate.render({
        elem: '#startDate', //指定元素
        range: false //开启日期范围，默认使用“_”分割
    });
    // 初始化日期选择器
    laydate.render({
        elem: '#endDate', //指定元素
        range: false //开启日期范围，默认使用“_”分割
    });

    $(function () {
        var rwxq = eval($("#rwxq").val());
        console.log(rwxq);
        var getTpl = document.getElementById('detailView').innerHTML
            , view = document.getElementById('view');
        laytpl(getTpl).render(rwxq, function (html) {
            view.innerHTML = html;
        });

        var i = 1;
        $('input[name="zrwxh"]').each(function () {
            $(this).val(i++);
        });

    });

    //上一步下一步隐藏显示
    $("#dataTwo").hide();
    $("#dataThree").hide();
    $("#jbInfo").addClass("layui-btn");
    $("#fuJ").addClass("layui-btn layui-btn-primary");
    $("#czjl").addClass("layui-btn layui-btn-primary");

    $("#jbInfo").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#jbInfo").addClass("layui-btn");
        $("#fuJ").addClass("layui-btn layui-btn-primary");
        $("#czjl").addClass("layui-btn layui-btn-primary");
        $("#dataTwo").hide();
        $("#dataOne").show();
        $("#dataThree").hide();
    });
    $("#fuJ").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#fuJ").addClass("layui-btn");
        $("#jbInfo").addClass("layui-btn layui-btn-primary");
        $("#czjl").addClass("layui-btn layui-btn-primary");
        $("#dataOne").hide();
        $("#dataTwo").show();
        $("#dataThree").hide();
    });

    /**
     * 遍历表格内容返回数组
     * @param  Int   id 表格id
     * @return Array
     */
    window.getTableContent = function (id) {
        var mytable = document.getElementById(id);
        var data = [];
        for (var i = 0, rows = mytable == null ? 0 : mytable.rows.length; i < rows; i++) {
            var row = {};
            row.zrwxh = mytable.rows[i].cells[0].childNodes[0].value;
            row.rwmc = mytable.rows[i].cells[1].childNodes[0].value;
            row.cron = mytable.rows[i].cells[2].childNodes[0].value;
            data.push(row);
        }

        return data;
    }

    // //监听下一步---录入列表编辑
    form.on('submit(addXmrwxx)', function (data) {
        $("#rwxq").val(JSON.stringify(getTableContent('tab')));
        data.field.rwxq = $("#rwxq").val();
        // alert(JSON.stringify(data.field));
        console.log(data);

        // alert(JSON.stringify(data.field));
        // alert($("#searchForm").serialize());
        $.ajax({
            url: PageContext.getUrl('/xmrwxx/addOrUpdate'),
            contentType: "application/json",
            type: 'post',
            async: false,
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.flag == "true") {
                    //添加，修改成功后为ID赋值
                    $("#id").val(data.id);
                    // $('label').css('display', 'none');
                    $("#jbInfo").removeClass();
                    $("#fuJ").removeClass();
                    $("#fuJ").addClass("layui-btn");
                    $("#jbInfo").addClass("layui-btn layui-btn-primary");
                    $("#dataOne").hide();
                    $("#dataTwo").show();
                    $("#dataThree").hide();
                    // window.location.href=PageContext.getUrl("/product/list");
                    // layer.msg("添加成功");
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })

    form.on('submit(save)', function (data) {
        console.log(JSON.stringify(data.field));
        // data.field.id = $("#id").val();
        // alert(JSON.stringify(data.field));
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/xmrwxx/rwfj'),
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
            // window.location.href = PageContext.getUrl("/xmxxgl/list");
        } else {
            top.layer.msg(ajaxReturnData.msg, {icon: 5});
        }
        return false;
    });

    //监听-提交审核
    form.on('submit(rwfh)', function (data) {
        $("#rwxq").val(JSON.stringify(getTableContent('tab')));
        data.field.rwxq = $("#rwxq").val();
        $.ajax({
            url: PageContext.getUrl('/xmrwxx/rwfh/' + $("#id").val()),
            contentType: "application/json",
            type: 'post',
            async: false,
            data: JSON.stringify(data.field),
            success: function (data) {
                if (data.flag == "true") {
                    window.location.href = PageContext.getUrl("/xmxxgl/get/xmgl/" + data.xmId + "?xmjd=" + data.xmjd);
                    layer.msg("提交成功");
                } else {
                    lay.msg(data.msg)
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })

    //多文件多次上传-layui不支持一次传输文件
    //定义一个字符串存取attachFile异步请求数据
    var map = {};
    var fileName = null;
    //多文件上传
    upload.render({
        elem: '#test2'
        , url: '/fuJian/upload/'
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
                var A=$("#attachFile").val();
                if(A==null||A==undefined||A==""){
                    $("#attachFile").val(JSON.stringify(map));
                }else{
                    $("#attachFile").val(JSON.stringify($.extend( map, eval("(" + $("#attachFile").val() + ")") )));
                }
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
                layer.msg("文件" + res.tmpFileName + "大小不能超过20M");
                $('#demo2').html(" ");
            } else {
                layer.msg("文件上传时发生错误,请稍后重试");
                $('#demo2').html(" ");
            }
        }
    });

    //点击事件
    $(document).on('click', '.pdfList i', function () {
        delete(map[$(this)[0].parentNode.text]);
        $("#attachFile").val(JSON.stringify(map));
        $(this).parent('a').remove();
    });

    window.setCronCallback = function (zrwxh, cron) {
        var mytable = document.getElementById('tab');
        for (var i = 0, rows = mytable == null ? 0 : mytable.rows.length; i < rows; i++) {
            var zrwxh0 = mytable.rows[i].cells[0].childNodes[0].value;
            if (zrwxh0 == zrwxh) {
                mytable.rows[i].cells[2].childNodes[0].value = cron;
                break;
            }
        }
    }
    window.selectFzrCallback = function (selectFzr) {
        $("#fzrId").val(selectFzr.id);
        $("#fzrName").val(selectFzr.name);
        $("#fzrXx").val(JSON.stringify(selectFzr));
    }
    window.selectZhrCallback = function (selectZhr) {
        var zhrId = '';
        var zhrName = '';
        for (var p in selectZhr) {
            zhrId += selectZhr[p].id + ',';
            zhrName += selectZhr[p].name + ',';
        }
        $("#zhrId").val(zhrId);
        $("#zhrName").val(zhrName);
        $("#zhrXx").val(JSON.stringify(selectZhr));
    }
    window.selectFhrCallback = function (selectFhr) {
        var fhrId = '';
        var fhrName = '';
        for (var p in selectFhr) {
            fhrId += selectFhr[p].id + ',';
            fhrName += selectFhr[p].name + ',';
        }
        $("#fhrId").val(fhrId);
        $("#fhrName").val(fhrName);
        $("#fhrXx").val(JSON.stringify(selectFhr));
    }
    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        selectFzr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectFzr&ryid=" + $("#fzrId").val(), "选择任务负责人", '1000px', '600px');
        },
        selectFhr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectFhr&ryid=" + $("#fhrId").val(), "选择任务复核人", '1000px', '600px');
        },
        selectZhr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectZhr&ryid=" + $("#zhrId").val(), "选择任务知会人", '1000px', '600px');
        },
        add: function () {
            window.location.href = PageContext.getUrl("/xmrwxx/toAdd" + "?id=" + $("#id").val() + "&xmId=" + $("#xmId").val() + "&xmjd=" + $("#xmjd").val());
        },
        goBack: function () {
            window.location.href = PageContext.getUrl("/xmxxgl/get/xmgl/" + $("#xmId").val() + "?xmjd=" + $("#xmjd").val());
        }
    };


    window.deleteData = function (obj) {
        layer.confirm('真的要删除该子任务吗？', function (index) {
            // $(obj).parent().parent().remove();//移除当前行 checkbox的父级是td，td的父级是tr，然后删除tr。就ok了。用each，选择多行遍历删除
            // $(obj).closest('tr').remove();
            $(obj).parents("tr").remove();
            layer.close(index);
        });
    };

    //定时提醒
    window.dstx = function (elem) {
        var mytable = document.getElementById('tab');
        //得到当前所在行
        var rows = $(elem).parents("tr").prevAll().length;
        var zrwxh = mytable.rows[rows].cells[0].childNodes[0].value;
        var cron = mytable.rows[rows].cells[2].childNodes[0].value;
        Common.openFrame("/xmrwxx/toCron" + "?zrwxh=" + zrwxh + "&cron=" + cron, "设置任务定时提醒", '1050px', '580px');
    }

    //新增子任务
    var trlisthtml = $(".layui-table:first>tbody>tr:last").html();//获取默认的一行tr，用作复制
    $("#addrow").click(function () {//增加一行

        $(".layui-table:first>tbody:last").append('<tr>' + trlisthtml + '</tr>');//向tbody最后添加一行tr.
        $(".layui-table:first>tbody>tr:last").find(":input[name=\"rwmc\"]").val(' ');   //将尾行元素克隆来的保存的值清空
        $(".layui-table:first>tbody>tr:last").find(":input[name=\"cron\"]").val(' ');

        var i = 1;
        $('input[name="zrwxh"]').each(function () {
            $(this).val(i++);
        });
    });
});