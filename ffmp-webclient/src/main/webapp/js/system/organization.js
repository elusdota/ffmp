/**
 * Created by jiangliang on 2016/7/15.
 */
$(function () {
    //权限初始化
    getAnth();
    //加载角色树
    getOrganizationTree();
});
//加载角色树方法
function getOrganizationTree() {
    $.ajax("rest/organization/findAll", {
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var options = {
                data: data
            };
            $('#organization').treeview(options);
            $('#organization').on('nodeSelected', function (event, data) {
                $("#updateOrganization").removeAttr("disabled");
                $("#createOrganization").removeAttr("disabled");
            });
        },
        error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
}
//权限获取
function getAnth() {
    $("#createOrganization").attr("disabled", "true");
    $("#updateOrganization").attr("disabled", "true");
}
//创建组织机构事件定义
$("#createOrganization").click(function () {
    document.getElementById("organizationForm").reset();
    $('#organizationModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#organizationModel").modal("show");
});
//修改组织机构事件定义
$("#updateOrganization").click(function () {
    var data = $('#organization').treeview('getSelected');
    $("#name").val(data[0].text);
    $("#organizationId").val(data[0].id);
    $('#organizationModel').modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#roleModel").modal("show");
});
//数据提交事件定义
$("#submitData").click(function () {
    var id = $("#organizationId").val();
    if ($("#organizationForm").valid()) {
        if (id == null || id == "") {
            var org = $('#organization').treeview('getSelected');
            $.ajax('rest/organization', {
                type: 'POST',
                data: JSON.stringify(getdata(org)),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    getOrganizationTree();
                    resetForm();
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        } else {
            $.ajax('rest/organization', {
                type: 'PUT',
                data: JSON.stringify(getUpdatedata()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    getOrganizationTree();
                    resetForm();
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
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
        name: $('#name').val(),
        type: $('#type').val()
    };
    var data = {
        organization: organization,
        parentId: org[0].id
    };
    return data;
}
//获取修改数据方法
function getUpdatedata() {
    var organization = {
        id: $("#organizationId").val(),
        name: $('#name').val(),
        type: $('#type').val()
    };
    return organization;
}
//模态框form初始化
function resetForm() {
    document.getElementById("organizationForm").reset();
    $("#organizationModel").modal("hide");
}