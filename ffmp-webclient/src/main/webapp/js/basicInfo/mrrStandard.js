/**
 * 设施维管标准
 * Created by suelmer on 2016/7/16.
 */
;
$(function () {
    //$("[data-mask]").inputmask();
    var $mrrstandardTable = $('#mrrstandardTable');
    var $techniqueTable = $('#techniqueTable');
    var $lookTechniqueTable = $('#lookTechniqueTable');
    $mrrstandardTable.bootstrapTable({
        method: 'POST',
        url: 'rest/mrrstandard/findAll',
        striped: true,
        //  clickToSelect: true,
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: params.search
            };
            return JSON.stringify(fin);
        },
        columns: [
            {
                field: 'state', checkbox: true
            },
            {title: '序号', formatter: runningFormatter},
            {title: "编码", field: "code", sortable: true},
            {title: "系统/设施名称", field: "name", sortable: true},
            {title: "工作内容", field: "jobContent", sortable: true},
            {
                title: '查看技术要求',
                align: 'center',
                sortable: true,
                formatter: operateFormatter,
                events: 'operateEvents'
            }
        ]
    });

    function runningFormatter(value, row, index) {
        return index + 1;
    }

    function operateFormatter(value, row, index) {
        if (row.jobContent == null) {
            return "";
        } else {
            return [
                '<a class="edit" href="javascript:void(0)"><i class="glyphicon glyphicon-eye-open"></i> 技术要求</a>'
            ].join('');
        }
    }

    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
            var $modal = $("#techniqueModal").modal({show: false});
            $lookTechniqueTable.bootstrapTable("load",row.techniqueRequirementsList);
            $modal.modal('show');
        }
    };


    $lookTechniqueTable.bootstrapTable({
        columns: [
            {title: "序号", formatter: runningFormatter},
            {title: "检查内容", field: "name", align: 'center', sortable: true},
            {title: "性质类别", field: "type", align: 'center', sortable: true},
            {title: "技术规范", field: "description", align: 'center', sortable: true},
            {title: "维保方式", field: "mrrMethod", align: 'center', sortable: true},
            {title: "抽查比例(%)", field: "proportion", align: 'center', sortable: true},
            {title: "检查方法", field: "inspection", align: 'center', sortable: true},
            {title: "使用年限", field: "lifetime", align: 'center', sortable: true},
            {title: "检修年限", field: "changetime", align: 'center', sortable: true},
            {title: "期限类型", field: "maturity", align: 'center', sortable: true}
        ]

    });
    //创建设施名称
    $("#createMrrStandardName").click(function () {
        $("#resetMrrStandardName").trigger("click");
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        if (selectRow.length) {
            $("#parent_code").val(selectRow[0].code);
            $("#pcode").text(selectRow[0].code + "-");
            $("#parent_name").val(selectRow[0].name);
        }
        $("#createMrrStandardNameModal").modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });

    //创建维护管理项目
    $("#createMrrStandard").click(function () {
        $("#resetTechnique").trigger("click");
        $("#resetMrrStandard").trigger("click");
        $techniqueTable.bootstrapTable("removeAll");
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        if (selectRow.length) {
            $("#parent_code_content").val(selectRow[0].code);
            $("#pcode_content").text(selectRow[0].code + "-");
            $("#parent_name_content").val(selectRow[0].name);
        }
        $("#createMrrStandardModal").modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
    });

    //检查设施名称编号是否重复
    $("#code").blur(function () {
        var $pcode = $("#pcode");
        var code = $pcode.text() == "" ? $("#code").val().trim() : $pcode.text() + $("#code").val().trim();
        var checkResult = checkCodeRepeat(code);
        console.log("--------checkResult---" + checkResult);
        if (checkResult) {
            var html = '<div class="alert alert-warning alert-dismissible" id="codeWarning">' +
                ' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
                '<h5><i class="icon fa fa-warning"></i>编码重复</h5>' +
                '</div>';
            $("#codeDiv").after(html);
        } else {
            $("#codeWarning").hide();
        }

    });

    function checkCodeRepeat(code) {
        var isRepeat = false;
        if (code != "" && code != null) {
            $.ajax('rest/mrrstandard/findOneByCode', {
                type: 'get',
                async: false,
                data: {code: code},
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    console.log("-----------" + data);
                    if (data != null) {
                        isRepeat = true;
                    }
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
        return isRepeat;
    }

    //检查维护管理项目编号是否重复
    $("#code_content").blur(function () {
        var $pcode = $("#pcode_content");
        var code = $pcode.text() == "" ? $("#code_content").val().trim() : $pcode.text() + $("#code_content").val().trim();
        var checkResult = checkCodeRepeat(code);
        if (checkResult) {
            var html = '<div class="alert alert-warning alert-dismissible" id="codeWarning">' +
                ' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
                '<h5><i class="icon fa fa-warning"></i>编码重复</h5>' +
                '</div>';
            $("#codeDiv").after(html);
        } else {
            $("#codeWarning").hide();
        }
    });

    //技术标准
    $techniqueTable.bootstrapTable({
        columns: [
            {title: "序号", formatter: runningFormatter},
            {title: "检查内容", field: "name", align: 'center', sortable: true},
            {title: "性质类别", field: "type", align: 'center', sortable: true},
            {title: "技术规范", field: "description", align: 'center', sortable: true},
            {title: "维保方式", field: "mrrMethod", align: 'center', sortable: true},
            {title: "抽查比例(%)", field: "proportion", align: 'center', sortable: true},
            {title: "检查方法", field: "inspection", align: 'center', sortable: true},
            {title: "使用年限", field: "lifetime", align: 'center', sortable: true},
            {title: "检修年限", field: "changetime", align: 'center', sortable: true},
            {title: "期限类型", field: "maturity", align: 'center', sortable: true},
            {
                title: '操作',
                align: 'center',
                events: 'techniqueOperateEvents',
                formatter: techniqueOperateFormatter
            }
        ],
        striped: true
    });

    function techniqueOperateFormatter(value, row, index) {
        return [
            '<a class="remove" href="javascript:void(0)" title="移除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }

    window.techniqueOperateEvents = {
        'click .remove': function (e, value, row, index) {
            $techniqueTable.bootstrapTable('remove', {
                field: 'description',
                values: [row.tName]
            });
        }
    };
    //添加技术标准
    $("#addTechnique").click(function () {
        function getTechniqueData() {
            return {
                name: $("#tName").val().trim(),
                type: $("#tType").val().trim(),
                description: $("#description").val().trim(),
                mrrMethod: $("#mrrMethod").val().trim(),
                lifetime: $("#lifetime").val().trim(),
                changetime: $("#changetime").val().trim(),
                maturity: $("#maturity").val().trim(),
                proportion: $("#proportion").val().trim(),
                inspection: $("#inspection").val().trim()
            };
        }

        if ($("#techniqueForm").valid()) {
            $techniqueTable.bootstrapTable("append", getTechniqueData());
            $("#resetTechnique").trigger("click");
        }
    });

    //创建设施名称
    $("#createMrrStandardNameBtn").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        var $pcode = $("#pcode");
        var code = $pcode.text() == "" ? $("#code").val().trim() : $pcode.text() + $("#code").val().trim();
        var checkResult = checkCodeRepeat(code);
        if (checkResult) {
            var html = '<div class="alert alert-warning alert-dismissible" id="codeWarning">' +
                ' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
                '<h5><i class="icon fa fa-warning"></i>编码重复</h5>' +
                '</div>';
            $("#codeDiv").after(html);
            return;
        } else {
            $("#codeWarning").hide();
        }

        if ($("#mrrStandardNameForm").valid()) {
            var mrrStandardVal = {
                code: code,
                name: $("#name").val().trim()
            };
            var mrrStandardInfo = {
                parent: selectRow[0],
                mrrStandard: mrrStandardVal
            };
            console.log(mrrStandardVal.code);
            $.ajax('rest/mrrstandard/create', {
                type: 'POST',
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
    //创建维护项目
    $("#createMrrStandardBtn").click(function () {
        var selectRow = $mrrstandardTable.bootstrapTable('getSelections');
        var $pcode = $("#pcode_content");
        var code = $pcode.text() == "" ? $("#code_content").val().trim() : $pcode.text() + $("#code_content").val().trim();
        var checkResult = checkCodeRepeat(code);
        if (checkResult) {
            var html = '<div class="alert alert-warning alert-dismissible" id="codeWarning">' +
                ' <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' +
                '<h5><i class="icon fa fa-warning"></i>编码重复</h5>' +
                '</div>';
            $("#codeDiv").after(html);
            return;
        } else {
            $("#codeWarning").hide();
        }
        if ($("#mrrStandardForm").valid()) {
            var techniqueVal = $techniqueTable.bootstrapTable("getData");
            var mrrStandardVal = {
                code: code,
                name: $("#name_content").val().trim(),
                jobContent: $("#jobContent").val().trim(),
                remark: $("#remark").val().trim(),
                techniqueRequirementsList: techniqueVal instanceof Array ? techniqueVal : []
            };
            var mrrStandardInfo = {
                parent: selectRow[0],
                mrrStandard: mrrStandardVal
            };
            console.log(mrrStandardVal.code);
            $.ajax('rest/mrrstandard/create', {
                type: 'POST',
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