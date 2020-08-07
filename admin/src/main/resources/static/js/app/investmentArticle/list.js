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
            field: 'id',
            title: 'ID',
            align: 'center',
            sort: true
        }, {
            field: 'title',
            title: '标题',
            align: 'center',
            sort: true
        }, {
            field: 'source',
            title: '来源',
            align: 'center',
            sort: true
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            templet: '#status',
            sort: true
        }, {
            field: 'creatorName',
            title: '创建人',
            align: 'center',
            sort: true
        }, {
            field: 'createTime',
            title: '创建时间',
            align: 'center',
            sort: true
        }, {
            field: 'editTime',
            title: '更新时间',
            align: 'center',
            sort: true
        }, {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#investmentArticleBar',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#investmentArticleTables', '/investmentArticle/list', cols, table);

    //监听工具条
    table.on('tool(investmentArticleTables)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'update') {
            window.location.href = PageContext.getUrl("/investmentArticle/get/" + data.id);

            //element.tabChange("bodyTab", '');


            // /**var index = layui.layer.open({
            //     title: "修改公告",
            //     type: 2,
            //     skin: '',
            //     offset: ['85px', '330px'],
            //     area: ['800px', '600px'],
            //     content: PageContext.getUrl("/investmentArticle/toAdd"),
            //     success: function (layero, index) {
            //         var body = layui.layer.getChildFrame('body', index);
            //         if (data) {
            //             body.find("#id").val(data.id);
            //             body.find("#title").val(data.title);
            //             body.find("#summary").val(data.summary);
            //             body.find("#content").val(data.content);
            //             body.find("#source").val(data.source);
            //             //发布状态赋值
            //             body.find("input[name=status]").each(function (i, item) {
            //                 if ($(item).val() == data.status) {
            //                     $(item).prop('checked', true);
            //                 }
            //             });
            //             //是否置顶赋值
            //             // body.find("input[name=topMark]").each(function (i, item) {
            //             //     if ($(item).val() == data.topMark) {
            //             //         $(item).prop('checked', true);
            //             //     }
            //             // });
            //             form.render();
            //         }
            //     }
            // });**/
        } else if (obj.event === 'up') {//上架操作
            layer.confirm('真的要上架么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/investmentArticle/update/up'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    Common.searchTable('searchForm', initTable);
                    // table.reload('investmentArticleTables');
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
                    url: PageContext.getUrl('/investmentArticle/update/down'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    Common.searchTable('searchForm', initTable);
                    // table.reload('investmentArticleTables');
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
            //Common.openFrame("/app/investmentArticle/add", "新增投资观点", '1200px', '1000px');
            window.location.href = PageContext.getUrl("/investmentArticle/toAdd");
        }
    };

    //打开新窗口
    function addTab(_this) {
        tab.tabAdd(_this);
    }
});

