layui.use(['layer', 'form', 'table', 'laydate'], function () {
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
        //     },
        {
            field: 'name',
            width: 120,
            title: '产品简称',
            align: 'center',
            sort: true,
            totalRowText: '合计:'
        }, {
            field: 'code',
            width: 120,
            title: '产品编号',
            align: 'center',
            sort: true
        }, {
            field: 'amount',
            width: 120,
            title: '发行规模(元)',
            align: 'center',
            sort: true,
            totalRow: true
        }, {
            field: 'timeLimit',
            width: 100,
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
            width: 280,
            title: '产品募集期',
            align: 'center',
            templet: function (d) {
                return d.tenderStartTime + '至' + d.tenderEndTime
            },
            sort: true
        },
        {
            field: 'auditStatus',
            width: 120,
            title: '产品审核状态',
            align: 'center',
            templet: '#auditStatus',
            sort: true
        },
        {
            field: 'editTime',
            width: 150,
            title: '提交时间',
            align: 'center',
            sort: true
            // templet: '#createTime'
        }
        , {
            title: '操作',
            align: 'center',
            fixed: "right",
            toolbar: '#productExam-row-bar',
            sort: true
            // width: 100
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
    var initTable = Common.initTable('#productExam-table', '/product/exam/list', cols, table);


    //监听工具条
    table.on('tool(productExam-table)', function (obj) {
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
                    table.reload('productExam-table');
                    layer.msg(ajaxReturnData.msg, {icon: 1});
                } else {
                    layer.msg('复制失败', {icon: 5});
                }
                layer.close(index);
            });
        } else if (obj.event === 'productExamEdit') {
            location.href = PageContext.getUrl("/product/addProductExam/" + data.id);
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