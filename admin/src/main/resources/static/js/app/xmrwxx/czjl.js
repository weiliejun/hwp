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
            field: 'createTime',
            title: '变更时间',
            align: 'center',
            width: '12%',
            sort: true
        }, {
            field: 'creatorName',
            title: '变更人',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'bgnr',
            title: '变更内容',
            align: 'center',
            width: '8%',
            sort: true
        }, {
            field: 'bgq',
            title: '变更前',
            align: 'center',
            width: '36%',
            sort: true
        }, {
            field: 'bgh',
            title: '变更后',
            align: 'center',
            width: '36%',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#czjlTables', '/xmrwxx/czjlList', cols, table);

    $("#czjl").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#czjl").removeClass();
        $("#czjl").addClass("layui-btn");
        $("#jbInfo").addClass("layui-btn layui-btn-primary");
        $("#fuJ").addClass("layui-btn layui-btn-primary");
        $("#dataOne").hide();
        $("#dataTwo").hide();
        $("#dataThree").show();

        Common.searchTable('searchForm', initTable);
    });
});

