/**
 * Created by suelmer on 2016/7/17.
 */

;$(function(){
    var $keyPartStandardTable = $("#keyPartStandardTable");

    $keyPartStandardTable.bootstrapTable({
        method: 'POST',
        url: 'rest/keyPartStandard/findAll',
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
            {title: "重点部位名称", field: "name", align: 'center', sortable: true},
            {title: "维护管理内容", field: "mrrContent", align: 'center', sortable: true},
            {title: "维管方式", field: "mrrMethod", align: 'center', sortable: true},
            {title: "抽查比例", field: "proportion", align: 'center', sortable: true},
            {title: "修改控制", field: "control", align: 'center', sortable: true}
        ]
    });

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    $keyPartStandardTable.on('check.bs.table', function (row, $element) {
        $("#updateKeyPartStandard").removeAttr("disabled");
        $("#deleteKeyPartStandard").removeAttr("disabled");
    });
    $keyPartStandardTable.on('uncheck.bs.table', function (row, $element) {
        $("#updateKeyPartStandard").attr("disabled", "disabled");
        $("#deleteKeyPartStandard").attr("disabled", "disabled");
    });

    var $keyPartStandardModal = $("#keyPartStandardModal").modal({
        show: false
    });

    $("#createKeyPartStandard").click(function () {
        $("#keyPartStandardForm")[0].reset();
        $keyPartStandardModal.find('.modal-title').text($(this).text());
        $keyPartStandardModal.modal({
            backdrop: 'static',
            keyboard: false,
            show:true
        });
    });

    $("#updateKeyPartStandard").click(function () {
        var selectRow = $keyPartStandardTable.bootstrapTable('getSelections');
        $("#keyPartStandardId").val(selectRow[0].id);
        $("#name").val(selectRow[0].name);
        $("#mrrContent").val(selectRow[0].mrrContent);
        $("#mrrMethod").val(selectRow[0].mrrMethod);
        $("#proportion").val(selectRow[0].proportion);
        $("#control").val(selectRow[0].control);
        $("#keyPartStandardVersion").val(selectRow[0].version);
        $("#keyPartStandardAccessType").val(selectRow[0].accessType);

        $keyPartStandardModal.find('.modal-title').text($(this).text());
        $keyPartStandardModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });

    $("#deleteKeyPartStandard").click(function () {
        var selectRow = $keyPartStandardTable.bootstrapTable('getSelections');
        $.ajax('rest/keyPartStandard/delete?id=' + selectRow[0].id, {
            type: 'DELETE',
            success: function (data, XMLHttpRequest, jqXHR) {
                $keyPartStandardTable.bootstrapTable('refresh');
                alert("删除成功！");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    });

    $("#createKeyPartStandardBtn").click(function () {
        if ($("#keyPartStandardForm").valid()) {
            if ($("#keyPartStandardId").val() != "") {
                saveKeyPartStandard(true);
            } else {
                saveKeyPartStandard(false);
            }

        }
    });

    function saveKeyPartStandard(isUpdate) {
        var val = {};
        if (isUpdate) {
            val = {
                name: $("#name").val().trim(),
                mrrContent: $("#mrrContent").val().trim(),
                mrrMethod: $("#mrrMethod").val().trim(),
                proportion: $("#proportion").val().trim(),
                control: $("#control").val().trim(),
                id: $("#keyPartStandardId").val().trim(),
                version: $("#keyPartStandardVersion").val().trim(),
                accessType: $("#keyPartStandardAccessType").val().trim()
            };
        }else{
            val = {
                name: $("#name").val().trim(),
                mrrContent: $("#mrrContent").val().trim(),
                mrrMethod: $("#mrrMethod").val().trim(),
                proportion: $("#proportion").val().trim(),
                control: $("#control").val().trim(),
            };
        }

        $.ajax('rest/keyPartStandard/save', {
            type: 'POST',
            data: JSON.stringify(val),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $("#keyPartStandardForm")[0].reset();
                $keyPartStandardTable.bootstrapTable('refresh');
                $keyPartStandardModal.modal("hide");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});