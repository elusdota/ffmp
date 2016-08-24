/**
 * Created by jiangliang on 2016/8/18.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    $.ajax('rest/task?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#maintenanceProject").val(data.maintenanceProject.code);
            $("#proid").val(data.maintenanceProject.id);
            $("#customer").val(data.customer.name);
            $("#description").val(data.description);
            $("#repairnumber").val(data.repairnumber);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $('#materialTable').bootstrapTable({
        method: 'POST',
        url: 'rest/material/findByMaintenanceTask',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: id
            };
            return JSON.stringify(fin);
        },
        columns: [{title: "序号", formatter: runningFormatter}
            , {title: "材料名称", field: "name", align: 'center', sortable: true}
            , {title: "生产厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "单位", field: "unite", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "单价", field: "price", align: 'center', sortable: true}
            , {title: "金额", field: "total", align: 'center', sortable: true}
        ]
    });
    $('#materialTable').bootstrapTable({
        method: 'POST',
        url: 'rest/material/findByMaintenanceTask',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: id
            };
            return JSON.stringify(fin);
        },
        columns: [{title: "序号", formatter: runningFormatter}
            , {title: "材料名称", field: "name", align: 'center', sortable: true}
            , {title: "生产厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "单位", field: "unite", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "单价", field: "price", align: 'center', sortable: true}
            , {title: "金额", field: "total", align: 'center', sortable: true}
        ]
    });
    $.ajax('rest/taskEquipemt?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#equipmentTable').bootstrapTable({
                //height: 350,
                data: data.equipments,
                columns: [
                    {title: "序号", formatter: runningFormatter}
                    , {title: "设备名称", field: "name", align: 'center', sortable: true}
                    , {title: "设备编码", field: "code", align: 'center', sortable: true}
                    , {title: "大类", field: "typemax", align: 'center', sortable: true}
                    , {title: "小类", field: "typemin", align: 'center', sortable: true}
                    , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
                    , {title: "型号", field: "model", align: 'center', sortable: true}
                    , {title: "数量", field: "quantity", align: 'center', sortable: true}
                    , {title: "位置", field: "location", align: 'center', sortable: true}
                ],
                striped: true
            });
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});
function getSubData(){
    var maintenanceTask = {
        name: $('#organization').val()
    };
    var flowchartSteps = {
        name: $('#customer').val()
    };
    var data = {
        maintenanceTask:maintenanceTask,
        flowchartSteps:flowchartSteps,
        suspended:"",
        description: $("#name").val().trim()
    }
    return data;
}