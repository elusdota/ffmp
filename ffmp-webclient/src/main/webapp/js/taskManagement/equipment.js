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
            $('#equipmentTable').bootstrapTable({
                //height: 350,
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
        }, error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
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
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
});
function getSaveData() {
    var data = {
        id:$("#id").val().trim(),
        equipments: $("#equipmentTable").bootstrapTable("getData")
    }
    return data;
}
function getInsertData() {
    var data = {
        name: $("#equipumentname").val().trim(),
        type: $("#type").val().trim(),
        manufacturer: $("#manufacturer").val().trim(),
        model: $("#model").val().trim(),
        quantity: $("#quantity").val().trim()
    }
    return data;
}