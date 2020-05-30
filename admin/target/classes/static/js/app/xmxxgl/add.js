layui.use(['form', 'upload', 'layer', 'laytpl', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        laytpl = layui.laytpl,
        layer = layui.layer;

    $(function () {
        var id = $("#id").val();
        console.log("id：" + id);
        if (id == "" || id == null || id == undefined) {
            $("#sprDiv").hide();
            $("#sprDiv").css("display", "none");//隐藏div
        } else {
            $("#sprDiv").show();
        }

        $("#btfw2").css('display', 'inline-block');
        $("#btfw1").css('display', 'none');
    });

    $('#ywlx').blur(function () {
        layer.alert("没有起作用");
        var that = this;
        if (that.value == "" || that.value.length <= 0 || that.value == '主动管理型契约型基金' || that.value == '主动管理型合伙型基金') {
            $("#btfw1").css('display', 'inline-block');
            $("#btfw2").css('display', 'none');
        } else {
            $("#btfw2").css('display', 'inline-block');
            $("#btfw1").css('display', 'none');
        }
    });

    form.on('select(ywlx)', function (data) {
        layer.alert("没有起作用");
        category = data.value;
        categoryName = data.elem[data.elem.selectedIndex].text;
        form.render('select');
    });

    form.on('submit(save)', function (data) {
        console.log(data.field);
        var ajaxReturnData;
        $.ajax({
            url: PageContext.getUrl('/xmxxgl/addOrUpdate'),
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
            console.log(PageContext.getUrl("/xmxxgl/list"));
            window.location.href = PageContext.getUrl("/xmxxgl/list");
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

    window.selectXmjbrCallback = function (selectXmjbr) {
        $("#xmjbrId").val(selectXmjbr.id);
        $("#xmjbrName").val(selectXmjbr.name);
        $("#xmjbrXx").val(JSON.stringify(selectXmjbr));
    }
    window.selectCwfzrCallback = function (selectCwfzr) {
        $("#cwfzrId").val(selectCwfzr.id);
        $("#cwfzrName").val(selectCwfzr.name);
        $("#cwfzrXx").val(JSON.stringify(selectCwfzr));
    }
    window.selectFwfzrCallback = function (selectFwfzr) {
        $("#fwfzrId").val(selectFwfzr.id);
        $("#fwfzrName").val(selectFwfzr.name);
        $("#fwfzrXx").val(JSON.stringify(selectFwfzr));
    }
    window.selectFkfzrCallback = function (selectFkfzr) {
        $("#fkfzrId").val(selectFkfzr.id);
        $("#fkfzrName").val(selectFkfzr.name);
        $("#fkfzrXx").val(JSON.stringify(selectFkfzr));
    }
    window.selectXmfzrCallback = function (selectXmfzr) {
        $("#xmfzrId").val(selectXmfzr.id);
        $("#xmfzrName").val(selectXmfzr.name);
        $("#xmfzrXx").val(JSON.stringify(selectXmfzr));
    }
    window.selectXmqtcyCallback = function (selectXmqtcy) {
        var xmqtcyId = '';
        var xmqtcyName = '';
        for (var p in selectXmqtcy) {
            xmqtcyId += selectXmqtcy[p].id + ',';
            xmqtcyName += selectXmqtcy[p].name + ',';
        }
        $("#xmqtcyId").val(xmqtcyId);
        $("#xmqtcyName").val(xmqtcyName);
        $("#xmqtcy").val(JSON.stringify(selectXmqtcy));
    }
    window.selectSprCallback = function (selectSpr) {
        var sprId = '';
        var sprName = '';
        for (var p in selectSpr) {
            sprId += selectSpr[p].id + ',';
            sprName += selectSpr[p].name + ',';
        }
        $("#sprId").val(sprId);
        $("#sprName").val(sprName);
        $("#spr").val(JSON.stringify(selectSpr));
    }
    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        selectXmfzr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectXmfzr", "选择项目负责人", '1000px', '600px');
        },
        selectXmjbr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectXmjbr", "选择项目经办人", '1000px', '600px');
        },
        selectFwfzr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectFwfzr", "选择法务负责人", '1000px', '600px');
        },
        selectFkfzr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectFkfzr", "选择风控负责人", '1000px', '600px');
        },
        selectCwfzr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectCwfzr", "选择财务负责人", '1000px', '600px');
        },
        selectXmqtcy: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectXmqtcy", "选择项目其他成员", '1000px', '600px');
        },
        selectSpr: function () {
            Common.openFrame("/ryxxgl/selectList?cxmk=selectSpr", "选择审批人", '1000px', '600px');
        }
    };
});