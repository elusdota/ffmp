/**
 * 设施维管标准
 * Created by suelmer on 2016/7/16.
 */

;
$(function () {

    $('#mrrstandardTable').bootstrapTable({
        method: 'POST',
        url: 'rest/mrrstandard/findAll',
        striped: true,
        singleSelect: true,
        clickToSelect: true,
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
            {title: "维管方式", field: "mrrMethod", sortable: true},
            {title: "工作内容", field: "jobContent", sortable: true},
            {title: "抽查比例", field: "proportion", sortable: true},
            {title: "备注", field: "remark", sortable: true},
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
    function operateFormatter(){
        return [
            '<a class="edit" href="javascript:void(0)"><i class="glyphicon glyphicon-eye-open"></i> 技术要求</a>'
        ].join('');
    }

    window.operateEvents={
        'click .edit': function (e, value, row, index) {

        }
    };

    $("#createMrrStandard").click(function () {
        $("#resetTechnique").trigger("click");
        $('#techniqueTable').bootstrapTable("removeAll");
        $("#createMrrStandardModal").modal("show");
    });

    $('#techniqueTable').bootstrapTable({
        columns: [
            {title: "序号", formatter: runningFormatter} ,
            {title: "名称", field: "name", align: 'center', sortable: true} ,
            {title: "类型", field: "type", align: 'center', sortable: true} ,
            {title: "描述", field: "description", align: 'center', sortable: true}
        ],
        striped: true
    });

    $("#addTechnique").click(function(){
        function getTechniqueData() {
            return {
                name: $("#tName").val().trim(),
                type: $("#tType").val().trim(),
                description: $("#description").val().trim()
            };
        }

        if ($("#techniqueForm").valid()) {
            $('#techniqueTable').bootstrapTable("append", getTechniqueData());
            $("#resetTechnique").trigger("click");
        }
    });

    $("#createMrrStandardBtn").click(function(){
        if($("#mrrStandardForm").valid()){
            var selectRow = $('#mrrstandardTable').bootstrapTable('getSelections');
            var mrrStandardVal = {
                code: $("#code").val().trim(),
                name: $("#name").val().trim(),
                mrrMethod: $("#mrrMethod").val().trim(),
                jobContent: $("#jobContent").val().trim(),
                proportion: $("#proportion").val().trim(),
                remark: $("#remark").val().trim(),
                techniqueRequirementsList:$("#techniqueTable").bootstrapTable("getData")
            };
            var mrrStandardInfo={
                parent: selectRow[0],
                mrrStandard :mrrStandardVal
            };
            console.log("---------data----"+JSON.stringify(mrrStandardInfo));
            $.ajax('rest/mrrstandard/create', {
                type: 'POST',
                data: JSON.stringify(mrrStandardInfo),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $('#mrrstandardTable').bootstrapTable('refresh');
                    $("#resetMrrStandard").trigger("click");
                    $("#createMrrStandardModal").modal("hide");
                },  error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
});