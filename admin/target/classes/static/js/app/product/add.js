layui.use(['form', 'layer', 'laydate', 'jquery', 'upload'], function () {
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer;
    var upload = layui.upload;
    var laydate = layui.laydate;
//金额处理方法
    // console.log(Number($("#amount").val()).toLocaleString());
//产品简称校验非空------------------------------------------
    $('#product-name').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            // layer.msg("601");
            $("#product-nameError").css('display', 'inline-block');
            // layer.tips('不能为空啊', that); //在元素的事件回调体中，follow直接赋予this即可
        } else {
            $("#product-nameError").css('display', 'none');
            $.ajax({
                url: PageContext.getUrl('/jiaoYanRename'),
                type: 'post',
                async: false,
                data: "name=" + $("#product-name").val(),
                success: function (data) {
                    if (data.flag == "true") {
                        $("#reName").css('display', 'none');
                    } else {
                        $("#reName").css('display', 'inline-block');
                    }
                    //重新加载当前页面
                    // location.reload();
                }
            });
        }
    });
    $('#geName').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            $("#geNameError").css('display', 'inline-block');
        } else {
            $("#geNameError").css('display', 'none');
            $.ajax({
                url: PageContext.getUrl('/jiaoYanRename'),
                type: 'post',
                async: false,
                data: "geName=" + $("#geName").val(),
                success: function (data) {
                    if (data.flag == "true") {
                        $("#reGeName").css('display', 'none');
                    } else {
                        $("#reGeName").css('display', 'inline-block');
                    }
                    //重新加载当前页面
                    // location.reload();
                }
            });
        }
    });
    $('#code').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            $("#codeError").css('display', 'inline-block');
        } else {
            $("#codeError").css('display', 'none');
            $.ajax({
                url: PageContext.getUrl('/jiaoYanRename'),
                type: 'post',
                async: false,
                data: "code=" + $("#code").val(),
                success: function (data) {
                    if (data.flag == "true") {
                        $("#reCode").css('display', 'none');
                    } else {
                        $("#reCode").css('display', 'inline-block');
                    }
                    //重新加载当前页面
                    // location.reload();
                }
            });
        }
    });
    $('#geCode').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            $("#geCodeError").css('display', 'inline-block');
        } else {
            $("#geCodeError").css('display', 'none');
            $.ajax({
                url: PageContext.getUrl('/jiaoYanRename'),
                type: 'post',
                async: false,
                data: "geCode=" + $("#geCode").val(),
                success: function (data) {
                    if (data.flag == "true") {
                        $("#reGeCode").css('display', 'none');
                    } else {
                        $("#reGeCode").css('display', 'inline-block');
                    }
                    //重新加载当前页面
                    // location.reload();
                }
            });
        }
    });
    //合同编号
    $('#contractNo').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            $("#contractNoError").css('display', 'inline-block');
        } else {
            $("#contractNoError").css('display', 'none');
            $.ajax({
                url: PageContext.getUrl('/jiaoYanRename'),
                type: 'post',
                async: false,
                data: "contractNo=" + $("#contractNo").val(),
                success: function (data) {
                    if (data.flag == "true") {
                        $("#reContractNo").css('display', 'none');
                    } else {
                        $("#reContractNo").css('display', 'inline-block');
                    }
                    //重新加载当前页面
                    // location.reload();
                }
            });
        }
    });
//产品协议校验------------
    $('#productAgreementType').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0) {
            $("#productAgreementTypeError").css('display', 'inline-block');
        } else {
            $("#productAgreementTypeError").css('display', 'none');
        }
    });
