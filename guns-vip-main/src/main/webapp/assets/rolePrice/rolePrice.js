layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 管理
     */
    var RolePrice = {
        tableId: "rolePriceTable"
    };

    /**
     * 初始化表格的列
     */
    RolePrice.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '角色 价钱 关联表'},
            {field: 'roleId', hide: true, title: '角色ID'},
            {field: 'roleName', sort: true, title: '角色名称'},
            {field: 'productId', hide: true, title: '商品Id'},
            {field: 'productName', sort: true, title: '商品名称'},
            {field: 'price', hide: true, title: '价钱'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    RolePrice.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(RolePrice.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    RolePrice.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/rolePrice/add';
    };

    /**
     * 导出excel按钮
     */
    RolePrice.exportExcel = function () {
        var checkRows = table.checkStatus(RolePrice.tableId);
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
    RolePrice.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/rolePrice/edit?id=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    RolePrice.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/rolePrice/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(RolePrice.tableId);
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
        elem: '#' + RolePrice.tableId,
        url: Feng.ctxPath + '/rolePrice/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: RolePrice.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        RolePrice.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        RolePrice.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        RolePrice.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + RolePrice.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            RolePrice.openEditDlg(data);
        } else if (layEvent === 'delete') {
            RolePrice.onDeleteItem(data);
        }
    });
});
