/**
 * Created by jiangliang on 2016/8/15.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $("#viewOverflow").attr("disabled", "true");
    $('#overflowTable').bootstrapTable({
        method: 'POST',
        url: 'rest/overflow/findAll',
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
        onCheck: function (row) {
            $("#viewOverflow").removeAttr("disabled");
        },
        onUncheck: function (row) {
            $("#viewOverflow").attr("disabled", "true");
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "单据编号", field: "number", sortable: true}
            , {title: "操作人", field: "executor", sortable: true}
            , {title: "创建日期", field: "date", sortable: true}
        ]
    });
    $('#detailsTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "名称", field: "name", align: 'center', sortable: true}
            , {title: "入库类型", field: "inventoryType", align: 'center', sortable: true}
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

})
;
$("#createOverflow").click(function () {
    document.getElementById("overflowForm").reset();
    $('#overflowModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#overflowModel").modal("show");
});
$("#viewOverflow").click(function () {
    var row = $('#overflowTable').bootstrapTable('getSelections')[0];
    $("#submitData").addClass("hidden");
    $("#overflowForm").addClass("hidden");
    $('#detailsTable').bootstrapTable('removeAll');
    $('#detailsTable').bootstrapTable("append", row.overflowdetails);
    $('#overflowModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#overflowModel").modal("show");
});
$("#queryOverflow").click(function () {
    $('#overflowTable').bootstrapTable('refresh');
});
$("#insertTable").click(function () {
    if ($("#overflowForm").valid()) {
        $('#detailsTable').bootstrapTable("append", getInsertData());
    }
});
$("#submitData").click(function () {
    $.ajax('rest/overflow', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#overflowTable').bootstrapTable('refresh');
            document.getElementById("overflowForm").reset();
            $("#overflowModel").modal("hide");
            $('#detailsTable').bootstrapTable('removeAll');
        },  error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
$("#giveupData").click(function () {
    $("#overflowForm").removeClass("hidden");
    $("#submitData").removeClass("hidden");
    $('#detailsTable').bootstrapTable('removeAll');
    document.getElementById("overflowForm").reset();
    $("#overflowModel").modal("hide");
});
function getSaveData() {
    var data = {
        number: "",
        overflowdetails: $("#detailsTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#name").val().trim(),
        type: $("#type").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        inventoryType:$("#inventoryType").val().trim(),
        model: $("#model").val().trim(),
        price: $("#price").val().trim(),
        amount: $("#price").val().trim() * $("#quantity").val().trim(),
        quantity: $("#quantity").val().trim()
    }
    return data;
}