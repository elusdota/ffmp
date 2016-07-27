/**
 * Created by jiangliang on 2016/6/29.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $('#dispatchTable').bootstrapTable({
        method: 'POST',
        url: 'rest/dispatch/findAll',
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
                number: $("#number").val(),
                executor: $("#executor").val(),
                startdate: $("#startdate").val(),
                enddate: $("#enddate").val(),
                sort: params.sort
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "单据编号", field: "number"}
            , {title: "操作人", field: "executor"}
            , {title: "创建日期", field: "date"}
            , {
                title: '查看详细',
                align: 'center',
                sortable: true,
                formatter: operateFormatter2,
                events: 'operateEvents2'
            }
        ]
    });
    $('#inventoryTable').bootstrapTable({
        method: 'POST',
        url: 'rest/inentory/searchByName',
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
                search: params.search
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: "序号", formatter: runningFormatter}
            , {title: "材料名称", field: "name", align: 'center', sortable: true}
            , {title: "类型", field: "type", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "单价", field: "price", align: 'center', sortable: true}
            , {title: "金额", field: "amount", align: 'center', sortable: true}
            , {title: "入库时间", field: "time", align: 'center', sortable: true}
        ]
    });
    $('#detailsTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "材料名称", field: "name", align: 'center', sortable: true}
            , {title: "类型", field: "type", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "单价", field: "price", align: 'center', sortable: true}
            , {title: "金额", field: "amount", align: 'center', sortable: true}
        ],
        striped: true
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    function operateFormatter2(value, row, index) {
        return [
            '<a class="edit" href="javascript:void(0)">',
            '查看详细</a>'
        ].join('');
    }

    window.operateEvents2 = {
        'click .edit': function (e, value, row, index) {
            $("#submitData").addClass("hidden");
            $("#dispatchForm").addClass("hidden");
            $('#detailsTable').bootstrapTable('removeAll');
            $('#detailsTable').bootstrapTable("append", row.dispatchdetails);
            $('#dispatchModel').modal({
                backdrop: 'static',
                keyboard: false
            });
            $("#dispatchModel").modal("show");
        }
    }
});
$("#insertTable").click(function () {
    var data = $('#inventoryTable').bootstrapTable('getSelections')[0];
    if (data != null && $("#dispatchForm").valid()) {
        $('#detailsTable').bootstrapTable("append", getInsertData(data));
    }
});

$("#queryDispatch").click(function () {
    $('#dispatchTable').bootstrapTable('refresh');
});
$("#createDispatch").click(function () {
    $('#dispatchModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#dispatchModel").modal("show");
});
$("#submitData").click(function () {
    $.ajax('rest/dispatch', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#dispatchTable').bootstrapTable('refresh');
            $("#dispatchModel").modal("hide");
            $('#detailsTable').bootstrapTable('removeAll');
        },  error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
function getSaveData() {
    var data = {
        number: "",
        dispatchdetails: $("#detailsTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData(org) {
    var data = {
        inventory_id: org.id,
        name: org.name,
        type: org.type,
        manufacturer: org.manufacturer,
        model: org.model,
        price: $("#price").val().trim(),
        amount: org.quantity * org.price,
        quantity: getQuantity(org)
    }
    return data;
}
function getQuantity(org) {
    if ($("#quantity").val().trim() > org.quantity) {
        return org.quantity;
    }
    else {
        return $("#quantity").val().trim();
    }
}
$("#giveupData").click(function () {
    $("#dispatchForm").removeClass("hidden");
    $("#submitData").removeClass("hidden");
    $('#detailsTable').bootstrapTable('removeAll');
    $("#dispatchModel").modal("hide");
});