/**
 * Created by suelmer on 2016/11/2.
 * 合同修改页面的控制器
 */
;
$(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var $paymentTable = $("#paymentTable");
    $paymentTable.bootstrapTable({
        columns: [
            {field: 'id', title: 'ID', visible: false, switchable: false},
            {title: "期数", field: "period", align: 'center', formatter: runningFormatter},
            {title: "付款时间", field: "paymentDate", align: 'center', sortable: true},
            {title: "付款金额(元)", field: "paymentAmount", align: 'center', sortable: true},
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
        row.period = value;
        return value;
    }

    function paymentOperateFormatter(value, row, index) {
        return [
            '<a class="remove" href="javascript:void(0)" title="移除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>&nbsp;  &nbsp;',
            '<a class="plus" href="javascript:void(0)" title="添加">',
            '<i class="glyphicon glyphicon-plus"></i>',
            '</a>&nbsp;  &nbsp;',
            '<a class="edit" href="javascript:void(0)" title="修改">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>'

        ].join('');
    }

    var $modal = $("#paymentModal").modal({show: false});
    window.paymentOperateEvents = {
        'click .remove': function (e, value, row, index) {
            $paymentTable.bootstrapTable('remove', {
                field: 'paymentDate',
                values: [row.paymentDate]
            });
        },
        'click .plus': function (e, value, row, index) {
            $modal.modal('show');
        },
        'click .edit': function (e, value, row, index) {
            $("#paymentMethodId").val(row.id);
            $("#paymentDate").val(row.paymentDate);
            $("#paymentAmount").val(row.paymentAmount);
            $("#receipt").val(row.receipt);
            $("input[name='confirmation']").attr("checked", row.confirmation);
            $modal.modal('show');
        }
    };
    function confirmFormatter(value, row, index) {
        if (value == true) {
            return "是";
        } else {
            return "否";
        }
    }

    $("#customer").select2({
        theme: "bootstrap",
        language: "zh-CN",
        ajax: {
            url: "rest/customer/findByNameLike",
            dataType: 'json',
            delay: 1000,
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
        escapeMarkup: function (markup) {
            return markup;
        }, // 定义option格式使其工作
        minimumInputLength: 1,
        templateResult: formatRepo,   //显示查询结果
        templateSelection: formatRepoSelection      //显示选中对象
    });
    function formatRepo(repo) {
        var markup = "<option value=" + repo.id + ">" + repo.name + "</option>";
        return markup;
    }

    function formatRepoSelection(repo) {
        return repo.name || "";
    }

    var contractId = $("#contractId").val().trim();
    $.ajax('rest/contract/findOneById?id=' + contractId, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#customer").data("customerId", data.customer.id);
            $("#select2-customer-container").text(data.customer.name);
            $("#customer").val(data.customer.id);
            $("#manager").val(data.manager);
            $("#managerTel").val(data.managerTel);
            $("#agent").val(data.agent);
            $("#amount").val(data.amount);
            //$("#taxNO").val(data.taxNO);
            $("#expiry").val(data.expiry);
            $("#contractType").val(data.contractType);
            $("#address").val(data.address);
            $("#content").val(data.content);
            $paymentTable.bootstrapTable("load", data.paymentList);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });

    $("#updatePaymentBtn").click(function () {
        var payment={
            period:1,
            paymentDate: $("#paymentDate").val().trim(),
            paymentAmount :$("#paymentAmount").val().trim(),
            receipt: $("#receipt").val().trim(),
            confirmation: $("input[name='confirmation']:checked").val().trim()
        };
        var $paymentMethodId = $("#paymentMethodId");
        if ($("#paymentMethodForm").valid()) {
            if( $paymentMethodId.val().trim()=="" || $paymentMethodId.val().trim() ==null){
                $paymentTable.bootstrapTable("append", payment);
            }else{
                payment.id=$paymentMethodId.val().trim();
                $paymentTable.bootstrapTable('updateRow',{
                    index: 1, row: payment
                });
            }
        }
        $("#resetPaymentMethod").trigger("click");
        $modal.modal('hide');
    });

    $("#cancelBtn").click(function () {
        $("#resetContract").trigger("click");
        $("#resetPaymentMethod").trigger("click");
        $paymentTable.bootstrapTable("removeAll");

        $("#main-content").fadeOut(function () {
            $("#main-content").load("contract/contract",  function () {
                $("#main-content").fadeIn();
            });
        });
    });

    $("#saveContractdBtn").click(function () {
        if ($("#update_ContractForm").valid()) {
            var paymentTableVal = $paymentTable.bootstrapTable("getData");
            console.log(paymentTableVal);
            var contractVal = {
                id:$("#contractId").val().trim(),
                name: $("#name").val().trim(),
                customerId: $("#customer").val().trim() == undefined || "" || null ? $("#customer").data("customerId"):$("#customer").val().trim(),
                manager: $("#manager").val().trim(),
                managerTel: $("#managerTel").val().trim(),
                agent: $("#agent").val().trim(),
                amount: $("#amount").val().trim(),
                //taxNO: $("#taxNO").val().trim(),
                expiry: $("#expiry").val().trim(),
                contractType: $("#contractType").val().trim(),
                address: $("#address").val().trim(),
                content: $("#content").val().trim(),
                paymentVOList:paymentTableVal instanceof Array ? paymentTableVal : []
            };
            console.log(contractVal);
            $.ajax('rest/contract/update', {
                type: 'PUT',
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