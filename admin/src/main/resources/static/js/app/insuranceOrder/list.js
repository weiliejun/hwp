layui.use(['layer', 'laydate', 'form', 'table', 'excel'], function () {
    var $ = layui.$,
        layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        excel = layui.excel;

    //日期时间选择范围
    laydate.render({
        elem: '#rangeTime1', //指定元素
        trigger: 'click',
        range: true //开启日期范围，默认使用“_”分割
    });


    var cols = [[
        {
            field: 'name',
            title: '联系人',
            align: 'center',
            sort: true
        }, {
            field: 'mobile',
            title: '手机号',
            align: 'center',
            sort: true
        }, {
            field: 'mobile',
            title: '手机号',
            align: 'center',
            sort: true
        }, {
            field: 'orderInsuranceType',
            title: '预约险种',
            align: 'center',
            templet: function (d) {
                if (d.orderInsuranceType != undefined) {
                    if (d.orderInsuranceType === '1') {
                        return "车险";
                    } else if (d.orderInsuranceType === '2') {
                        return "重大疾病保险";
                    }

                } else {
                    return "未知";
                }
            },
            sort: true
        }, {
            field: 'createTime',
            title: '预约时间',
            align: 'center'
        }, {
            field: 'status',
            title: '是否处理',
            align: 'center',
            templet: function (d) {
                if (d.status != undefined) {
                    if (d.status === '1') {
                        return "已处理";
                    } else if (d.status === '0') {
                        return "待处理";
                    }

                } else {
                    return "未知";
                }
            },
            sort: true
        }, {
            field: 'update',
            title: '操作',
            align: 'center',
            fixed: "right",
            toolbar: '#insuranceOrderBar',
            sort: true
        }
    ]];

    // 表格渲染
    var initTable = Common.initTable('#insuranceOrderTables', '/insuranceOrder/list', cols, table);

    //监听工具条
    table.on('tool(insuranceOrderTables)', function (obj) {
        var data = obj.data;
        //查看
        console.log(data);
        if (obj.event === 'detail') {
            // 跳转至详情页面
            window.location.href = PageContext.getUrl("/insuranceOrder/detail/" + data.id);
        } // else if (obj.event === 'disable') {//禁用
        //     layer.confirm('真的禁用公告么？', function (index) {
        //         var ajaxReturnData;
        //         $.ajax({
        //             url: PageContext.getUrl('/websiteBulletin/disable'),
        //             type: 'post',
        //             async: false,
        //             data: {id: data.id},
        //             success: function (data) {
        //                 ajaxReturnData = data;
        //             }
        //         });
        //         if (ajaxReturnData.flag == 'true') {
        //             table.reload('websiteBulletinTables');
        //             layer.msg(ajaxReturnData.msg, {icon: 1});
        //         } else {
        //             layer.msg('操作失败', {icon: 5});
        //         }
        //         layer.close(index);
        //     });
        // } else if (obj.event === 'enable') {//启用
        //     layer.confirm('真的将该公告置为可用么？', function (index) {
        //         var ajaxReturnData;
        //         $.ajax({
        //             url: PageContext.getUrl('/websiteBulletin/enable'),
        //             type: 'post',
        //             async: false,
        //             data: {id: data.id},
        //             success: function (data) {
        //                 ajaxReturnData = data;
        //             }
        //         });
        //         if (ajaxReturnData.flag == 'true') {
        //             table.reload('websiteBulletinTables');
        //             layer.msg(ajaxReturnData.msg, {icon: 1});
        //         } else {
        //             layer.msg('操作失败', {icon: 5});
        //         }
        //         layer.close(index);
        //     });
        // } else if (obj.event === 'delete') {
        //     layer.confirm('真的删除该公告么？', function (index) {
        //         var ajaxReturnData;
        //         $.ajax({
        //             url: PageContext.getUrl('/websiteBulletin/delete'),
        //             type: 'post',
        //             async: false,
        //             data: {id: data.id},
        //             success: function (data) {
        //                 ajaxReturnData = data;
        //             }
        //         });
        //         //删除结果
        //         if (ajaxReturnData.flag == 'true') {
        //             table.reload('websiteBulletinTables');
        //             layer.msg(ajaxReturnData.msg, {icon: 1});
        //         } else {
        //             layer.msg('删除失败', {icon: 5});
        //         }
        //         layer.close(index);
        //     });
        // }
    });

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        search: function () {

            var arr = [];
            $("input:checkbox[name='rtxnTypeCode']:checked").each(function (i) {
                arr = [];
                arr[i] = $(this).val();
            });
            if (arr.length > 0) {
                $("#typeCodes").val(arr.join(","));
            }

            Common.searchTable('searchForm', initTable);
        },
        searchFormClear: function () {
            Common.searchTableClear('searchForm');
        },
        add: function () {
            Common.openFrame("/websiteBulletin/toadd", "新增公告", '800px', '600px');
        },
        exportExcel: function () {
            Common.exportExcel("fundTransactionTables", "货基投资记录", excel);
        }
    };


});