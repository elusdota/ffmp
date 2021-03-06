/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    $("#viewTask").attr("disabled", "true");
    $("#detailsTask").attr("disabled", "true");
    $('#taskTable').bootstrapTable({
        method: 'POST',
        url: 'rest/task/findHistoryTask',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#viewTask").removeAttr("disabled");
            $("#detailsTask").removeAttr("disabled");
        },
        onUncheck: function (row) {
            $("#viewTask").attr("disabled", "true");
            $("#detailsTask").attr("disabled", "true");
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
$("#viewTask").click(function () {
    var data = $('#taskTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/taskInformation?id="+data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#detailsTask").click(function () {
    var data = $('#taskTable').bootstrapTable('getSelections');
    $("#main-content").load("taskManagement/auditTask?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});