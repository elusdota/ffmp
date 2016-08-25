/**
 * 创建/修改合同信息
 * Created by suelmer on 2016/7/18.
 */
;
$(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var $paymentTable = $("#paymentTable");
    $paymentTable.bootstrapTable({
        columns: [
            {title: "期数", field: "period", align: 'center', formatter: runningFormatter},
            {title: "付款时间", field: "paymentDate", align: 'center', sortable: true},
            {title: "票据", field: "receipt", align: 'center', sortable: true},
            {title: "确认收款", field: "confirmation", align: 'center', sortable: true, formatter: confirmFormatter},
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
        value = index + 1;
        return value;
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
            $paymentTable.bootstrapTable('remove', {
                field: 'paymentDate',
                values: [row.paymentDate]
            });
        }
    };
    function confirmFormatter(value, row, index) {
        if (value == "true") {
            return "是";
        } else {
            return "否";
        }
    }
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

    $("#customer").select2({
        theme: "bootstrap",
        language: "zh-CN",
        ajax: {
            url: "rest/customer/findByNameLike",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data,
                    pagination: {
                        more: (params.page * 2) < data.length
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // 定义option格式使其工作
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });

    function formatRepo (repo) {
         var markup =  "<option value="+ repo.id + ">"+ repo.name + "</option>";
        return markup;
    }
    function formatRepoSelection (repo) {
        return repo.name || "" ;
    }
    function restForm(){
        $("#resetContract").trigger("click");
        $("#resetPaymentMethod").trigger("click");
        $paymentTable.bootstrapTable("removeAll");
    }
    $("#cancelBtn").click(function () {
        restForm();
        $("#main-content").fadeOut(function () {
            $("#main-content").load("contract/contract", function () {
                $("#main-content").fadeIn();
            });
        });
    });

    $("#createContractdBtn").click(function () {
        if ($("#contractForm").valid()) {
            var paymentTableVal = $paymentTable.bootstrapTable("getData");
            console.log(paymentTableVal);
            var contractVal = {
                name: $("#name").val().trim(),
                customerId: $("#customer").val().trim(),
                manager: $("#manager").val().trim(),
                managerTel: $("#managerTel").val().trim(),
                agent: $("#agent").val().trim(),
                amount: $("#amount").val().trim(),
                taxNO: $("#taxNO").val().trim(),
                expiry: $("#expiry").val().trim(),
                contractType: $("#contractType").val().trim(),
                address: $("#address").val().trim(),
                content: $("#content").val().trim(),
                paymentSet:paymentTableVal instanceof Array ? paymentTableVal : []
            };

            $.ajax('rest/contract/create', {
                type: 'POST',
                data: JSON.stringify(contractVal),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $("#cancelBtn").trigger("click");
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
});