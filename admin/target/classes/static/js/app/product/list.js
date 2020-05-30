layui.use(['layer', 'form', 'table', 'laydate', 'laypage'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        table = layui.table,
        laydate = layui.laydate,
        laypage = layui.laypage;
    var cols = [[
        /*{
            checkbox: true,
            width: 60,
            fixed: true
        },*/
        // {field:'id', width:80, title: 'ID',style:'display:none;', sort: true,templet:function (d) {
        //         //隐藏ID
        //         $("[data-field='id']").css('display','none');
        //     }
        // },
        {
            field: 'name',
            width: 120,
            title: '产品简称',
            align: 'center',
            sort: true,
            totalRowText: '合计:'
        }, {
            field: 'code',
            width: 150,
            title: '产品编号',
            align: 'center',
            sort: true
        }, {
            field: 'amount',
            width: 150,
            title: '发行规模(元)',
            align: 'center',
            sort: true,
            totalRow: true
        }, {
            field: 'timeLimit',
            width: 150,
            title: '产品期限(天)',
            align: 'center',
            sort: true
        }, {
            field: 'annualRate',
            width: 150,
            title: '预期年化收益率(%)',
            align: 'center',
            sort: true
        }, {
            field: 'repayType',
            width: 120,
            title: '还款方式',
            align: 'center',
            templet: '#repayType',
            sort: true
        }, {
            field: 'auditStatus',
            width: 120,
            title: '产品状态',
            align: 'center',
            templet: '#auditStatus',
            sort: true
        },
        {
            field: 'timeScope',
            width: 230,
            title: '产品募集期',
            align: 'center',
            templet: function (d) {
                return d.tenderStartTime + '至' + d.tenderEndTime
            },
            sort: true
        },
        {
            field: 'editTime',
            width: 220,
            title: '创建时间',
            align: 'center',
            // templet: '#createTime',
            sort: true
        }
        , {
            title: '常用操作',
            align: 'center',
            fixed: "right",
            toolbar: '#product-row-bar',
            sort: true
            // width: 180
        }
    ]];


    //日期时间选择范围
    laydate.render({
        elem: '#timeScope' //指定元素
        //,type: 'datetime'    //是否显示时间
        , trigger: 'click'
        //,lang: 'en'    //英文
        , range: true //开启日期范围，默认使用“_”分割
    });

    // 表格渲染
    var initTable = Common.initTable('#product1-table', '/product/all/list', cols, table);


    //监听工具条
    table.on('tool(product1-table)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'productEdit') {
            location.href = PageContext.getUrl("/product/addProductOne/" + data.id);
        } else if (obj.event === 'productCope') {
            layer.confirm('确认要将该产品复制么？',
                function (index) {
                    var ajaxReturnData;
                    $.ajax({
                        url: PageContext.getUrl('/product/productCope'),
                        type: 'post',
                        async: false,
                        data: {id: data.id},
                        success: function (data) {
                            ajaxReturnData = data;
                        }
                    });
                    //查询结果
                    if (ajaxReturnData.flag == 'true') {
                        table.reload('product1-table');
                        layer.msg(ajaxReturnData.msg, {icon: 1});
                    } else {
                        layer.msg('复制失败', {icon: 5});
                    }
                    layer.close(index);
                });
        } else if (obj.event === 'productExamSub') {
            layer.confirm('确认要将该产品提交审核么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/subExam'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                //删除结果
                if (ajaxReturnData.flag == 'true') {
                    table.reload('product1-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('审核提交失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'productDelete') {
            layer.confirm('真的作废该产品么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/delete'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                //删除结果
                if (ajaxReturnData.flag == 'true') {
                    table.reload('product1-table');
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
        }
    };
});