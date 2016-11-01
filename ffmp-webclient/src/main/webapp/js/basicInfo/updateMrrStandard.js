/**
 * 更新维管标准或是删除维管标准
 * Created by suelmer on 2016/10/31.
 */
;
$(function () {
    var $mrrstandardTable = $('#mrrstandardTable');
    var $techniqueTable = $('#techniqueTable');

    $mrrstandardTable.on('check.bs.table', function ($element, row) {
        if (row.mrrMethod == null) {
            $("#updateMrrStandardName").removeAttr("disabled");
            $("#deleteMrrStandardName").removeAttr("disabled");
            $("#updateMrrStandard").attr("disabled", "disabled");
            $("#deleteMrrStandard").attr("disabled", "disabled");
        } else {
            $("#updateMrrStandardName").attr("disabled", "disabled");
            $("#deleteMrrStandardName").attr("disabled", "disabled");
            $("#updateMrrStandard").removeAttr("disabled");
            $("#deleteMrrStandard").removeAttr("disabled");
        }
    });
    $mrrstandardTable.on('uncheck.bs.table', function ($element, row) {
        if (row.mrrMethod == null) {
            $("#updateMrrStandardName").attr("disabled", "disabled");
            $("#deleteMrrStandardName").attr("disabled", "disabled");
        } else {
            $("#updateMrrStandard").attr("disabled", "disabled");
            $("#deleteMrrStandard").attr("disabled", "disabled");
        }
    });

    var $createMrrStandardNameModal = $("#createMrrStandardNameModal").modal({
        show: false
    });

    var $createMrrStandardModal = $("#createMrrStandardModal").modal({
        show: false
    });

    $("#updateMrrStandardName").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        var parent = getMrrStandardParent(selectRow[0].id);
        if (selectRow[0].mrrMethod == null) {
            $("#code").val(selectRow[0].code);
            $("#name").val(selectRow[0].name);

            $("#parent_code").val(parent.code);
            $("#parent_name").val(parent.name);

            $createMrrStandardNameModal.find('.modal-title').text($(this).text());
            $createMrrStandardNameModal.modal({
                backdrop: 'static',
                keyboard: false,
                show: true
            });
        }
    });

    $("#updateMrrStandard").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        var parent = getMrrStandardParent(selectRow[0].id);
        if (selectRow[0].mrrMethod != null) {
            $("#parent_code_content").val(parent.code);
            $("#parent_name_content").val(parent.name);

            $("#code_content").val(selectRow[0].code);
            $("#name_content").val(selectRow[0].name);
            $("#mrrMethod").val(selectRow[0].mrrMethod);
            $("#lifetime").val(selectRow[0].lifetime);
            $("#changetime").val(selectRow[0].changetime);
            $("#maturity").val(selectRow[0].maturity);
            $("#jobContent").val(selectRow[0].jobContent);
            $("#proportion").val(selectRow[0].proportion);
            $("#remark").val(selectRow[0].remark);
            $("#inspection").val(selectRow[0].inspection);
            $techniqueTable.bootstrapTable("load",selectRow[0].techniqueRequirementsList);
            $createMrrStandardModal.find('.modal-title').text($(this).text());
            $createMrrStandardModal.modal({
                backdrop: 'static',
                keyboard: false,
                show: true
            });
        }
    });
    $("#deleteMrrStandardName").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');

        if(selectRow[0].children.length == 0){
            deleteMrrStandard();
        }else{
            $.messager.alert(selectRow[0].name+" 有子目录，不能删除！");
        }

    });
    $("#deleteMrrStandard").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');

        if(selectRow[0].children.length == 0){
            deleteMrrStandard();
        }else{
            $.messager.alert(selectRow[0].name+" 有子目录，不能删除！");
        }
    });
    function deleteMrrStandard(){
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        $.ajax('rest/mrrstandard/delete?id=' + selectRow[0].id, {
            type: 'DELETE',
            success: function (data, XMLHttpRequest, jqXHR) {
                $mrrstandardTable.bootstrapTable('refresh');
                $.messager.alert("删除成功！");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }

    function getMrrStandardParent(id){
        var parentMrrStandard={};
        $.ajax('rest/mrrstandard/parent?id=' +id, {
            type: 'get',
            success: function (data, XMLHttpRequest, jqXHR) {
                parentMrrStandard =data;
            }, error: function (XMLHttpRequest) {
                console.log("加载上一级维管标准出错");
            }
        });
        return parentMrrStandard;
    }


    //更新设施名称
    $("#createMrrStandardNameBtn").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        if ($("#mrrStandardNameForm").valid()) {
            var $pcode = $("#pcode");
            var mrrStandardVal = {
                code: $pcode.text() == "" ?  $("#code").val().trim() : $pcode.text() +  $("#code").val().trim(),
                name: $("#name").val().trim()
            };
            var mrrStandardInfo = {
                parent: selectRow[0],
                mrrStandard: mrrStandardVal
            };
            console.log(mrrStandardVal.code);
            $.ajax('rest/mrrstandard/update', {
                type: 'PUT',
                data: JSON.stringify(mrrStandardInfo),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $mrrstandardTable.bootstrapTable('refresh');
                    $("#pcode").text("");
                    $("#resetMrrStandardName").trigger("click");
                    $("#createMrrStandardNameModal").modal("hide");
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    //更新维护项目
    $("#createMrrStandardBtn").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        if ($("#mrrStandardForm").valid()) {
            var techniqueVal = $techniqueTable.bootstrapTable("getData");
            var $pcode = $("#pcode_content");
            var mrrStandardVal = {
                code: $pcode.text() == "" ?  $("#code_content").val().trim() : $pcode.text() +  $("#code_content").val().trim(),
                name: $("#name_content").val().trim(),
                mrrMethod: $("#mrrMethod").val().trim(),
                lifetime:$("#lifetime").val().trim(),
                changetime:$("#changetime").val().trim(),
                maturity:$("#maturity").val().trim(),
                jobContent: $("#jobContent").val().trim(),
                proportion: $("#proportion").val().trim(),
                remark: $("#remark").val().trim(),
                inspection: $("#inspection").val().trim(),
                techniqueRequirementsList: techniqueVal instanceof Array ? techniqueVal : []
            };
            var mrrStandardInfo = {
                parent: selectRow[0],
                mrrStandard: mrrStandardVal
            };
            console.log(mrrStandardVal.code);
            $.ajax('rest/mrrstandard/update', {
                type: 'PUT',
                data: JSON.stringify(mrrStandardInfo),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $mrrstandardTable.bootstrapTable('refresh');
                    $("#pcode_content").text("");
                    $("#resetMrrStandard").trigger("click");
                    $("#createMrrStandardModal").modal("hide");
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
});