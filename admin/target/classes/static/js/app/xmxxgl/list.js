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
            field: 'cpmc',
            title: '产品名称',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'cpgm',
            title: '产品规模',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'cpqx',
            title: '产品期限',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'yqsy',
            title: '预期收益',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'ywlx',
            title: '业务类型',
            align: 'center',
            width: '12%',
            sort: true
        }, {
            field: 'xmfzrName',
            title: '项目负责人',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'xmjd',
            title: '当前阶段',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#xmxxglBar',
            width: '28%',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#xmxxglTables', '/xmxxgl/list', cols, table);

    //监听工具条
    table.on('tool(xmxxglTables)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'update') {
            window.location.href = PageContext.getUrl("/xmxxgl/get/update/" + data.id);

        } else if (obj.event === 'view') {
            window.location.href = PageContext.getUrl("/xmxxgl/get/view/" + data.id);

        } else if (obj.event === 'xmgl') {
            window.location.href = PageContext.getUrl("/xmxxgl/get/xmgl/" + data.id + "?xmjd=" + data.xmjd);

        } else if (obj.event === 'jsxm') {
            layer.confirm('真的要结束项目吗？', function (index) {
                window.location.href = PageContext.getUrl("/xmxxgl/get/jsxm/" + data.id);
                layer.close(index);
            });

        } else if (obj.event === 'bgjd') {
            // window.location.href = PageContext.getUrl("/xmxxgl/get/bgjd/" + data.id);
            Common.openFrame("/xmxxgl/get/bgjd/" + data.id, "变更阶段", '600px', '350px');
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
            // Common.openFrame("/xmxxgl/toAdd", "新增人员", '1200px', '1000px');
            window.location.href = "/xmxxgl/toAdd";
        }
    };

    //打开新窗口
    function addTab(_this) {
        tab.tabAdd(_this);
    }
});

