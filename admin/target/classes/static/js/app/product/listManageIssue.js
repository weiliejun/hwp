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
            width: 150,
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
            width: 180,
            title: '发行规模(元)',
            align: 'center',
            sort: true,
            totalRow: true
        }, {
            field: 'timeLimit',
            width: 120,
            title: '产品期限(天)',
            align: 'center',
            templet: '#status',
            sort: true
        }, {
            field: 'annualRate',
            width: 200,
            title: '预期年化收益率(%)',
            align: 'center',
            sort: true
        }, {
            field: 'repayType',
            width: 150,
            title: '偿还方式',
            align: 'center',
            templet: '#repayType',
            sort: true
        },
        {
            field: 'timeScope',
            width: 150,
            title: '产品募集期',
            align: 'center',
            templet: function (d) {
                return d.tenderStartTime + '至' + d.tenderEndTime
            },
            sort: true
        },
        // {
        //     field: 'repayStartDate',
        //     width: 120,
        //     title: '计息开始日期',
        //     align: 'center',
        //     templet: function (d) {
        //         if (d.repayStartDate==null){
        //             return "---";
        //         } else {
        //             return d.repayStartDate;
        //         }
        //     }
        // },
        // {
        //     field: 'repayEndDate',
        //     width: 120,
        //     title: '计息结束日期',
        //     align: 'center',
        //     templet: function (d) {
        //         if (d.repayEndDate==null){
        //             return "---"
        //         } else {
        //             return d.repayEndDate
        //         }
        //     }
        // },
        {
            field: 'status',
            width: 120,
            title: '状态',
            align: 'center',
            templet: function (d) {
                if (d.status === 'init') {
                    if (d.publishStatus === 'notIssue') {
                        return "待上架"
                    } else if (d.publishStatus === 'issue') {
                        return "已上架"
                    } else if (d.publishStatus === 'stop') {
                        return "已下架"
                    } else {
                        return "---"
                    }
                } else if (d.status === 'tender') {
                    return "募集中"
                } else if (d.status === 'fail') {
                    return "募集失败"
                } else if (d.status === 'success') {
                    return "募集成功"
                } else if (d.status === 'repaying') {
                    return "还款中"
                } else if (d.status === 'repayed') {
                    return "已还清"
                } else if (d.status === 'prepayed') {
                    return "已提前还清"
                } else {
                    return "---"
                }
            },
            sort: true
        }
        , {
            title: '操作',
            align: 'center',
            fixed: "right",
            toolbar: '#productManage-row-bar',
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
    var initTable = Common.initTable('#productManage-table', '/product/managerIssue/list', cols, table);

    //日期时间选择范围
    laydate.render({
        elem: '#repayStartDate' //募集开始时间
    });
    laydate.render({
        elem: '#repayEndDate' //募集结束时间
    });

    //监听工具条
    table.on('tool(productManage-table)', function (obj) {
        var data = obj.data;
        //修改
        if (obj.event === 'productCope') {
            layer.confirm('确认要将该产品复制么？', function (index) {
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
                //删除结果
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('复制失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'productEditIssue') {
            location.href = PageContext.getUrl("/product/addProductTwo/" + data.id);
        } else if (obj.event === 'productTran') {
            // location.href=PageContext.getUrl("/productTransaction/list?productId="+data.id);
            location.href = PageContext.getUrl("/product/lookProduct/" + data.id);
        } else if (obj.event === 'disable') {//停用
            layer.confirm('真的将该产品变为下架么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/disable'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'enable') {//启用
            layer.confirm('真的将该产品变为上架么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/enable'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'loan') {//放款
            layer.confirm('真的将产品放款么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/loan'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'refund') {//流标
            layer.confirm('真的将产品流标么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/refund'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'moneyBack') {//还款
            layer.confirm('真的将产品还款么？', function (index) {
                var ajaxReturnData;
                $.ajax({
                    url: PageContext.getUrl('/product/update/moneyBack'),
                    type: 'post',
                    async: false,
                    data: {id: data.id},
                    success: function (data) {
                        ajaxReturnData = data;
                    }
                });
                if (ajaxReturnData.flag == 'true') {
                    table.reload('productManage-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('操作失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'delete') {
            layer.confirm('真的将该产品作废么？', function (index) {
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
                    table.reload('productExam-table');
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