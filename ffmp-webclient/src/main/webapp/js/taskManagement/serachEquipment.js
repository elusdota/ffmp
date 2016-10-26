/**
 * Created by jiangliang on 2016/8/10.
 */
$(document).ready(function () {
    $("#printCode").attr("disabled", "true");
    $('#equipmentTable').bootstrapTable({
        method: 'POST',
        url: 'rest/equipment/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#printCode").removeAttr("disabled");
        },
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
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "设备名称", field: "name", align: 'center', sortable: true}
            , {title: "设备编码", field: "code", align: 'center', sortable: true}
            , {title: "大类", field: "typemax", align: 'center', sortable: true}
            , {title: "小类", field: "typemin", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "生产日期", field: "productionDate", align: 'center', sortable: true}
            , {title: "投入使用日期", field: "inputDate", align: 'center', sortable: true}
            , {title: "位置", field: "location", align: 'center', sortable: true}
            , {title: "状态", field: "nowstate", align: 'center', sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#printCode").click(function () {
    var row = $('#equipmentTable').bootstrapTable('getSelections')[0];
    $("#imgbarcode").html("<img src="+"barcode?fmt=JPEG&msg="+row.code+" height='68px' width='190px'>");
    //$("#imgbarcode").load("barcode?fmt=JPEG&msg="+row.code, function () {
    //    //$("#main-content").fadeIn();
    //});,top=0,left=0
    $('#barcodeModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#barcodeModel").modal("show");
    var newstr = document.all.item("imgbarcode").innerHTML;
    printWindow = window.open("","","width=450px,height=600px");
    var name =row.name;
    var labelName="<table><tr><td style='text-align:center'><label>"+name+"</label></td></tr><tr> <td>";
    printWindow.document.write(getStyle() +"<div class='form-group' id='imgbarcode'>"+labelName+newstr+"</td></tr> </table></div>");
    //printWindow.document.write(getStyle()+newstr);
    printWindow.print();
    printWindow.close();
});
function getStyle(){
    var style= "<style type='text/css'  media='print'> "+ "@page {margin: 0mm;width=42mm,height=60mm}"+
        "html {background-color: #fff8f8;margin: 0px;}"+
        "body {border: solid 0px blue;margin: 0mm 0mm 0mm 0mm;}"+"</style>";
    return style;
}
$("#closemodel").click(function () {
    $("#barcodeModel").modal("hide");
});