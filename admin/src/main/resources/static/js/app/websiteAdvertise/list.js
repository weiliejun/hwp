layui.use(['layer', 'form', 'table', 'laypage'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        table = layui.table,
        laypage = layui.laypage;
    var cols = [[
        // {
        //     field: 'id',
        //     width: 100,
        //     title: 'ID',
        //     align: 'center',
        //     sort: true
        // },
        {
            field: 'topic',
            width: 150,
            title: '公告标题',
            align: 'center',
            sort: true
        }, {
            field: 'advertisePicture',
            width: 240,
            title: '图片地址',
            align: 'center',
            sort: true
            // ,
            // templet:function (d) {
            //     //隐藏图片地址
            //     $("[data-field='advertisePicture']").css('display','none');
            // }
        }, {
            field: 'channel',
            width: 120,
            title: '所属板块',
            align: 'center',
            templet: '#channel',
            sort: true
        }, {
            field: 'type',
            width: 120,
            title: '展现形式',
            align: 'center',
            templet: '#type',
            sort: true
        }, {
            field: 'targetUrl',
            width: 240,
            title: '链接',
            align: 'center',
            sort: true
        }, {
            field: 'status',
            width: 180,
            title: '状态',
            align: 'center',
            templet: '#status',
            sort: true
        }, {
            field: 'createTime',
            width: 240,
            title: '创建时间',
            align: 'center',
            templet: '#createTime',
            sort: true
        }, {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#websiteAdvertisebar',
            sort: true
        }
    ]];


    // 表格渲染
    var initTable = Common.initTable('#websiteAdvertiseTables', '/websiteAdvertise/list', cols, table);

    //监听工具条
    table.on('tool(websiteAdvertiseTables)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'update') {
            var index = layui.layer.open({
                title: "修改轮播图",
                type: 2,
                skin: '',
                // offset: ['85px', '330px'],
                area: ['800px', '800px'],
                content: PageContext.getUrl("/websiteAdvertise/toadd"),
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    if (data) {
                        body.find("#id").val(data.id);
                        body.find("#topic").val(data.topic);
                        body.find("#targetUrl").val(data.targetUrl);
                        body.find("#advertisePicture").val(data.advertisePicture);
                        body.find("#createTime").val(data.createTime);
                        // 所属板块回显选中
                        body.find("input[name=channel]").each(function (i, item) {
                            if ($(item).val() == data.channel) {
                                $(item).prop('selected', true);
                            }
                        });
                        // 展示形式赋值
                        body.find("input[name=type]").each(function (i, item) {
                            if ($(item).val() == data.type) {
                                $(item).prop('checked', true);
                            }
                        });
                        // 是否可用赋值
                        body.find("input[name=status]").each(function (i, item) {
                            if ($(item).val() == data.status) {
                                $(item).prop('checked', true);
                            }
                        });
                        form.render();
                    }
                }
            });
            form.render();
        } else if (obj.event === 'disable') {//禁用
            layer.confirm('确定不再显示该轮播图么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/websiteAdvertise/disable'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('websiteAdvertiseTables');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'enable') {//启用
            layer.confirm('确定显示该轮播图么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/websiteAdvertise/enable'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('websiteAdvertiseTables');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除该轮播图么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/websiteAdvertise/delete'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                //删除结果
                if (ajaxReturnData.flag == 'true') {
                    table.reload('websiteAdvertiseTables');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('删除失败', {icon: 5});
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
            Common.openFrame("/websiteAdvertise/toadd", "APP轮播图添加", '800px', '800px');
        }
    };

});