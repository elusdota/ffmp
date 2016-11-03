/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $("#queryProject").attr("disabled", "true");
    $("#allocationEquipment").attr("disabled", "true");
    $("#allocationInspections").attr("disabled", "true");
    $("#updateProject").attr("disabled", "true");
    $("#endProject").attr("disabled", "true");

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
            $("#allocationInspections").removeAttr("disabled");
            $("#updateProject").removeAttr("disabled");
            $("#endProject").removeAttr("disabled");
        },
        onUncheck: function (row) {
            $("#queryProject").attr("disabled", "true");
            $("#allocationEquipment").attr("disabled", "true");
            $("#allocationInspections").attr("disabled", "true");
            $("#updateProject").attr("disabled", "true");
            $("#endProject").attr("disabled", "true");
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
            , {title: "项目名称", field: "name", sortable: true}
            , {title: "项目编号", field: "code", sortable: true}
            , {title: "地址", field: "address", sortable: true}
            , {title: "建筑面积(m²)", field: "area", sortable: true}
            , {title: "建筑总高度(m)", field: "totalHeight", sortable: true}
            , {title: "楼层", field: "floors", sortable: true}
            , {title: "使用性质", field: "nature", sortable: true}
            , {title: "消防安全管理人", field: "manager", sortable: true}
            , {title: "建筑投入使用时间", field: "inputDate", sortable: true}
            , {title: "每月几号巡检", field: "days", sortable: true}
            , {title: "联系电话", field: "managerTelephone", sortable: true}
            , {
                title: "项目状态", field: "terminate", sortable: true, formatter: function (val) {
                    if (val) {
                        return '终止';
                    }
                    else {
                        return '正在维护';
                    }
                }
            }
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
    $("#main-content").load("taskManagement/equipment?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#queryProject").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/projectInformation?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#allocationInspections").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/inspection?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#updateProject").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/updateProject?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#endProject").click(function () {
    var data = $('#projectTable').bootstrapTable('getSelections');
    $.ajax('rest/maintenanceProject?id='+data[0].id, {
        type: 'DELETE',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#projectTable').bootstrapTable('refresh');
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
