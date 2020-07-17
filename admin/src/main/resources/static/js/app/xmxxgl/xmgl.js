layui.use(['form', 'upload', 'layer', 'laytpl', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        upload = layui.upload,
        laytpl = layui.laytpl,
        layer = layui.layer;
    $(function () {
        $.ajax({
            url: PageContext.getUrl("/xmxxgl/xmgl/" + $("#id").val() + "/" + $("#xmjd").val()),
            type: 'post',
            async: false,
            success: function (data) {
                // alert(data)
                // console.log(data);
                if (data.flag === "true") {
                    var getTpl = document.getElementById('detailView').innerHTML
                        , view = document.getElementById('view');
                    laytpl(getTpl).render(data.data, function (html) {
                        view.innerHTML = html;
                    });

                }

            }
        });

        var i = 1;
        $('input[name="rwxh"]').each(function () {
            $(this).val(i++);
        });
    });

    //点击事件
    $('#xmmx').click(function () {
        Common.openFrame("/xmxxgl/get/view/" + $("#id").val(), "产品详细信息", '1000px', '600px');
    });

    window.deleteData = function (id, xmId) {
        layer.confirm('真的要删除该任务吗？', function (index) {
            window.location.href = PageContext.getUrl("/xmrwxx/delete/" + id + "/" + xmId + "?xmjd=" + $("#xmjd").val());
            layer.close(index);
        });
    }
    window.updateData = function (id, xmId) {
        window.location.href = PageContext.getUrl("/xmrwxx/toAdd" + "?id=" + id + "&xmId=" + xmId + "&xmjd=" + $("#xmjd").val());
    }

    window.rwbz = function (id, xmId) {
        Common.openFrame("/xmrwxx/get/rwbz/" + id, "任务备注", '1000px', '600px');
    }



    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        add: function () {
            window.location.href = PageContext.getUrl("/xmrwxx/toAdd" + "?id=" + "&xmId=" + $("#id").val() + "&xmjd=" + $("#xmjd").val());
        },
        goBack: function () {
            window.location.href = PageContext.getUrl("/xmxxgl/list");
        }
    };

    form.on('select(xmjd)', function (data) {
        window.location.href = PageContext.getUrl("/xmxxgl/get/xmgl/" + $("#id").val() + "?xmjd=" + data.value);
    });
});