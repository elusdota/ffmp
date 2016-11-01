/**
 * Created by jiangliang on 2016/8/11.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var id = $("#id").val().trim();
    $.ajax('rest/employee?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#code").val(data.code);
            $("#sex").val(data.sex);
            $("#cardid").val(data.cardid);
            $("#phone").val(data.phone);
            $("#email").val(data.email);
            $("#role").val(data.role);
            //$("#certificate").val(data.certificate);
            //$("#type").val(data.type);
            $("#professional").val(data.professional);
            $("#date").val(data.date);
            $("#diploma").val(data.diploma);
            $("#school").val(data.school);
            $("#specializing").val(data.specializing);
            //$("#work").val(data.work);
            if(data.work){
                $("#work").val("true");
            }else{
                $("#work").val("false");
            }

        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
})
$("#save").click(function () {
    var cardInfo = getIdCardInfo($("#cardid").val().trim());
    if(!cardInfo.isTrue){
        $("#cardid-error").css("display","inline-block");
        return;
    }
    if ($("#saveForm").valid()) {
        $.ajax('rest/employee', {
            type: 'PUT',
            data: JSON.stringify(getSaveData()),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $("#main-content").load("basicInformation/employee", function () {
                    $("#main-content").fadeIn();
                });
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
});
//检查身份证号码格式是否准确
$("#cardid").blur(function () {
    var cardNo = document.getElementById('cardid').value;
    var cardInfo = getIdCardInfo(cardNo);
    if(cardInfo.isTrue){
        $("#cardid-error").css("display","none");
    }else{
        $("#cardid-error").css("display","inline-block");
    }
})
function getSaveData() {
    var data = {
        id: $("#id").val().trim(),
        name: $("#name").val().trim(),
        code: $("#code").val().trim(),
        sex: $("#sex").val().trim(),
        cardid: $("#cardid").val().trim(),
        phone: $("#phone").val().trim(),
        email: $("#email").val().trim(),
        role: $("#role").val().trim(),
        //certificate: $("#certificate").val().trim(),
        professional: $("#professional").val().trim(),
        diploma:$("#diploma").val().trim(),
        school:$("#school").val().trim(),
        specializing:$("#specializing").val().trim(),
        date: $("#date").val().trim(),
        work: $("#work").val().trim()
    }
    return data;
}
