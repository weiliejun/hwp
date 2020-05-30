layui.use(['layer', 'laydate', 'form', 'table'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        table = layui.table,
        laydate = layui.laydate;

    // 初始化日期选择器
    laydate.render({
        elem: '#rangeTime1', //指定元素
        range: true //开启日期范围，默认使用“_”分割
    });

    var cols = [[
        {
            field: 'name',
            title: '姓名',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'phone',
            title: '手机号码',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'gsyx',
            title: '公司邮箱',
            align: 'center',
            width: '15%',
            sort: true
        }, {
            field: 'gryx',
            title: '个人邮箱',
            align: 'center',
            width: '15%',
            sort: true
        }, {
            field: 'dept',
            title: '部门',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'rank',
            title: '职级',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#ryxxglBar',
            width: '10%',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#ryxxglTables', '/ryxxgl/list', cols, table);

    //监听工具条
    table.on('tool(ryxxglTables)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'update') {
            window.location.href = PageContext.getUrl("/ryxxgl/get/update/" + data.id);

        } else if (obj.event === 'view') {
            window.location.href = PageContext.getUrl("/ryxxgl/get/view/" + data.id);
        } else if (obj.event === 'up') {//上架操作
            layer.confirm('真的要上架么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/ryxxgl/update/up'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    Common.searchTable('searchForm', initTable);
                    // table.reload('ryxxglTables');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'down') {//下架操作
            layer.confirm('真的要下架么？？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/ryxxgl/update/down'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    Common.searchTable('searchForm', initTable);
                    // table.reload('ryxxglTables');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        }
    });

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        search: function () {
            Common.searchTable('searchForm', initTable);
        },
        searchFormClear: function () {
            Common.searchTableClear('searchForm');
        },
        add: function () {
            //Common.openFrame("/app/ryxxgl/add", "新增人员", '1200px', '1000px');
            window.location.href = "/ryxxgl/toAdd";
        }
    };

    //打开新窗口
    function addTab(_this) {
        tab.tabAdd(_this);
    }
});

