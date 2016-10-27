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
        clickToSelect: true,
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
            {title: "经办人", field: "agent", align: 'center', sortable: true},
            {title: "合同地址", field: "address", align: 'center', sortable: true,visible:false},
            {title: "合同金额", field: "amount", align: 'center', sortable: true},
            {title: "合同类别", field: "contractType", align: 'center', sortable: true},
            {title: "税号", field: "taxNO", align: 'center', sortable: true},
            {title: "效期", field: "expiry", align: 'center', sortable: true},
            {title: "合同内容", field: "content", align: 'center', sortable: true,visible:false},
            {title: "创建时间", field: "createTime", align: 'center', sortable: true,visible:false},
            {
                title: '付款方式',
                align: 'center',
                events: 'paymentOperateEvents',
                formatter: paymentOperateFormatter
            },
            {
                title: '上传附件',
                align: 'center',
                events: 'attachmentOperateEvents',
                formatter: attachmentOperateFormatter
            }
        ]
    });

    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
    $contractTable.on('check.bs.table', function (row, $element) {
        $("#updateContract").removeAttr("disabled");
        $("#deleteContract").removeAttr("disabled");
    });
    $contractTable.on('uncheck.bs.table', function (row, $element) {
        $("#updateContract").attr("disabled", "disabled");
        $("#deleteContract").attr("disabled", "disabled");
    });
    $("#createContract").click(function () {
        $("#main-content").fadeOut(function () {
            $("#main-content").load("contract/contractForm", function () {
                $("#main-content").fadeIn();
            });
        });
    });

    function paymentOperateFormatter(value, row, index) {
        return [
            '<a class="has-popover" href="javascript:void(0)" title="查看">',
            '<i class="glyphicon glyphicon-eye-open"></i>',
            '</a>'
        ].join('');
    }

    window.paymentOperateEvents = {
        'click .has-popover': function (e, value, row, index) {
            $("#paymentTable").bootstrapTable({
                columns: [
                    {title: "期数", field: "period", align: 'center', formatter: function (value, row, index) {
                        return index + 1;
                    }},
                    {title: "付款时间", field: "paymentDate", align: 'center', sortable: true},
                    {title: "付款金额(元)", field :"paymentAmount", align: 'center', sortable: true},
                    {title: "票据", field: "receipt", align: 'center', sortable: true},
                    {title: "确认收款", field: "confirmation", align: 'center', sortable: true, formatter: function (value, row, index) {
                        if (value == "true") {
                            return "是";
                        } else {
                            return "否";
                        }
                    }}
                ]
            });
            $("#paymentTable").bootstrapTable("load", row.paymentList);
            $("#modal-title").text(row.name+"--付款方式");
            $("#paymentTableModal").modal("show");
        }
    };

    function attachmentOperateFormatter(value, row, index) {
        return [
            '<a class="upload" href="javascript:void(0)" title="上传">',
            '<i class="glyphicon glyphicon-upload"></i>',
            '</a>'
        ].join('');
    }

    window.attachmentOperateEvents = {
        'click .upload': function (e, value, row, index) {
            $("#main-content").fadeOut(function () {
                $("#main-content").load("common/fileUpload",{id:row.id}, function () {
                    $("#main-content").fadeIn();
                });
            });
        }
    };
});