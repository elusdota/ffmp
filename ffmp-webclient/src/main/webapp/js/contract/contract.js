/**
 * Created by suelmer on 2016/7/15.
 */

;$(function(){
    var $contractTable = $("#contractTable");

    $contractTable.bootstrapTable({
        method: 'POST',
        url: 'rest/contract/findAll',
        striped: true,
        singleSelect: true,
        queryParams: function (params) {
            var para = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: params.search
            };
            return JSON.stringify(para);
        },
        columns: [
            {
                field: 'state', checkbox: true
            },
            {title: "序号", formatter: runningFormatter},
            {title: "合同名称", field: "name", align: 'center', sortable: true},
            {title: "客户名称", field: "customer.name", align: 'center', sortable: true},
            {title: "负责人", field: "manager", align: 'center', sortable: true},
            {title: "负责人电话", field: "managerTel", align: 'center', sortable: true},
            {title: "付款方式", field: "payment", align: 'center', sortable: true},
            {title: "经办人", field: "agent", align: 'center', sortable: true},
            {title: "合同地址", field: "address", align: 'center', sortable: true},
            {title: "合同金额", field: "amount", align: 'center', sortable: true},
            {title: "合同类别", field: "contractType", align: 'center', sortable: true},
            {title: "税号", field: "TaxNO", align: 'center', sortable: true},
            {title: "效期", field: "expiry", align: 'center', sortable: true},
            {title: "合同内容", field: "content", align: 'center', sortable: true},
            {title: "创建时间", field: "createTime", align: 'center', sortable: true}

        ]
    });

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

    $("#createContract").click(function () {
        $("#main-content").fadeOut(function () {
            $("#main-content").load("contract/contractForm", function () {
                $("#main-content").fadeIn();
            });
        });
    });
});