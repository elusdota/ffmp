/**
 * Created by jiangliang on 2016/6/28.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $('#purchasesTable').bootstrapTable({
        method: 'POST',
        url: 'rest/purchases/findAll',
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
    $('#detailsTable').bootstrapTable({
        //height: 300,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "材料名称", field: "name", align: 'center', sortable: true}
            , {title: "类型", field: "type", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
        ],
        striped: true
    });
    function operateFormatter2(value, row, index) {
        return [
            '<a class="edit" href="javascript:void(0)">',
            '查看详细</a>'
        ].join('');
    }

    window.operateEvents2 = {
        'click .edit': function (e, value, row, index) {
            $("#submitData").addClass("hidden");
            $("#purchasesForm").addClass("hidden");
            $('#detailsTable').bootstrapTable('removeAll');
            $('#detailsTable').bootstrapTable("append", row.purchasesdetails);
            $('#purchasesModel').modal({
                backdrop: 'static',
                keyboard: false
            });
            $("#purchasesModel").modal("show");
        }
    }
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

});
$("#queryPurchases").click(function () {
    $('#purchasesTable').bootstrapTable('refresh');
});
$("#createPurchases").click(function () {
    document.getElementById("purchasesForm").reset();
    $('#purchasesModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#purchasesModel").modal("show");
});
$("#insertTable").click(function () {
    if ($("#purchasesForm").valid()) {
        $('#detailsTable').bootstrapTable("append", getInsertData());
    }
});
$("#submitData").click(function () {
    $.ajax('rest/purchases', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#purchasesTable').bootstrapTable('refresh');
            document.getElementById("purchasesForm").reset();
            $("#purchasesModel").modal("hide");
            $('#detailsTable').bootstrapTable('removeAll');
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
$("#giveupData").click(function () {
    $("#purchasesForm").removeClass("hidden");
    $("#submitData").removeClass("hidden");
    document.getElementById("purchasesForm").reset();
    $("#purchasesModel").modal("hide");
    $('#detailsTable').bootstrapTable('removeAll');
});
function getSaveData() {
    var data = {
        number: "",
        purchasesdetails: $("#detailsTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#name").val().trim(),
        type: $("#type").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        model: $("#model").val().trim(),
        quantity: $("#quantity").val().trim()
    }
    return data;
}
function getManufacturer() {
    $.ajax("rest/Agent/getAll", {
        type: 'GET',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var selectlist = data;
            if (data != null) {
                for (i = 0; i < selectlist.length; i++) {
                    $("#manufacturer").append("<option id='" + data[i].id + "' value='" + data[i].name + "'>" + data[i].name + "</option>");

                }
            }
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
}