/**
 * Created by jiangliang on 2016/8/11.
 */
$(document).ready(function () {
    $("#save").click(function () {
        if ($("#saveForm").valid()) {
            $.ajax('rest/employee', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $("#main-content").load("basicInformation/employee", function () {
                        $("#main-content").fadeIn();
                    });
                },  error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    function getSaveData() {
        var data = {
            name: $("#name").val().trim(),
            code:$("#code").val().trim(),
            sex:$("#sex").val().trim(),
            cardid:$("#cardid").val().trim(),
            phone:$("#phone").val().trim(),
            email:$("#email").val().trim(),
            role:$("#role").val().trim(),
            certificate:$("#certificate").val().trim(),
            professional:$("#professional").val().trim(),
            date:$("#date").val().trim(),
            work:$("#work").val().trim()
        }
        return data;
    }
})
