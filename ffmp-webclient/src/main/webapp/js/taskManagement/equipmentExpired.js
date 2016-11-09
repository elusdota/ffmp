/**
 * Created by jiangliang on 2016/11/9.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    $('#equipmentTable').bootstrapTable({
        method: 'POST',
        url: 'rest/equipment/findExpired',
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
                search: id
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "设备名称", field: "equipment.name", align: 'center', sortable: true}
            , {title: "设备编码", field: "equipment.code", align: 'center', sortable: true}
            , {title: "大类", field: "equipment.typemax", align: 'center', sortable: true}
            , {title: "小类", field: "equipment.typemin", align: 'center', sortable: true}
            , {title: "厂家", field: "equipment.manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "equipment.model", align: 'center', sortable: true}
            , {title: "数量", field: "equipment.quantity", align: 'center', sortable: true}
            , {title: "生产日期", field: "equipment.productionDate", align: 'center', sortable: true}
            , {title: "投入使用日期", field: "equipment.inputDate", align: 'center', sortable: true}
            , {title: "位置", field: "equipment.location", align: 'center', sortable: true}
            , {title: "状态", field: "equipment.nowstate", align: 'center', sortable: true}
            , {title: "检修日期", field: "expired", align: 'center', sortable: true}
            , {title: "报废日期", field: "scrap", align: 'center', sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

});