/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $("#queryProject").attr("disabled", "true");
    $("#allocationEquipment").attr("disabled", "true");
    $('#projectTable').bootstrapTable({
        method: 'POST',
        url: 'rest/maintenanceProject/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#queryProject").removeAttr("disabled");
            $("#allocationEquipment").removeAttr("disabled");
        },
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: params.search
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "项目名称", field: "name"}
            , {title: "项目编号", field: "code"}
            , {title: "地址", field: "address"}
            , {title: "建筑面积", field: "area"}
            , {title: "建筑总高度", field: "totalHeight"}
            , {title: "楼层", field: "floors"}
            , {title: "使用性质", field: "nature"}
            , {title: "消防安全管理人", field: "manager"}
            , {title: "联系电话", field: "managerTelephone"}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#createProject").click(function () {
    $("#main-content").load("taskManagement/createProject", function () {
        $("#main-content").fadeIn();
    });
});
$("#allocationEquipment").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/equipment?id="+data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#queryProject").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/projectInformation?id="+data[0].id, function () {
        $("#main-content").fadeIn();
    });
});