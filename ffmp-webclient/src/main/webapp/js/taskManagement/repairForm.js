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
            , {title: "报修单编号", field: "code"}
            , {title: "项目编号", field: "projectNumber"}
            , {title: "报修人", field: "person"}
            , {title: "故障部位", field: "parts"}
            , {title: "故障描述", field: "description"}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
    $("#submitData").click(function () {
        $.ajax('rest/repairForm', {
            type: 'POST',
            data: JSON.stringify(getSaveData()),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $('#repairTable').bootstrapTable('refresh');
            }, error: function (XMLHttpRequest) {
                $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
            }
        });
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
    $("#main-content").load("taskManagement/createProject", function () {
        $("#main-content").fadeIn();
    });
});
$("#queryProject").click(function () {
    var data1 = $('#repairTable').bootstrapTable('getSelections');
    $.ajax('rest/task/repairFormCode?repairFormCode='+data1[0].code, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            if(data!=null){
                $("#main-content").load("taskManagement/projectInformation?id=" + data.id, function () {
                    $("#main-content").fadeIn();
                });
            }
            $('#repairTable').bootstrapTable('refresh');
        }, error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
});