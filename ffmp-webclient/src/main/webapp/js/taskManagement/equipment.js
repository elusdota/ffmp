/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var id = $("#id").val().trim();
    getType();
    $('#equipmentTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "设备名称", field: "name", align: 'center', sortable: true}
            , {title: "大类", field: "typemax", align: 'center', sortable: true}
            , {title: "小类", field: "typemin", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "生产日期", field: "productionDate", align: 'center', sortable: true}
            , {title: "投入使用日期", field: "inputDate", align: 'center', sortable: true}
            , {title: "状态", field: "nowstate", align: 'center', sortable: true}
        ],
        striped: true
    });
    $.ajax('rest/maintenanceProject?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
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
$("#insertTable").click(function () {
    if ($("#equipumentForm").valid()) {
        $('#equipmentTable').bootstrapTable("append", getInsertData());
    }
});
$("#save").click(function () {
    $.ajax('rest/maintenanceProject', {
        type: 'PUT',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#main-content").load("taskManagement/maintenanceProject", function () {
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
        equipments: $("#equipmentTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#equipumentname").val().trim(),
        typemax: $("#typemax").val().trim(),
        typemin: $("#typemin").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        model: $("#model").val().trim(),
        quantity: $("#quantity").val().trim(),
        productionDate: $("#productionDate").val().trim(),
        nowstate: $("#nowstate").val().trim(),
        inputDate: $("#inputDate").val().trim()
    }
    return data;
}
function getType() {
    $.ajax("rest/mrrstandard/findRoot", {
        type: 'GET',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var selectlist = data;
            if (data != null) {
                for (i = 0; i < selectlist.length; i++) {
                    $("#typemax").append("<option id='" + data[i].id + "' value='" + data[i].name + "'>" + data[i].name + "</option>");
                }
                type_onChange();
            }
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
}
function type_onChange() {
    $.ajax("rest/mrrstandard/findOneByName?name=" + $("#typemax").val().trim(), {
        type: 'GET',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var selectlist = data;
            $("#typemin").empty();
            if (data != null) {
                for (i = 0; i < selectlist.length; i++) {
                    $("#typemin").append("<option id='" + data[i].id + "' value='" + data[i].name + "'>" + data[i].name + "</option>");
                }
            }
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
}