/**
 * 详情对话框
 */
var SalesInfosInfoDlg = {
    data: {
        sendUserName: "",
        sendUserPhone: "",
        reciveUserName: "",
        userPhone: "",
        usersLocation: "",
        productName: "",
        buyNumber: "",
        productPrice: "",
        marketPrice: "",
        productNumber: "",
        productImage: "",
        payMoney:"",
        productId:"",
        sendUserId:""

    }
};

layui.use(['form', 'admin','upload', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    var element = layui.element;
    var laydate = layui.laydate;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/salesInfos/buyDetailInfos?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('salesInfosForm', result.data);
    $("#productNumberFlag").val($("#productNumber").val());
    $("#productImage").attr('src', "/productInfos/previewAvatar?avatar="+result.data.productId);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/salesInfos/buy", function (data) {
            Feng.success("下单成功！");
            window.location.href = Feng.ctxPath + '/productInfos/customer'
        }, function (data) {
            Feng.error("下单失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });


    upload.render({
        elem: '#imgHead'
        , url: Feng.ctxPath + '/system/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#productImage').attr('src', result);
            });
        }
        , done: function (res) {
            var ajax = new $ax(Feng.ctxPath + "/system/updateAvatar", function (data) {
                Feng.success(res.message);
            }, function (data) {
                Feng.error("修改失败!" + data.responseJSON.message + "!");
            });
            ajax.set("fileId", res.data.fileId);
            ajax.start();
        }
        , error: function () {
            Feng.error("上传头像失败！");
        }
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/productInfos/customer'
    });
});


//转化正整数
function parseIntegerInfo(value){
    var $ = layui.jquery;
    if(value == 0){
        alert("下单数量不能为0");
    }
    console.log(value)
    if(value.indexOf("\.") != -1){
        alert("购买数量不能输入小数");
        value = 1;
    }
    if(value < 0){
        alert("下单数量不能为负数");
        value = 1;
    }

    // 购买数量和价格、库存的变化
    value = parseInt(value);
    var productNumber = $("#productNumber").val();
    var marketPrice = parseInt($("#marketPrice").val());
    var productPrice = parseInt($("#productPrice").val());
    var productNumberFlag = parseInt($("#productNumberFlag").val());
    var productNumber = parseInt(productNumber);
    if(value > productNumber){
        alert("库存数量不足，剩余库存为："+productNumber);
        value = 1;
    } else {
        $("#pMarketPrice").val(marketPrice * value);
        $("#payMoney").val(productPrice * value);
        $("#productNumber").val(productNumberFlag - value);
    }
    return value;
}