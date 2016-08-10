/**
 * Created by jiangliang on 2016/6/28.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $("#viewInbounds").attr("disabled", "true");
    $('#inboundsTable').bootstrapTable({
        method: 'POST',
        url: 'rest/inbounds/findAll',
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
            $("#viewInbounds").removeAttr("disabled");
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

})
;
$("#createInbounds").click(function () {
    document.getElementById("inboundsForm").reset();
    $('#inboundsModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#inboundsModel").modal("show");
});
$("#viewInbounds").click(function () {
    var row = $('#inboundsTables').bootstrapTable('getSelections')[0];
    $("#submitData").addClass("hidden");
    $("#inboundsForm").addClass("hidden");
    $('#detailsTable').bootstrapTable('removeAll');
    $('#detailsTable').bootstrapTable("append", row.inboundsdetails);
    $('#inboundsModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#inboundsModel").modal("show");
});
$("#queryInbounds").click(function () {
    $('#inboundsTable').bootstrapTable('refresh');
});
$("#insertTable").click(function () {
    if ($("#inboundsForm").valid()) {
        $('#detailsTable').bootstrapTable("append", getInsertData());
    }
});
$("#submitData").click(function () {
    $.ajax('rest/inbounds', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#inboundsTable').bootstrapTable('refresh');
            document.getElementById("inboundsForm").reset();
            $("#inboundsModel").modal("hide");
            $('#detailsTable').bootstrapTable('removeAll');
        },  error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
$("#giveupData").click(function () {
    $("#inboundsForm").removeClass("hidden");
    $("#submitData").removeClass("hidden");
    $('#detailsTable').bootstrapTable('removeAll');
    document.getElementById("inboundsForm").reset();
    $("#inboundsModel").modal("hide");
});
function getSaveData() {
    var data = {
        number: "",
        inboundsdetails: $("#detailsTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#name").val().trim(),
        type: $("#type").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        model: $("#model").val().trim(),
        price: $("#price").val().trim(),
        amount: $("#price").val().trim() * $("#quantity").val().trim(),
        quantity: $("#quantity").val().trim()
    }
    return data;
}