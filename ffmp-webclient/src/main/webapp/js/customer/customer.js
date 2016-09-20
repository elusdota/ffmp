/**
 * Created by jiangliang on 2016/7/23.
 */
$(document).ready(function () {
    $("#updateCustomer").attr("disabled", "true");
    $('#customerTable').bootstrapTable({
        method: 'POST',
        url: 'rest/customer/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#updateCustomer").removeAttr("disabled");
        },
        onUncheck: function (row) {
            $("#updateCustomer").attr("disabled", "true");
        },
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "客户名称", field: "name", sortable: true}
            , {title: "客户编码", field: "code", sortable: true}
            , {title: "联系人", field: "contect", sortable: true}
            , {title: "联系电话", field: "telephone", sortable: true}
            , {title: "地址", field: "address", sortable: true}
            , {title: "邮箱", field: "email", sortable: true}
            , {title: "税号", field: "taxId", sortable: true}
            , {title: "纳税类型", field: "type", sortable: true}
            , {title: "开户行", field: "bank", sortable: true}
            , {title: "银行账户", field: "bankAccount", sortable: true}
            , {title: "创建时间", field: "createTime", sortable: true}
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#createCustomer").click(function () {
    document.getElementById("customerForm").reset();
    $('#customerModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#customerModel").modal("show");
});
$("#updateCustomer").click(function () {
    var data = $('#customerTable').bootstrapTable('getSelections');
    $("#id").val(data[0].id);
    $("#name").val(data[0].name);
    $("#contect").val(data[0].contect);
    $("#telephone").val(data[0].telephone);
    $("#address").val(data[0].address);
    $("#taxId").val(data[0].taxId);
    $("#email").val(data[0].email);
    $("#bank").val(data[0].bank);
    $("#bankAccount").val(data[0].bankAccount);
    $("#type").val(data[0].type);
    $('#customerModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#customerModel").modal("show");
});
$("#submitData").click(function () {
    if ($("#customerForm").valid()) {
        var id=$("#id").val().trim();
        if (id == null || id == "") {
            $.ajax('rest/customer', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $('#customerTable').bootstrapTable('refresh');
                    document.getElementById("customerForm").reset();
                    $("#customerModel").modal("hide");
                },  error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
        else{
            $.ajax('rest/customer', {
                type: 'PUT',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $('#customerTable').bootstrapTable('refresh');
                    document.getElementById("customerForm").reset();
                    $("#customerModel").modal("hide");
                },  error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    }
});
$("#giveupData").click(function () {
    document.getElementById("customerForm").reset();
    $("#customerModel").modal("hide");
});
function getSaveData() {
    var data = {
        id: $("#id").val().trim(),
        name: $("#name").val().trim(),
        contect: $("#contect").val().trim(),
        telephone: $("#telephone").val().trim(),
        address: $("#address").val().trim(),
        taxId: $("#taxId").val().trim(),
        email: $("#email").val().trim(),
        bank: $("#bank").val().trim(),
        bankAccount: $("#bankAccount").val().trim(),
        type: $("#type").val().trim()
    }
    return data;
}