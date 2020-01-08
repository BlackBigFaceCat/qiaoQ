/**
 * 详情对话框
 */
var RolePriceInfoDlg = {
    data: {
        roleId: "",
        price: ""
    }
};

layui.use(['layer','table','form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var table = layui.table;
    var layer = layui.layer;

    /**
     * 管理
     */
    var RolePrice = {
        tableId: "rolePriceTable"
    };

    var roleDetialPriceTable = {
        tableId: "roleDetialPriceTable"
    };

    RolePrice.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: '角色 价钱 关联表'},
            {field: 'roleId', hide: true, title: '角色ID'},
            {field: 'roleName', sort: true, title: '角色名称'},
            {field: 'productId', hide: true, title: '商品Id'},
            {field: 'productName',  hide: true,sort: true,  width:120, title: '商品名称'},
            {field: 'pricePresent', sort: true,  width:118, title: '价钱百分比'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    roleDetialPriceTable.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: '角色 商品 价钱 关联'},
            {field: 'roleId', hide: true, title: '角色ID'},
            {field: 'productImage', sort: true, title: '商品图片地址',align:"center",templet:'#productImg', style: 'height:110px;padding:0px;'},
            {field: 'productName', sort: true, title: '商品名称'},
            {field: 'roleName', sort: true, title: '角色名称'},
            {field: 'productId', hide: true, title: '商品Id'},
            {field: 'productPrice', sort: true, title: '商品进价'},
            {field: 'price', sort: true, title: '客户进价'},
            {align: 'center', toolbar: '#detialPriceTableBar', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + RolePrice.tableId,
        url: Feng.ctxPath + '/rolePrice/listWithPrice',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: RolePrice.initColumn()
    });


    //让当前iframe弹层高度适应
    admin.iframeAuto();


    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/rolePrice'
    });


    /**
     * 弹出添加价格百分比对话框
     */
    RolePrice.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '设置价钱百分比',
            area: ['510px', '360px'],
            content: Feng.ctxPath + '/rolePrice/edit?id='+ data.id+'&roleId='+data.roleId,
            end: function () {
                admin.getTempData('formOk') &&table.reload(RolePrice.tableId) && table.reload(roleDetialPriceTable.tableId);
            }
        });
    };

    /**
     * 设置每个商品的详细价格
     */
    RolePrice.editProductDetialPrice = function (data) {
        table.render({
            elem: '#' + roleDetialPriceTable.tableId,
            url: Feng.ctxPath + '/rolePrice/listWithDetialPrice?id='+ data.id+'&roleId='+data.roleId,
            page: true,
            height: "full-158",
            cellMinWidth: 100,
            cols: roleDetialPriceTable.initColumn()
        });
    };

    /**
     * 设置每个商品的详细价格
     */
    roleDetialPriceTable.editDetialPrice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '设置价钱',
            area: ['510px', '360px'],
            content: Feng.ctxPath + '/rolePrice/editDetial?id='+ data.id+'&roleId='+data.roleId,
            end: function () {
                admin.getTempData('formOk') &&table.reload(RolePrice.tableId) && table.reload(roleDetialPriceTable.tableId);
            }
        });
    };

    // 工具条点击事件
    table.on('tool(' + RolePrice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'editPrice') {
            RolePrice.openEditDlg(data);
        }else if (layEvent === 'editProductDetialPrice') {
            RolePrice.editProductDetialPrice(data);
        }else if (layEvent === 'editDetialPrice') {
            roleDetialPriceTable.editDetialPrice(data);
        }
    });

});