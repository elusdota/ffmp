/**
 * Created by suelmer on 2016/7/2.
 */
$(function () {

    $.ajax("rest/areaMgmt/province", {
        type: 'GET',
        contentType: "application/json;charset=utf-8",  //发送信息至服务器时内容编码类型
        dataType: "json", //预期服务器返回的数据类型
        success: function (data, textStatus, jqXHR) {

        },
        error: function (XMLHttpRequest) {
            $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
        }
    });
});