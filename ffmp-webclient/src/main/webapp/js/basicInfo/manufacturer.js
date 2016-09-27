/**
 * 生产厂商
 * Created by suelmer on 2016/7/12.
 */
;
$(function () {
    var $manufactureTable = $("#manufactureTable");

    $manufactureTable.bootstrapTable({
        method: 'POST',
        url: 'rest/manufacturer/findAll',
        striped: true,
        singleSelect: true,
        clickToSelect: true,
        queryParams: function (params) {
            var para = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: params.search
            };
            return JSON.stringify(para);
        },
        columns: [
            {
                field: 'state', checkbox: true
            },
            {title: "序号", formatter: runningFormatter},
            {title: "生产厂商名称", field: "name", align: 'center', sortable: true},
            {title: "联系人", field: "contact", align: 'center', sortable: true},
            {title: "联系电话", field: "telphone", align: 'center', sortable: true}
        ]
    });

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    $manufactureTable.on('check.bs.table', function (row, $element) {
        $("#updateManufacturer").removeAttr("disabled");
        $("#deleteManufacturer").removeAttr("disabled");
    });
    $manufactureTable.on('uncheck.bs.table', function (row, $element) {
        $("#updateManufacturer").attr("disabled", "disabled");
        $("#deleteManufacturer").attr("disabled", "disabled");
    });
    var $manufacturerModal = $("#manufacturerModal").modal({
        show: false
    });

    $("#createManufacturer").click(function () {
        $("#manufactureForm")[0].reset();
        $manufacturerModal.find('.modal-title').text($(this).text());
        $manufacturerModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });
    $("#updateManufacturer").click(function () {
        var selectRow = $manufactureTable.bootstrapTable('getSelections');
        $("#manfactureId").val(selectRow[0].id);
        $("#name").val(selectRow[0].name);
        $("#contact").val(selectRow[0].contact);
        $("#telphone").val(selectRow[0].telphone);
        $("#manfactureVersion").val(selectRow[0].version);
        $("#manfactureAccessType").val(selectRow[0].accessType);

        $manufacturerModal.find('.modal-title').text($(this).text());
        $manufacturerModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });
    $("#deleteManufacturer").click(function () {
        var selectRow = $manufactureTable.bootstrapTable('getSelections');
        $.ajax('rest/manufacturer/delete?id=' + selectRow[0].id, {
            type: 'DELETE',
            success: function (data, XMLHttpRequest, jqXHR) {
                $manufactureTable.bootstrapTable('refresh');
                $.messager.alert("删除成功！");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    });
    $("#createManufacturerBtn").click(function () {
        if ($("#manufactureForm").valid()) {
            if ($("#manfactureId").val() != "") {
                saveManufacturer(true);
            } else {
                saveManufacturer(false);
            }

        }
    });
    function saveManufacturer(isUpdate) {
        var val = {};
        if (isUpdate) {
            val = {
                name: $("#name").val().trim(),
                contact: $("#contact").val().trim(),
                telphone: $("#telphone").val().trim(),
                id: $("#manfactureId").val().trim(),
                version: $("#manfactureVersion").val().trim(),
                accessType: $("#manfactureAccessType").val().trim()
            };
        }else{
            val = {
                name: $("#name").val().trim(),
                contact: $("#contact").val().trim(),
                telphone: $("#telphone").val().trim()
            };
        }

        $.ajax('rest/manufacturer/save', {
            type: 'POST',
            data: JSON.stringify(val),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $("#manufactureForm")[0].reset();
                $manufactureTable.bootstrapTable('refresh');
                $manufacturerModal.modal("hide");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});