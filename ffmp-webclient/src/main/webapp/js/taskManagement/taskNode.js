/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    $.ajax('rest/taskNode?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#delegate").val(data.delegate.name);
            $("#dueDate").val(data.dueDate);
            $('#equipmentTable').bootstrapTable({
                //height: 350,
                data: data.equipments,
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
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});