/**
 * Created by jiangliang on 2016/8/12.
 */
$(document).ready(function () {
    $.ajax("rest/account/login", {
        type: "GET",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#accountId").val(data.id);
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $("#submitUpdateData").click(function () {
        if ($("#saveForm").valid()) {
            $.ajax('rest/account/update', {
                type: 'POST',
                data: JSON.stringify(getupdatedata()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                   alert("密码已经修改请重新登录！");
                    $("#main-content").load("/j_spring_security_logout", function () {
                        //$("#main-content").fadeIn();
                        window.location.reload();
                    });
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    //获取修改后的数据
    function getupdatedata() {
        var data = {
            id: $('#accountId').val(),
            name: $('#name').val(),
            password: $('#newpassword').val()
        };
        var data1 = {
            account: data,
            rawpassword: $('#password').val()
        }
        return data1;
    }
});