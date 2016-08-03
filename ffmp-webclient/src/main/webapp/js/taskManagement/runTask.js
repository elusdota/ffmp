/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    $("#viewTask").attr("disabled", "true");
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
    $("#main-content").load("taskManagement/taskInformation?id="+data[0].id, function () {
        $("#main-content").fadeIn();
    });
});