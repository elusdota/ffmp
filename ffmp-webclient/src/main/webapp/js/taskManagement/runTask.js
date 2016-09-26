/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    $("#viewTask").attr("disabled", "true");
    $("#repairAuditTask").attr("disabled", "true");
    $("#auditTask").attr("disabled", "true");
    $("#materialsTask").attr("disabled", "true");
    $('#taskTable').bootstrapTable({
        method: 'POST',
        url: 'rest/task/findRunTask',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#viewTask").removeAttr("disabled");
            $.ajax('rest/taskNode/getSteps?id=' + row.id, {
                type: 'GET',
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    if (data.name == '维保负责人审核') {
                        $("#repairAuditTask").removeAttr("disabled");
                    }
                    else {
                        $("#repairAuditTask").attr("disabled", "true");
                    }
                    if (data.name == '维保总监审核') {
                        $("#auditTask").removeAttr("disabled");
                    }
                    else {
                        $("#auditTask").attr("disabled", "true");
                    }
                    if (data.name == '申请材料') {
                        $("#materialsTask").removeAttr("disabled");
                    }
                    else {
                        $("#materialsTask").attr("disabled", "true");
                    }
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        },
        onUncheck: function (row) {
            $("#viewTask").attr("disabled", "true");
            $("#auditTask").attr("disabled", "true");
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
            , {title: "任务名称", field: "name", sortable: true}
            , {title: "报修单编号", field: "repairnumber", sortable: true}
            , {title: "起始日期", field: "startdate", sortable: true}
            , {title: "到期日期", field: "enddate", sortable: true}
            , {title: "任务描述", field: "description", sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#createTask").click(function () {
    $("#main-content").load("taskManagement/createTask", function () {
        $("#main-content").fadeIn();
    });
});
$("#viewTask").click(function () {
    var data = $('#taskTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/taskInformation?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#materialsTask").click(function () {
    var data = $('#taskTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/material?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#auditTask").click(function () {
    var data = $('#taskTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/auditTask?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});