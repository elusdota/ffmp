/**
 * 创建/修改合同信息
 * Created by suelmer on 2016/7/18.
 */
;$(function(){
    var $paymentTable = $("#paymentTable");
    $paymentTable.bootstrapTable({
        columns: [
            {title: "期数", field: "period", align: 'center',formatter: runningFormatter},
            {title: "付款时间", field: "paymentDate", align: 'center', sortable: true},
            {title: "票据", field: "receipt", align: 'center', sortable: true},
            {title: "确认收款", field: "confirmation", align: 'center', sortable: true},
            {
                title: '操作',
                align: 'center',
                events: 'paymentOperateEvents',
                formatter: paymentOperateFormatter
            }
        ],
        striped: true
    });

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
    function paymentOperateFormatter(value, row, index) {
        return [
            '<a class="remove" href="javascript:void(0)" title="移除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }
    window.paymentOperateEvents = {
        'click .remove': function (e, value, row, index) {
            $techniqueTable.bootstrapTable('remove', {
                field: 'period',
                values: [row.period]
            });
        }
    };

    $("#addPaymentMethod").click(function () {
        function getPaymentMethodData() {
            return {
                paymentDate: $("#paymentDate").val().trim(),
                receipt: $("#receipt").val().trim(),
                confirmation: $("input[name='confirmation']:checked").val().trim()
            };
        }

        if ($("#paymentMethodForm").valid()) {
            $paymentTable.bootstrapTable("append", getPaymentMethodData());
            $("#resetPaymentMethod").trigger("click");
        }
    });
});