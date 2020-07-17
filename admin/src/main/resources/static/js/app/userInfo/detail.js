layui.use(['form', 'laytpl', 'table', 'layer', 'jquery'], function () {

    var $ = layui.$,
        form = layui.form,
        laytpl = layui.laytpl,
        table = layui.table,
        layer = layui.layer;

    // 自定义审核状态单选钮验证规则
    form.verify({
        auditStatus: function (value) {
            if ($("input[name='auditStatus']:checked").val() == null) {
                return "审核状态必选";
            }
        }
    });


    var userId = $("#userId").val();

    // 跳转到详情后加载用户详情信息
    loadUserDetail(userId);


    // 加载用户详情
    function loadUserDetail(userId) {
        if (userId != null) {
            $.ajax({
                url: PageContext.getUrl('/userInfoManage/detail/' + userId),
                type: 'post',
                async: false,
                success: function (data) {
                    // alert(data)
                    // console.log(data);

                    if (data.flag === "true") {
                        var getTpl = document.getElementById('detailView').innerHTML
                            , view = document.getElementById('view');
                        laytpl(getTpl).render(data.data, function (html) {
                            view.innerHTML = html;
                        });

                    }

                }
            });
        }
    }


    // 列表列
    var cols = [[
        {
            field: 'orderId',
            title: '订单号',
            align: 'center',
            sort: true,
            totalRowText: '合计：'
        }, {
            field: 'transId',
            title: '支付流水',
            align: 'center',
            sort: true
        }, {
            field: 'trsAmount',
            title: '交易金额(元)',
            align: 'center',
            sort: true,
            totalRow: true
        }, {
            field: 'rtxnTypeCode',
            title: '交易类型',
            align: 'center',
            sort: true,
            templet: function (d) {
                /*
                 WTHI=资金转出
                 RCGI=资金转入
                 XYSG=兴业基金申购
                 XYSH=兴业基金赎回
                 XYSY=余额宝收益
                 CPDJ=产品购买-冻结
                 CPJD=产品流标-解冻
                 CPKK=产品满标-扣款
                 CPHK=产品回款
                 */

                var codeEnum = {
                    "WTHI": "资金转出",
                    "RCGI": "资金转入",
                    "XYSG": "申购",
                    "XYSH": "赎回",
                    "XYSY": "余额宝收益",
                    "CPDJ": "产品购买-冻结",
                    "CPJD": "产品流标-解冻",
                    "CPKK": "产品满标-扣款",
                    "CPHK": "产品回款"
                };

                if (d.rtxnTypeCode != undefined) {
                    return codeEnum[d.rtxnTypeCode]
                } else {
                    return "未知";
                }
            }
        }, {
            field: 'tradeStatus',
            title: '交易状态',
            align: 'center',
            fixed: "right",
            sort: true,
            templet: function (d) {
                if (d.tradeStatus != undefined) {
                    if (d.tradeStatus === '0') {
                        return "交易成功";
                    } else if (d.tradeStatus === '1') {
                        return "交易失败";
                    } else {
                        return "交易进行中";
                    }
                } else {
                    return "未知";
                }

            }
        }, {
            field: 'returnMsg',
            title: '回执消息',
            align: 'center',
            sort: true
        }, {
            field: 'createTime',
            title: '操作时间',
            align: 'center',
            sort: true
        }, {
            field: 'createTime',
            title: '回执时间',
            align: 'center',
            fixed: "right",
            sort: true
        }
    ]];


    // 表格渲染
    var initTable = Common.initTable('#userTranTables', '/userInfoManage/userTransactionList/' + userId + '/false', cols, table);

    //按钮事件监听
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //按钮事件定义
    var active = {
        search: function () {
            searchUserInfo();
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

    // 搜索用户信息
    function searchUserInfo() {
        var formData = $("#searchForm").serialize();
        var formJsonArray = $("#searchForm").serializeArray();

        if (!formParamIsNull(formJsonArray)) {
            // alert("为空添加数据")
            formData = formData + "&userId=" + userId;
        }

        $.ajax({
            url: PageContext.getUrl('/userInfoManage/isExist'),
            type: 'post',
            async: false,
            data: formData,
            success: function (data) {
                if (data.flag === "true") {
                    // 重新加载搜索页面
                    searchLoadPage(data.userId);
                } else {
                    layer.msg('查询不到此用户', {icon: 5});
                }
            }
        });
    }

    // 搜索成功后重新加载
    function searchLoadPage(userId) {
        var isNotEarning = false;
        // 加载用户信息
        loadUserDetail(userId);
        // 列表是否包含余额宝收益
        if ($("#excludeOption").prop("checked")) {
            // alert("不包含余额宝选中");
            isNotEarning = true;
        }
        // 加载用户交易记录列表
        Common.initTable('#userTranTables', '/userInfoManage/userTransactionList/' + userId + "/" + isNotEarning, cols, table);

    }

    // 点击不包含余额宝事件
    $("#excludeOption").click(function () {
        if ($("#excludeOption").prop("checked")) {
            Common.initTable('#userTranTables', '/userInfoManage/userTransactionList/' + userId + "/" + true, cols, table);
        }
    });


    // 判断表单中查询条件是否为空
    function formParamIsNull(jsonObject) {
        for (var i = 0; i < jsonObject.length; i++) {
            if (jsonObject[i].value !== "") {
                return true;
            }
            return false;
        }
    }


    // 返回到上一级页面
    $("#back").click(function () {
        window.history.go(-1);
    });


});