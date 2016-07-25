/**
 * Created by jiangliang on 2016/6/22.
 */
$(function () {
    //权限初始化
    getAnth();
    //加载角色树
    getRoleTree();
});
//加载角色树方法
function getRoleTree() {
    $.ajax("rest/role/findAll?id=" + "null", {
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var options = {
                data: data
            };
            $('#roles').treeview(options);
            $('#roles').on('nodeSelected', function (event, data) {
                getGrantedAuthorities(data);
                if (data.type == "角色") {
                    $("#anth").removeAttr("disabled");
                    $("#updateRole").removeAttr("disabled");
                    $("#createRole").attr("disabled", "true");
                }
                else {
                    $("#anth").attr("disabled", "true");
                    $("#createRole").removeAttr("disabled");
                    $("#updateRole").attr("disabled", "true");
                }
            });
        },
        error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
}
//根据角色数据加载权限树
function getGrantedAuthorities(data) {
    $.ajax("rest/grantedAuthority/findAll", {
        type: "GET",
        data: data,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var options = {
                showCheckbox: true,
                data: data
            };
            $('#grantedAuthorities').treeview(options);
            $('#grantedAuthorities').on('nodeChecked', function (event, data) {
                var org = $('#roles').treeview('getSelected');
                allocationAnth(getallocationAuthData(org, data, true));
            });
            $('#grantedAuthorities').on('nodeUnchecked', function (event, data) {
                var org = $('#roles').treeview('getSelected');
                allocationAnth(getallocationAuthData(org, data, false));
            });
        }, error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    })
    ;
}
$("#anth").click(function () {
    $('#grantedAuthorities').treeview('enableAll', {silent: true});
});
//权限获取
function getAnth() {
    $("#createRole").attr("disabled", "true");
    $("#updateRole").attr("disabled", "true");
    $("#anth").attr("disabled", "true");
}
//创建角色事件定义
$("#createRole").click(function () {
    document.getElementById("roleForm").reset();
    $('#roleModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#roleModel").modal("show");
});
//修改角色事件定义
$("#updateRole").click(function () {
    var data = $('#roles').treeview('getSelected');
    $("#name").val(data[0].text);
    $("#roleId").val(data[0].id);
    $('#roleModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#roleModel").modal("show");
});
//数据提交事件定义
$("#submitData").click(function () {
    var org = $('#roles').treeview('getSelected');
    var id = $("#roleId").val();
    if ($("#roleForm").valid()) {
        if (id == null || id == "") {
            $.ajax('rest/role', {
                type: 'POST',
                data: JSON.stringify(getdata(org)),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    getRoleTree();
                    resetForm();
                }, error: function (XMLHttpRequest) {
                    $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
                }
            });
        } else {
            $.ajax('rest/role', {
                type: 'PUT',
                data: JSON.stringify(getUpdatedata()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    getRoleTree();
                    resetForm();
                }, error: function (XMLHttpRequest) {
                    $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
                }
            });
        }
    }
});
//放弃数据提交事件定义
$("#giveupData").click(function () {
    resetForm();
});
//获取数据方法
function getdata(org) {
    var organization = {
        id: org[0].id,
        name: org[0].text
    };
    var role = {
        name: $('#name').val()
    }
    var data = {
        role: role,
        organization: organization
    };
    return data;
}
//获取修改数据方法
function getUpdatedata(org) {
    var data = {
        id: $("#roleId").val(),
        name: $('#name').val()
    }
    return data;
}
//模态框form初始化
function resetForm() {
    document.getElementById("roleForm").reset();
    $("#roleModel").modal("hide");
}
//获取角色绑定数据方法
function getallocationAuthData(org, row, allocation) {
    var data = {
        role: org[0].id,
        anth: row.id,
        lift: allocation
    }
    return data;
}
//角色绑定方法
function allocationAnth(auth) {
    $.ajax("rest/role/allocationAuth", {
        type: "POST",
        data: JSON.stringify(auth),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, XMLHttpRequest, jqXHR) {
            //什么也不做
        }, error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
}