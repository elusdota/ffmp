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
    $('#equipmentTable').bootstrapTable({
        method: 'POST',
        url: 'rest/taskEquipemt/findByMaintenanceTask',
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
            , {title: "设备名称", field: "equipment.name", align: 'center', sortable: true}
            , {title: "设备编码", field: "equipment.code", align: 'center', sortable: true}
            , {title: "大类", field: "equipment.typemax", align: 'center', sortable: true}
            , {title: "小类", field: "equipment.typemin", align: 'center', sortable: true}
            , {title: "厂家", field: "equipment.manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "equipment.model", align: 'center', sortable: true}
            , {title: "数量", field: "equipment.quantity", align: 'center', sortable: true}
            , {title: "位置", field: "equipment.location", align: 'center', sortable: true}
            , {title: "备注", field: "description", align: 'center', sortable: true}
        ]
    });
    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    $("#approved").click(function () {
        $.ajax('rest/taskNode', {
            type: 'POST',
            data: JSON.stringify(getSaveData("yes")),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                outLoading();
                $("#main-content").load("taskManagement/runTask", function () {
                    $("#main-content").fadeIn();
                });
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
            , beforeSend: function () {
                loading();
            }
        });
    });
    $("#rejected").click(function () {
        $.ajax('rest/taskNode', {
            type: 'POST',
            data: JSON.stringify(getSaveData("no")),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                outLoading();
                $("#main-content").load("taskManagement/runTask", function () {
                    $("#main-content").fadeIn();
                });
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
            , beforeSend: function () {
                loading();
            }
        });
    });
    function getSaveData(tmper) {
        var data = {
            maintenanceTaskId: $("#id").val().trim(),
            stepResult: tmper
        }
        return data;
    }

    function loading() {
        $("#requestLoading").addClass("modal-backdrop fade in");
        $("#requestLoading").removeClass("hidden");
    }

    function outLoading() {
        $("#requestLoading").removeClass("modal-backdrop fade in");
        $("#requestLoading").addClass("hidden")
    }
});
