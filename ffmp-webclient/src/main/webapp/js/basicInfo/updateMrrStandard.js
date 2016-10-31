/**
 * 更新维管标准或是删除维管标准
 * Created by suelmer on 2016/10/31.
 */
;
$(function () {
    var $mrrstandardTable = $('#mrrstandardTable');
    var $techniqueTable = $('#techniqueTable');

    $mrrstandardTable.on('check.bs.table', function ($element,row) {
        if(row.mrrMethod == null){
            $("#updateMrrStandardName").removeAttr("disabled");
            $("#deleteMrrStandardName").removeAttr("disabled");
            $("#updateMrrStandard").attr("disabled", "disabled");
            $("#deleteMrrStandard").attr("disabled", "disabled");
        }else{
            $("#updateMrrStandardName").attr("disabled", "disabled");
            $("#deleteMrrStandardName").attr("disabled", "disabled");
            $("#updateMrrStandard").removeAttr("disabled");
            $("#deleteMrrStandard").removeAttr("disabled");
        }
    });
    $mrrstandardTable.on('uncheck.bs.table', function ($element,row) {
        if(row.mrrMethod == null){
            $("#updateMrrStandardName").attr("disabled", "disabled");
            $("#deleteMrrStandardName").attr("disabled", "disabled");
        }else{
            $("#updateMrrStandard").attr("disabled", "disabled");
            $("#deleteMrrStandard").attr("disabled", "disabled");
        }
    });
});