//募集规模--------------
    $('#amount').blur(function () {
        var that = this;
        // if(/^[1-9]\d*$/.test((that.val().toString()).replaceAll(/,/g, ""))==false){
        //     $("#amountError").css('display','inline-block');
        // }
        console.log($('#amount').val());
        console.log(that);
        // that.value=that.getAttribute("data-value");
        // alert(that.getAttribute("data-value"));
        if (that.value == "" || that.value.length <= 0) {
            $("#amountError").css('display', 'inline-block');
        } else {
            $("#amountError").css('display', 'none');
            $('#tenderInitAmount').val(that.value / 200);
        }
    });
    //产品期限
    $('#timeLimit').blur(function () {
        var that = this;
        // that.value=that.getAttribute("data-value");
        if (that.value == "" || that.value.length <= 0 || /^[1-9]\d*$/.test(that.value) == false) {
            $("#timeLimitError").css('display', 'inline-block');
        } else {
            $("#timeLimitError").css('display', 'none');
        }
    });
    //起购金额
    $('#tenderInitAmount').blur(function () {
        var that = this;
        // that.value=that.getAttribute("data-value");
        if (that.value == "" || that.value.length <= 0 || /^[1-9]\d*$/.test(that.value) == false) {
            // alert("777");
            $("#tenderInitAmountError").css('display', 'none');
            $("#tenderInitAmountNotNull").css('display', 'inline-block');
        } else if ($("#amount").val() / 200 > that.value) {
            // alert("888");
            $("#tenderInitAmountNotNull").css('display', 'none');
            $("#tenderInitAmountError").css('display', 'inline-block');
        } else {
            $("#tenderInitAmountError").css('display', 'none');
            $("#tenderInitAmountNotNull").css('display', 'none');
        }
    });
    //递增金额
    $('#tenderIncreaseAmount').blur(function () {
        var that = this;
        // that.value=that.getAttribute("data-value");
        if (that.value == "" || that.value.length <= 0 || /^[1-9]\d*$/.test(that.value) == false) {
            $("#tenderIncreaseAmountError").css('display', 'inline-block');
        } else {
            $("#tenderIncreaseAmountError").css('display', 'none');
        }
    });
    //预期年化收益率 是否是整数且小数点后一位
    $('#annualRate').blur(function () {
        var that = this;
        // that.value=that.getAttribute("data-value");
        if (that.value == "" || that.value.length <= 0 || /^[0-9]+([.]{1}[0-9]{1})?$/.test(that.value) == false || that.value < 0) {
            $("#annualRateError").css('display', 'inline-block');
        } else {
            $("#annualRateError").css('display', 'none');
        }
    });
    //单笔最大购买金额
    $('#highSingleInvest').blur(function () {
        var that = this;
        // var mVal=that.getAttribute("data-value");
        if (that.value == "" || that.value.length <= 0 || /^[1-9]\d*$/.test(that.value) == false) {
            $("#highSingleInvestError").css('display', 'inline-block');
        } else {
            $("#highSingleInvestError").css('display', 'none');
        }
    });
//收款账户----------------------------------------------------------------------
    $('#hxAcctNo').blur(function () {
        var that = this;
        if (that.value == "" || that.value.length <= 0 || that.value.length > 20) {
            $("#hxAcctNoError").css('display', 'inline-block');
        } else {
            $("#hxAcctNoError").css('display', 'none');
        }
    });

    //layui校验
    form.verify({
        productNotNuLL: function (value, item) { //非空校验
            if (value == null || value.length < 1) {
                // console.log(item);
                item.next("label").css('display', 'inline-block');
                return '带星号的必填项不能为空';
            }
        },
        tenderInitAmount: function (value, item) { //起投金额
            // value=item.getAttribute("data-value");
            if (!/^[1-9]\d*$/.test(value)) {
                return '起投金额必须为正整数';
            }
            if ($("#amount").val() / 200 > value) {
                return '起投金额必须大于募集规模的200分之一';
            }
            // $("#tenderInitAmount").val(item.getAttribute("data-value"));
        }, highSingleInvest: function (value, item) { //单笔最大购买不得少于起投金额
            console.log(item);
            // value=item.getAttribute("data-value");
            // alert($("#tenderInitAmount").val());
            // alert(value);
            if (value < $("#tenderInitAmount").val()) {
                return '单笔最大购买不得少于起投金额';
            }
            // alert(item.getAttribute("data-value"));
            // $("#highSingleInvest").val(item.getAttribute("data-value"));
        }
    });

