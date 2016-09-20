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
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#printCode").click(function () {
    var row = $('#equipmentTable').bootstrapTable('getSelections')[0];
    $("#imgbarcode").html("<img src="+"barcode?fmt=JPEG&msg="+row.code+">");
    //$("#imgbarcode").load("barcode?fmt=JPEG&msg="+row.code, function () {
    //    //$("#main-content").fadeIn();
    //});
    $('#barcodeModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#barcodeModel").modal("show");
});

$("#closemodel").click(function () {
    $("#barcodeModel").modal("hide");
});