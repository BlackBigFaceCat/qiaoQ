layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var SalesInfos = {
        tableId: "salesInfosTable"
    };

    /**
     * 初始化表格的列
     */
    SalesInfos.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '销售单ID'},
            {field: 'sendUserName', sort: true, title: '发货人姓名', width  :120, style: 'height:110px;'},
            {field: 'saleName', sort: true, title: '收货人姓名', width  :120},
            {field: 'phone', sort: true, title: '电话', width  :140},
            {field: 'location', sort: true, title: '地址'},
            {field: 'productImage', sort: true, title: '产品图片',align:"center",templet:'#productImg', style: 'height:110px;padding:0px;'},
            {field: 'productId', sort: true, title: '产品名称', minWidth  :120},
            {field: 'saleNum', sort: true, title: '购买数量', width  :120},
            {field: 'payMoney', sort: true, title: '付款金额', width  :120},
            {field: 'state', sort: true, title: '付款状态', width  :120},
            {align: 'center', toolbar: '#tableBar', title: '操作', width  :230}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SalesInfos.search = function () {
        var queryData = {};
        queryData['saleName'] = $("#condition").val();
        table.reload(SalesInfos.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 点击查询未付款的销售单
     */
    SalesInfos.btnHaveNotPay = function () {
        var queryData = {};
        queryData['state'] = "0";
        table.reload(SalesInfos.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 点击查询已付款的销售单
     */
    SalesInfos.btnHavePay = function () {
        var queryData = {};
        queryData['state'] = "1";
        table.reload(SalesInfos.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    /**
     * 弹出添加对话框
     */
    SalesInfos.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/salesInfos/add';
    };

    /**
     * 导出excel按钮
     */
    SalesInfos.exportExcel = function () {
        var checkRows = table.checkStatus(SalesInfos.tableId);
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
    SalesInfos.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/salesInfos/edit?id=' + data.id;
    };

    /**
     * 点击确认收钱
     *
     * @param data 点击按钮时候的行数据
     */
    SalesInfos.onGetMoneyItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/salesInfos/getMoney", function (data) {
                Feng.success("赚钱啦，好开心!");
                table.reload(SalesInfos.tableId);
            }, function (data) {
                Feng.error("确认失败!~" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("确认收到钱了嘛?", operation);
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    SalesInfos.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/salesInfos/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SalesInfos.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SalesInfos.tableId,
        url: Feng.ctxPath + '/salesInfos/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SalesInfos.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnHavePay').click(function () {
        SalesInfos.btnHavePay();
    });

    // 搜索按钮点击事件
    $('#btnHaveNotPay').click(function () {
        SalesInfos.btnHaveNotPay();
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SalesInfos.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        SalesInfos.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        SalesInfos.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SalesInfos.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SalesInfos.openEditDlg(data);
        } else if (layEvent === 'delete') {
            SalesInfos.onDeleteItem(data);
        } else if (layEvent === 'getMoney') {
            if(data.state == "未付款"){
                SalesInfos.onGetMoneyItem(data);
            } else{
                Feng.success("已经收过钱了哦~");
            }
        }
    });
});
