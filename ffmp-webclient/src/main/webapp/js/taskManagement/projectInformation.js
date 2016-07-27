/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    $.ajax('rest/maintenanceProject?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#organization").val(data.delegate.name);
            $("#customer").val(data.customer.name);
            $("#address").val(data.address);
            $("#area").val(data.area);
            $("#totalHeight").val(data.totalHeight);
            $("#floors").val(data.floors);
            $("#nature").val(data.nature);
            $("#manager").val(data.manager);
            $("#managerTelephone").val(data.managerTelephone);
            $("#equipmentCase").val(data.equipmentCase);
            getEquiment(data.id);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    function getEquiment(projectId) {
        $('#equipmentTable').bootstrapTable({
            method: 'POST',
            url: 'rest/equipment/findProject',
            sidePagination: 'server',
            striped: true,
            singleSelect: true,
            checkbox: true,
            clickToSelect: true,
            onCheck: function (row) {
            },
            queryParams: function (params) {
                var fin = {
                    offset: params.offset,
                    limit: params.limit,
                    order: params.order,
                    sort: params.sort,
                    search: projectId
                };
                return JSON.stringify(fin);
            },
            columns: [
                {title: "序号", formatter: runningFormatter}
                , {title: "设备名称", field: "name", align: 'center', sortable: true}
                , {title: "类型", field: "type", align: 'center', sortable: true}
                , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
                , {title: "型号", field: "model", align: 'center', sortable: true}
                , {title: "数量", field: "quantity", align: 'center', sortable: true}
            ]
        });
    }

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});