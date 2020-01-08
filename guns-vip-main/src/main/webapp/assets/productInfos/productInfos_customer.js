layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var ProductInfos = {
        tableId: "productInfosTable"
    };

    /**
     * 初始化表格的列
     */
    ProductInfos.initColumn = function () {
        return [[{type: 'checkbox'},
            {field: 'id', hide: true, title: '产品ID'},
            {field: 'productImage', sort: true, title: '商品图片地址',align:"center",templet:'#productImg', style: 'height:110px;padding:0px;'},
            {field: 'productName', sort: true, title: '产品名称', style: 'height:110px;'},
            {field: 'warehouseLocation', sort: true, title: '仓库地址'},
            {field: 'productNumber', sort: true, title: '产品库存数目'},
            {field: 'productClassify', sort: true, title: '产品分类'},
            {field: 'articleNumber', sort: true, title: '货号', minWidth  :50},
            {field: 'productPrice', sort: true, title: '进货价'},
            {field: 'marketPrice', sort: true, title: '市场价'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth  :120}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ProductInfos.search = function () {
        var queryData = {};
        queryData['productName'] = $("#condition").val();
        console.log(queryData);
        table.reload(ProductInfos.tableId, {
            where: queryData, page: {curr: 1}
        }, 'data');
    };

    /**
     * 弹出添加对话框
     */
    ProductInfos.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/productInfos/add';
    };

    /**
     * 导出excel按钮
     */
    ProductInfos.exportExcel = function () {
        var checkRows = table.checkStatus(ProductInfos.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    ProductInfos.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/productInfos/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    ProductInfos.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/productInfos/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ProductInfos.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 前往下单页面
     *
     * @param data 点击按钮时候的行数据
     */
    ProductInfos.onBuyItem = function (data) {
        window.location.href = Feng.ctxPath + '/productInfos/buyPage?id=' + data.id;
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ProductInfos.tableId,
        url: Feng.ctxPath + '/productInfos/list_customer',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ProductInfos.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ProductInfos.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ProductInfos.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ProductInfos.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ProductInfos.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ProductInfos.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ProductInfos.onDeleteItem(data);
        } else if (layEvent === 'buy') {
            ProductInfos.onBuyItem(data);
        }
    });
});

