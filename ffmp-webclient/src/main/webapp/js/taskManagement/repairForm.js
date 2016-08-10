/**
 * Created by jiangliang on 2016/7/25.
 */
$(document).ready(function () {
    $("#queryTask").attr("disabled", "true");
    $('#repairTable').bootstrapTable({
        method: 'POST',
        url: 'rest/repairForm/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#queryTask").removeAttr("disabled");
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
            , {title: "报修单编号", field: "code", sortable: true}
            , {title: "项目编号", field: "projectNumber", sortable: true}
            , {title: "报修人", field: "person", sortable: true}
            , {title: "故障部位", field: "parts", sortable: true}
            , {title: "故障描述", field: "description", sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    $("#submitData").click(function () {
        if ($("#repairForm").valid()) {
            $.ajax('rest/repairForm', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $('#repairTable').bootstrapTable('refresh');
                    $("#repairModel").modal("hide");
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    function getSaveData() {
        var data = {
            projectNumber: $("#projectNumber").val().trim(),
            person: $("#person").val().trim(),
            parts: $("#parts").val().trim(),
            description: $("#description").val().trim()
        }
        return data;
    }
})
$("#createRepair").click(function () {
    document.getElementById("repairForm").reset();
    $('#repairModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#repairModel").modal("show");
});
$("#giveupData").click(function () {
    document.getElementById("repairForm").reset();
    $("#repairModel").modal("hide");
});
$("#queryTask").click(function () {
    var data1 = $('#repairTable').bootstrapTable('getSelections');
    $.ajax('rest/task/repairFormCode?repairFormCode=' + data1[0].code, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            if (data != null) {
                $("#main-content").load("taskManagement/taskInformation?id=" + data.id, function () {
                    $("#main-content").fadeIn();
                });
            }
            $('#repairTable').bootstrapTable('refresh');
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});