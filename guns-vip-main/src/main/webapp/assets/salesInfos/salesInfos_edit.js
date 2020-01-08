/**
 * 详情对话框
 */
var SalesInfosInfoDlg = {
    data: {
        state: "",
        phone: "",
        saleNum: "",
        saleName: "",
        location: "",
        payMoney: "",
        productId: "",
        productNumber:"",
        productPrice:"",
        productName:""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/salesInfos/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('salesInfosForm', result.data);
    $("#productNumberFlag").val($("#productNumber").val());

    $('#productImage').attr('src', "/productInfos/previewAvatar?avatar="+result.data.productId);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/salesInfos/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/salesInfos'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/salesInfos'
    });

});

//转化正整数
function changeSaleNum(value){
    var $ = layui.jquery;
    if(value == 0){
        alert("老板，数量不能为0~··");
    }
    console.log(value)
    if(value.indexOf("\.") != -1){
        alert("老板，数量不能输入小数~··");
        value = 1;
    }
    if(value < 0){
        alert("老板，下单数量不能为负数");
        value = 1;
    }

    // 购买数量和价格、库存的变化
    value = parseInt(value);
    var productNumber = $("#productNumber").val();
    var productPrice = $("#productPrice").val();
    var productPrice = parseInt(productPrice);
    var productNumberFlag = parseInt($("#productNumberFlag").val());
    var productNumber = parseInt(productNumber);
    if(value > productNumber){
        alert("库存数量不足，剩余库存为："+productNumber);
        value = 1;
    } else {
        console.log(productPrice,value);
        $("#payMoney").val(productPrice * value);
        $("#productNumber").val(productNumberFlag - value);
    }
    return value;
}