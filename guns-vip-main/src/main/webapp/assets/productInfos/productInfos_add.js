/**
 * 添加或者修改页面
 */
var ProductInfosInfoDlg = {
    data: {
        productName: "",
        warehouseLocation: "",
        productNumber: "",
        productClassify: "",
        articleNumber: "",
        productImage: "",
        productPrice: "",
        marketPrice: "",
        pleasedPrice: "",
        customerPleasedPrice: ""
    }
};

layui.use(['form','upload','admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var upload = layui.upload;
    var admin = layui.admin;

    //让当前iframe弹层高度适应
    admin.iframeAuto();


    var ajax = new $ax(Feng.ctxPath + "/productInfos/getProductId");
    var result = ajax.start();
    var id = result.data;

    // 设置 productInfo的值
    $("#id").val(id);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/productInfos/addItem", function (data) {
            Feng.success("添加成功！");
            document.getElementById("submitBtn").disabled=true;
            // window.location.href = Feng.ctxPath + '/productInfos'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    upload.render({
        elem: '#imgHead'
        , url: Feng.ctxPath + '/productInfos/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {

                var productInfos = $("#id").val();
                $('#productImage').attr('src', result);
                // $('#productImage').attr('src', "/productInfos/previewAvatar&avatar="+productInfos);
            });
        }
        , done: function (res) {
            // var ajax = new $ax(Feng.ctxPath + "/productInfos/updateAvatar", function (data) {
            //     Feng.success(res.message);
            // }, function (data) {
            //     Feng.error("修改失败!" + data.responseJSON.message + "!");
            // });
            // ajax.set("fileId", res.data.fileId);
            // ajax.start();
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/productInfos'
    });
});