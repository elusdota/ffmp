/**
 * 更新维管标准或是删除维管标准
 * Created by suelmer on 2016/10/31.
 */
;
$(function () {
    var $mrrstandardTable = $('#mrrstandardTable');
    var $lookTechniqueTable = $('#lookTechniqueTable');

    $mrrstandardTable.on('check.bs.table', function ($element, row) {
        $("#updateMrrStandard").removeAttr("disabled");
        $("#deleteMrrStandard").removeAttr("disabled");
    });
    $mrrstandardTable.on('uncheck.bs.table', function ($element, row) {
        $("#updateMrrStandard").removeAttr("disabled");
        $("#deleteMrrStandard").removeAttr("disabled");
    });
    $lookTechniqueTable.on('check.bs.table', function ($element, row) {
        $("#updateTechniqueBtn").removeAttr("disabled");
        $("#deleteTechniqueBtn").removeAttr("disabled");
    });
    $lookTechniqueTable.on('uncheck.bs.table', function ($element, row) {
        $("#updateTechniqueBtn").attr("disabled", "disabled");
        $("#deleteTechniqueBtn").attr("disabled", "disabled");
    });

    var $updateMrrStandardModal = $("#updateMrrStandardModal").modal({
        show: false
    });

    var $updateTechniqueModal = $("#updateTechniqueModal").modal({
        show: false
    });

    $("#updateMrrStandard").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        var parent = getMrrStandardParent(selectRow[0].id);
        var len = -1;
        if(parent !=""){
            len= parent.code.length +1;
            $("#pcode_update").text(parent.code+ "-");
            $("#ParentMRRStandardId").val(parent.id);
        }
        $("#MRRStandardId").val(selectRow[0].id);

        $("#code_update").val(selectRow[0].code.substring(len));
        $("#name_update").val(selectRow[0].name);

        $("#parent_code_update").val(parent.code);
        $("#parent_name_update").val(parent.name);

        if (selectRow[0].jobContent == null) {
            $(".standardCls").hide();
        } else {
            $("#jobContent_update").val(selectRow[0].jobContent);
            $("#remark_update").val(selectRow[0].remark);
            $(".standardCls").show();
        }
        $updateMrrStandardModal.find('.modal-title').text($(this).text());
        $updateMrrStandardModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });
    //更新维护项目
    $("#updateMrrStandardBtn").click(function () {
        if ($("#mrrStandardForm_update").valid()) {
            var $pcode = $("#pcode_update");
            var code = $pcode.text() == "" ? $("#code_update").val().trim() : $pcode.text() + $("#code_update").val().trim();
            var mrrStandardVal = {
                id:$("#MRRStandardId").val().trim(),
                code: code,
                name: $("#name_update").val().trim(),
                jobContent: $("#jobContent_update").val().trim(),
                remark: $("#remark_update").val().trim()
            };
            var mrrStandardInfo = {
                parent: {id: $("#ParentMRRStandardId").val().trim()},
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
                    $("#pcode_update").text("");
                    $("#resetMrrStandard_update").trigger("click");
                    $updateMrrStandardModal.modal("hide");
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });

    $("#deleteMrrStandard").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');

        if (selectRow[0].children.length == 0) {
            deleteMrrStandard();
        } else {
            $.messager.alert(selectRow[0].name + " 有子目录，不能删除！");
        }
    });
    function deleteMrrStandard() {
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

    function getMrrStandardParent(id) {
        var parentMrrStandard = {};
        $.ajax('rest/mrrstandard/parent?id=' + id, {
            type: 'get',
            async: false,
            success: function (data, XMLHttpRequest, jqXHR) {
                parentMrrStandard = data;
            }, error: function (XMLHttpRequest) {
                console.log("加载上一级维管标准出错");
            }
        });
        return parentMrrStandard;
    }

    $("#createTechniqueBtn").click(function () {
        $("#resetTechnique_update").trigger("click");
        $("#techniqueId").val("");
        $updateTechniqueModal.find('.modal-title').text($(this).text());
        $updateTechniqueModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });
    $("#updateTechniqueBtn").click(function () {
        $("#techniqueId").val("");
        var row = $lookTechniqueTable.bootstrapTable('getSelections');
        console.log(row);
        $("#techniqueId").val(row[0].id);
        $("#tName_update").val(row[0].name);
        $("#tType_update").val(row[0].type);
        $("#description_update").val(row[0].description);
        $("#mrrMethod_update").val(row[0].mrrMethod);
        $("#lifetime_update").val(row[0].lifetime);
        $("#changetime_update").val(row[0].changetime);
        $("#maturity_update").val(row[0].maturity);
        $("#proportion_update").val(row[0].proportion);
        $("#inspection_update").val(row[0].inspection);
        $updateTechniqueModal.find('.modal-title').text($(this).text());
        $updateTechniqueModal.modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });
    $("#deleteTechniqueBtn").click(function () {
        var selectRow = $lookTechniqueTable.bootstrapTable('getSelections');
        $.ajax('rest/techniquers/delete?id=' + selectRow[0].id, {
            type: 'DELETE',
            success: function (data, XMLHttpRequest, jqXHR) {
                $lookTechniqueTable.bootstrapTable('remove',{
                    field: 'id',
                    values: [selectRow[0].id]
                });
                $.messager.alert("删除成功！");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    });
    $("#updateSaveTechniqueBtn").click(function () {
        if ($("#techniqueForm_update").valid()) {
            var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
            var info= {
                name: $("#tName_update").val().trim(),
                type: $("#tType_update").val().trim(),
                description: $("#description_update").val().trim(),
                mrrMethod: $("#mrrMethod_update").val().trim(),
                lifetime: $("#lifetime_update").val().trim(),
                changetime: $("#changetime_update").val().trim(),
                maturity: $("#maturity_update").val().trim(),
                proportion: $("#proportion_update").val().trim(),
                inspection: $("#inspection_update").val().trim(),
                mrrStandard:{id:selectRow[0].id}
            };
            console.log(info);
            console.log("techniqueId-----------"+$("#techniqueId").val());
            if( $("#techniqueId").val().trim()!=""){//更新
               info.id= $("#techniqueId").val();
                console.log(info);
                $.ajax('rest/techniquers/update', {
                    type: 'PUT',
                    data: JSON.stringify(info),
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (data, XMLHttpRequest, jqXHR) {
                        $("#techniqueId").val("");
                        $lookTechniqueTable.bootstrapTable('updateRow',{
                            index: 1, row: data
                        });
                        $("#resetTechnique_update").trigger("click");
                        $updateTechniqueModal.modal("hide");
                    }, error: function (XMLHttpRequest) {
                        $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                        $("#message").modal("show");
                    }
                });
            }else{//添加
                $.ajax("rest/techniquers/create", {
                    type: "POST",
                    data: JSON.stringify(info),
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (data, XMLHttpRequest, jqXHR) {

                        $lookTechniqueTable.bootstrapTable('append',data);
                        $("#resetTechnique_update").trigger("click");
                        $updateTechniqueModal.modal("hide");
                    }, error: function (XMLHttpRequest) {
                        $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                        $("#message").modal("show");
                    }
                });
            }
        }

    });
});