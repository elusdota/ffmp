/**
 * Created by jiangliang on 2016/8/22.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
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
    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});
$("#insertTable").click(function () {
    if ($("#materialForm").valid()) {
        $.ajax('rest/material', {
            type: 'POST',
            data: JSON.stringify(getInsertData()),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $('#materialTable').bootstrapTable('refresh');
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});
$("#over").click(function () {
    $.ajax('rest/taskNode', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#main-content").load("taskManagement/runTask", function () {
                $("#main-content").fadeIn();
            });
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
function getSaveData() {
    var data = {
        id: $("#id").val().trim(),
        rest:'yes'
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#equipumentname").val().trim(),
        unite: $("#unite").val().trim(),
        price: $("#price").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        model: $("#model").val().trim(),
        quantity: $("#quantity").val().trim()
    }
    return data;
}