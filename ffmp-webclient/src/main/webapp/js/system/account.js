/**
 * Created by jiangliang on 2016/6/23.
 */
$(document).ready(function () {
    //权限初始化
    getAnth();
    //账户数据初始化加载
    $('#accountTable').bootstrapTable({
        method: 'POST',
        url: 'rest/account/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#updateAccount").removeAttr("disabled");
            $("#allocationRole").removeAttr("disabled");
            loadRoles(row);
        },
        onUncheck: function (row) {
            getAnth();
        },
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                search: params.search,
                sort: params.sort
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "账号", field: "name", sortable: true}
            , {title: "类型", field: "accessType", sortable: true}]
    });
    //根据选择的账户加载角色树
    function loadRoles(row) {
        $.ajax("rest/role/findAll", {
            type: "GET",
            data: row,
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            mimeType: 'application/json',
            success: function (data, XMLHttpRequest, jqXHR) {
                var options = {
                    showCheckbox: true,
                    //showTags:true,
                    data: data
                };
                $('#roles').treeview(options);
                $('#roles').on('nodeChecked', function (event, data) {
                    var org = $('#accountTable').bootstrapTable('getSelections');
                    allocationAnth(getallocationAuthData(org, data, true));
                });
                $('#roles').on('nodeUnchecked', function (event, data) {
                    var org = $('#accountTable').bootstrapTable('getSelections');
                    allocationAnth(getallocationAuthData(org, data, false));
                });
            },
            error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }

//账户角色分配数据获取
    function getallocationAuthData(org, row, allocation) {
        var data = {
            account: org[0].id,
            role: row.id,
            lift: allocation
        }
        return data;
    }

//执行角色分配
    function allocationAnth(auth) {
        $.ajax("rest/account/allocationRole", {
            type: "POST",
            data: JSON.stringify(auth),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            mimeType: 'application/json',
            success: function (data, XMLHttpRequest, jqXHR) {
                //什么也不做
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }

//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});
//角色分配按钮点击事件
$("#allocationRole").click(function () {
    $('#roles').treeview('enableAll', {silent: true});
});
//权限初始化方法
function getAnth() {
    $("#updateAccount").attr("disabled", "true");
    $("#allocationRole").attr("disabled", "true");
    //$("#anth").attr("disabled", "true");
}
//点击创建事件
$("#createAccount").click(function () {
    document.getElementById("accountForm").reset();
    $('#accountModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#accountModel").modal("show");
});
//定义点击修改按钮
$("#updateAccount").click(function () {
    var data = $('#accountTable').bootstrapTable('getSelections');
    $("#name1").val(data[0].name);
    $("#accountId").val(data[0].id);
    $('#accountUpdateModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#accountUpdateModel").modal("show");
});
//定义数据修改提交点击
$("#submitUpdateData").click(function () {
    if ($("#accountForm").valid()) {
        $.ajax('rest/account/update', {
            type: 'POST',
            data: JSON.stringify(getupdatedata()),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $('#accountTable').bootstrapTable('refresh');
                document.getElementById("accountUpdateForm").reset();
                $("#accountUpdateModel").modal("hide");
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});
//定义数据添加提交点击事件
$("#submitData").click(function () {
    if ($("#accountForm").valid()) {
        $.ajax('rest/account', {
            type: 'POST',
            data: JSON.stringify(getdata()),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $('#accountTable').bootstrapTable('refresh');
                resetForm();
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});
//放弃提交点击事件定义
$("#giveupData").click(function () {
    resetForm();
});
//放弃修改点击事件定义
$("#giveupUpdateData").click(function () {
    document.getElementById("accountUpdateForm").reset();
    $("#accountUpdateModel").modal("hide");
});
//获取添加数据
function getdata() {
    var data = {
        name: $('#name').val()
    };
    return data;
}
//获取修改后的数据
function getupdatedata() {
    var data = {
        id: $('#accountId').val(),
        name: $('#name1').val(),
        password: $('#newpassword').val()
    };
    var data1={
        account:data,
        rawpassword: $('#password').val()
    }
    return data1;
}
//模态框数据清除和关闭
function resetForm() {
    document.getElementById("accountForm").reset();
    $("#accountModel").modal("hide");
}