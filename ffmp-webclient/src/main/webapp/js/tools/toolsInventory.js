/**
 * Created by jiangliang on 2016/6/28.
 */
$(document).ready(function () {
    $('#inventoryTable').bootstrapTable({
        method: 'POST',
        url: 'rest/inentory/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        queryParams: function (params) {
            var fin = {
                name: $("#name").val(),
                type: $("#type").val(),
                model: $("#model").val(),
                inventoryType:'工具',
                manufacturer: $("#manufacturer").val(),
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                pageNumber: params.pageNumber,
                sort: params.sort
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: "序号", formatter: runningFormatter}
            , {title: "工具名称", field: "name", align: 'center', sortable: true}
            , {title: "类型", field: "type", align: 'center', sortable: true}
            , {title: "厂家", field: "manufacturer", align: 'center', sortable: true}
            , {title: "型号", field: "model", align: 'center', sortable: true}
            , {title: "数量", field: "quantity", align: 'center', sortable: true}
            , {title: "单价", field: "price", align: 'center', sortable: true}
            , {title: "金额", field: "amount", align: 'center', sortable: true}
            , {title: "入库时间", field: "time", align: 'center', sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
    $("#queryInventory").click(function () {
        $('#inventoryTable').bootstrapTable('refresh');
    });
});