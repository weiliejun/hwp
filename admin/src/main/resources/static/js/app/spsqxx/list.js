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
            width: '20%',
            sort: true
        }, {
            field: 'xmfzrName',
            title: '项目负责人',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'xmjd',
            title: '当前阶段',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'creatorName',
            title: '任务申请人',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'splx',
            title: '类型',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            field: 'createTime',
            title: '申请时间',
            align: 'center',
            width: '15%',
            sort: true
        }, {
            field: 'spyj',
            title: '审批状态',
            align: 'center',
            width: '10%',
            sort: true
        }, {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#spsqxxBar',
            width: '15%',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#spsqxxTables', '/spsqxx/list?cxmk='+$("#cxmk").val(), cols, table);

    //监听工具条
    table.on('tool(spsqxxTables)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'audit') {
            if (data.splx === '结束项目'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/jsxmsp/" + data.id + "/" + data.glId+"?cxmk="+$("#cxmk").val());
            }
            if (data.splx === '复核申请'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rwfhsp/" + data.id + "/" + data.glId+"?cxmk="+$("#cxmk").val());
            }
            if (data.splx === '人员变更申请'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rybgsp/" + data.id + "/" + data.glId+"?cxmk="+$("#cxmk").val());
            }
            if (data.splx === '复核申请'&&data.tzlx === '知会') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rwzh/" + data.id + "/" + data.glId+"?cxmk="+$("#cxmk").val());
            }
            if (data.splx === '人员变更申请'&&data.tzlx === '知会') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rybgzh/" + data.id + "/" + data.glId+"?cxmk="+$("#cxmk").val());
            }

            } else if (obj.event === 'view') {
            if (data.splx === '结束项目'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/jsxmView/" + data.id + "/" + data.glId);
            }else if (data.splx === '复核申请'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rwfhView/" + data.id + "/" + data.glId);
            }else if (data.splx === '人员变更申请'&&data.tzlx === '审批') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rybgView/" + data.id + "/" + data.glId);
            }else if (data.splx === '复核申请'&&data.tzlx === '知会') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rwzhView/" + data.id + "/" + data.glId);
            }else if (data.splx === '人员变更申请'&&data.tzlx === '知会') {
                window.location.href = PageContext.getUrl("/spsqxx/get/rybgzhView/" + data.id + "/" + data.glId);
            }
        } else if (obj.event === 'jsxm') {
            window.location.href = PageContext.getUrl("/spsqxx/get/jsxm/" + data.id);

        } else if (obj.event === 'bgjd') {
            // window.location.href = PageContext.getUrl("/spsqxx/get/bgjd/" + data.id);
            Common.openFrame("/spsqxx/get/bgjd/" + data.id, "变更阶段", '600px', '350px');
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
        }
    };

    //打开新窗口
    function addTab(_this) {
        tab.tabAdd(_this);
    }
});