//上一步下一步隐藏显示
    $("#dataTwo").hide();
    $("#jbInfo").addClass("layui-btn");
    $("#fuJ").addClass("layui-btn layui-btn-primary");

    $("#jbInfo").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#jbInfo").addClass("layui-btn");
        $("#fuJ").addClass("layui-btn layui-btn-primary");
        $("#dataTwo").hide();
        $("#dataOne").show();
    });
    $("#fuJ").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#fuJ").addClass("layui-btn");
        $("#jbInfo").addClass("layui-btn layui-btn-primary");
        $("#dataOne").hide();
        $("#dataTwo").show();
    });

    $("#one_show").click(function () {
        $("#jbInfo").removeClass();
        $("#fuJ").removeClass();
        $("#jbInfo").addClass("layui-btn");
        $("#fuJ").addClass("layui-btn layui-btn-primary");
        $("#dataTwo").hide();
        $("#dataOne").show();
    });

    $("#two_show_issue").click(function () {
        $("#productAdvantage").val(pda.getContent());
        $("#projectSummary").val(ps.getContent());
        $("#purchaseDesc").val(pcd.getContent());
        $("#reasonsPrepay").val(rp.getContent());
    });

    $("#two_show").click(function () {
        $("#productAdvantage").val(pda.getContent());
        $("#projectSummary").val(ps.getContent());
        $("#purchaseDesc").val(pcd.getContent());
        $("#reasonsPrepay").val(rp.getContent());
    });


    var rp = UE.getEditor('editorContent');
    var pda = UE.getEditor('editorContentProductAdvantage');
    var ps = UE.getEditor('editorContentProjectSummary');
    var pcd = UE.getEditor('editorContentPurchaseDesc');

    rp.focus();
    pda.focus();
    ps.focus();
    pcd.focus();

    rp.addListener("ready", function () {
        // editor准备好之后才可以使用
        if ($("#id").val() != null && $("#id").val().length > 0) {
            // alert("2333");
            rp.setContent($("#reasonsPrepay").val());
        } else {
            rp.setContent("产品管理人享有在起息日满45天后的投资期限内任一天提前终止该产品的权利，并在提前终止日前3个工作日发布通知。投资人无法选择提前结束");
        }
    });
    pda.addListener("ready", function () {
        if ($("#id").val() != null && $("#id").val().length > 0) {
            pda.setContent($("#productAdvantage").val());
        } else {
            pda.setContent("投资收益=投资本金*[预期年化收益]*产品存续天数/365\n以本产品预期年化收益6.123%为例：\n您购买了10万元份额的中铁11号，那么到期后的收益是：\n(100000*0.06123*38)/365=637.47");
        }
    });
    ps.addListener("ready", function () {
        if ($("#id").val() != null && $("#id").val().length > 0) {
            ps.setContent($("#projectSummary").val());
        } else {
            ps.setContent("天津金融资产交易所“中铁11号”收益分享合约产品，本产品在天津金融资产交易所挂牌发行。\n产品特点：\n稳收益：预期年化收益达6.123%\n低风险：本收益分享合约产品的底层资产相关交易由上市公司提供担保增信\n超安心：国有金融交易中心甄选优质投资标的");
        }
    });
    pcd.addListener("ready", function () {
        if ($("#id").val() != null && $("#id").val().length > 0) {
            pcd.setContent($("#purchaseDesc").val());
        } else {
            pcd.setContent("本产品直接使用账户可用余额购买，请提前安排资金，资金可使用绑定卡转入账户参与购买。\n产品总规模10000000元，起购金额40000元，追加金额10000元的整数倍，单笔最大购买金额1000000元，可多次购买。\n2019-06-19  09：00面向全体用户发售。\n本产品起息日为2019年06月21日，到期日为2109年09月19日，产品到期后本金及利息收益将于3个工作日内转入您的账户余额。\n请您在购买产品前仔细阅读并确认产品合同及认购协议。");
        }
    });
    rp.autoFloatEnabled = false;
    pda.autoFloatEnabled = false;
    ps.autoFloatEnabled = false;
    pcd.autoFloatEnabled = false;//防挡


    $('#saveButton').click(function () {
        // alert(pda.getContent());
        $("#productAdvantage").val(pda.getContent());
        $("#projectSummary").val(ps.getContent());
        $("#purchaseDesc").val(pcd.getContent());
        $("#reasonsPrepay").val(rp.getContent());
    });

    $('#saveButtonOne').click(function () {
        $("#productAdvantage").val(pda.getContent());
        $("#projectSummary").val(ps.getContent());
        $("#purchaseDesc").val(pcd.getContent());
        $("#reasonsPrepay").val(rp.getContent());
    });

    $('#temporaryStorage').click(function () {

        $("#productAdvantage").val(pda.getContent());
        $("#projectSummary").val(ps.getContent());
        $("#purchaseDesc").val(pcd.getContent());
        $("#reasonsPrepay").val(rp.getContent());
        $.ajax({
            type: 'post',
            url: PageContext.getUrl('/product/temporaryStorage'),
            data: $("#searchFormOne").serialize(),
            dataType: "html",
            // timeout: 5000,
            // async: true,
            success: function (data) {
                data = JSON.parse(data);
                if (data.flag == "true") {
                    layer.msg("添加成功");
                    $('label').css('display', 'none');
                } else {
                    console.log(data);
                    if (data.res == "notFind") {
                        layer.msg("重要信息不能为空");
                        if (data.errName == "name") {
                            $("#product-nameError").css('display', 'inline-block');
                        }
                        if (data.errCode == "code") {
                            $("#codeError").css('display', 'inline-block');
                        }
                        if (data.errGeCode == "geCode") {
                            $("#geCodeError").css('display', 'inline-block');
                        }
                        if (data.errGeName == "geName") {
                            $("#geNameError").css('display', 'inline-block');
                        }
                        if (data.errContractNo == "contractNo") {
                            $("#contractNoError").css('display', 'inline-block');
                        }
                    } else if (data.res == "reName") {
                        layer.msg("产品简称不能重复");
                        $("#reName").css('display', 'inline-block');
                    } else if (data.res == "reGeName") {
                        layer.msg("金交所产品名称不能重复");
                        $("#reName").css('display', 'inline-block');
                    } else if (data.res == "reCode") {
                        layer.msg("产品编码不能重复");
                        $("#reCode").css('display', 'inline-block');
                    } else if (data.res == "reGeCode") {
                        layer.msg("金交所产品编号不能重复");
                        $("#reCode").css('display', 'inline-block');
                    } else if (data.res == "reContractNo") {
                        layer.msg("合同编号不能重复");
                        $("#reContractNo").css('display', 'inline-block');
                    } else {
                        top.layer.msg("操作失败，请稍后重试");
                    }
                }
                //重新加载当前页面
                // location.reload();
            },
            error: function (data) {
                // alert("12133");
                top.layer.msg(data.msg, {icon: 5});
            }
        });
        return false;
    });


    // //监听下一步---录入列表编辑
    form.on('submit(addProduct)', function (data) {
        console.log(data);
        $.ajax({
            url: PageContext.getUrl('/product/productEditOne'),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                if (data.flag == "true") {
                    //添加，修改成功后为ID赋值
                    $("#id").val(data.productId);
                    $('label').css('display', 'none');
                    $("#jbInfo").removeClass();
                    $("#fuJ").removeClass();
                    $("#fuJ").addClass("layui-btn");
                    $("#jbInfo").addClass("layui-btn layui-btn-primary");
                    $("#dataOne").hide();
                    $("#dataTwo").show();
                    // window.location.href="/product/list";
                    // layer.msg("添加成功");
                } else {
                    if (data.res == "notFind") {
                        layer.msg("产品简称不能重复");
                        $("#product-nameError").css('display', 'inline-block');
                        $("#geNameError").css('display', 'inline-block');
                        $("#codeError").css('display', 'inline-block');
                        $("#geCodeError").css('display', 'inline-block');
                        $("#contractNoError").css('display', 'inline-block');
                    } else if (data.res == "reName") {
                        layer.msg("产品简称不能重复");
                        $("#reName").css('display', 'inline-block');
                    } else if (data.res == "reGeName") {
                        layer.msg("金交所产品名称不能重复");
                        $("#reName").css('display', 'inline-block');
                    } else if (data.res == "reCode") {
                        layer.msg("产品编码不能重复");
                        $("#reCode").css('display', 'inline-block');
                    } else if (data.res == "reGeCode") {
                        layer.msg("金交所产品编号不能重复");
                        $("#reCode").css('display', 'inline-block');
                    } else if (data.res == "reContractNo") {
                        layer.msg("合同编号不能重复");
                        $("#reContractNo").css('display', 'inline-block');
                    } else {
                        top.layer.msg("操作失败，请稍后重试");
                    }
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })

    //监听下一步---发布列表编辑
    form.on('submit(addProductIssue)', function (data) {
        $.ajax({
            url: PageContext.getUrl('/product/productEditTwo'),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                if (data.flag == "true") {
                    $("#id").val(data.productId);
                    $('label').css('display', 'none');
                    $("#jbInfo").removeClass();
                    $("#fuJ").removeClass();
                    $("#fuJ").addClass("layui-btn");
                    $("#jbInfo").addClass("layui-btn layui-btn-primary");
                    $("#dataOne").hide();
                    $("#dataTwo").show();
                    // window.location.href="/product/listManageIssue";
                    // layer.msg("添加成功");
                } else {
                    if (data.result == "reName") {
                        $("#reName").css('display', 'inline-block');
                    } else if (data.result == "reCode") {
                        $("#reCode").css('display', 'inline-block');
                    } else if (data.result == "reContractNo") {
                        $("#reContractNo").css('display', 'inline-block');
                    } else {
                        top.layer.msg(data.msg, {icon: 5});
                    }
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })

    //监听-提交审核
    form.on('submit(add)', function (data) {
        $.ajax({
            url: PageContext.getUrl('/product/productFile/' + $("#id").val()),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                if (data.flag == "true") {
                    window.location.href = "/product/list";
                    // layer.msg("添加成功");
                } else {
                    lay.msg(data.msg)
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })

    //监听-重新提交审核-进入发布列表
    form.on('submit(addTwo)', function (data) {
        $.ajax({
            url: PageContext.getUrl('/product/productFile/' + $("#id").val()),
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                if (data.flag == "true") {
                    window.location.href = "/product/listManageIssue";
                    // layer.msg("添加成功");
                } else {
                    lay.msg(data.msg)
                }
                //重新加载当前页面
                // location.reload();
            }
        });
        return false;
    })
    //日期时间选择范围
    laydate.render({
        elem: '#tenderStartTime' //募集开始时间
        , type: 'datetime'    //是否显示时间
        , format: 'yyyy-MM-dd HH:mm'
        //,trigger: 'click'
        //,lang: 'en'    //英文
        //,range: true //开启日期范围，默认使用“_”分割
        /*,done: function(value, date, endDate){
          console.log(value, date, endDate);
        }
        ,change: function(value, date, endDate){
          console.log(value, date, endDate);
        }*/
    });
    laydate.render({
        elem: '#tenderEndTime' //募集结束时间
        , type: 'datetime'
        , format: 'yyyy-MM-dd HH:mm'
    });


    //多文件多次上传-layui不支持一次传输文件
    //定义一个字符串存取attachFile异步请求数据
    var map = {};
    var fileName = null;
    //多文件上传
    upload.render({
        elem: '#test2'
        , url: '/fuJian/upload/'
        , accept: 'file'
        , exts: 'jpg|jpeg|gif|png|doc|docx|pdf|xlsx|xls|pptx|ppt'
        , multiple: true
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                // fileName=file.name;
                // map[file.name]='';
            });
        }
        , done: function (res) {
            if (res.code == '0') {
                // var i=0;
                // for(var prop in map){
                //   if(map.hasOwnProperty(prop)){
                map[res.oldFileName] = eval("(" + res.attachFile + ")")[0].path;
                //     i++;
                //   }
                // }
                var ht = $('#demo2').html();
                $('#demo2').html(ht + '<a href="' + eval("(" + res.attachFile + ")")[0].path + '" target="_blank"><i class="layui-icon layui-icon-close"></i>' + res.oldFileName + '</a>');
                //将获取的文件放入map中
                $("#attachFile").val(JSON.stringify(map));
                layer.msg("文件" + res.tmpFileName + "上传成功");
                //上传完毕
            } else if (res.code == '1') {
                layer.msg("文件" + res.tmpFileName + "不能为空");
                $('#demo2').html(" ");
            } else if (res.code = 'false') {
                layer.msg(res.message);
                $('#demo2').html(" ");
            } else if (res.code = 'lose') {
                layer.msg("文件" + res.tmpFileName + "上传失败");
                $('#demo2').html(" ");
            } else if (res.code = 'big') {
                layer.msg("文件" + res.tmpFileName + "大小不能超过20M");
                $('#demo2').html(" ");
            } else {
                layer.msg("文件上传时发生错误,请稍后重试");
                $('#demo2').html(" ");
            }
        }
    });

    //点击事件
    $(document).on('click', '.pdfList i', function () {
        delete(map[$(this)[0].parentNode.text]);
        $("#attachFile").val(JSON.stringify(map));
        $(this).parent('a').remove();
    });


});