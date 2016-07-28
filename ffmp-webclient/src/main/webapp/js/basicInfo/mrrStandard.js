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
        columns: [
            {
                field: 'state', checkbox: true
            },
            {title: '序号', formatter: runningFormatter},
            {title: "编码", field: "code", sortable: true},
            {title: "系统/设施名称/维护管理项目", field: "name", sortable: true},
            {title: "维管方式", field: "mrrMethod", sortable: true},
            {title: "工作内容", field: "jobContent", sortable: true},
            {title: "抽查比例", field: "proportion", sortable: true},
            {title: "备注", field: "remark", sortable: true},
            {
                title: '查看详细',
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
            '<a class="edit" href="javascript:void(0)"><i class="glyphicon glyphicon-eye-open"></i> 查看详细</a>'
        ].join('');
    }

    window.operateEvents={
        'click .edit': function (e, value, row, index) {

        }
    };

    $("#createMrrStandard").click(function () {
        //document.getElementById("inboundsForm").reset();
        //$('#inboundsModel').modal({
        //    backdrop: 'static',
        //    keyboard: false
        //});
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
});