/**
 * Created by jiangliang on 2016/8/11.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $("#save").click(function () {
        var cardInfo = getIdCardInfo($("#cardid").val().trim());
        if(!cardInfo.isTrue){
            $("#cardid-error").css("display","inline-block");
            return;
        }
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
            //certificate:$("#certificate").val().trim(),
            //type:$("#type").val().trim(),
            professional:$("#professional").val().trim(),
            diploma:$("#diploma").val().trim(),
            school:$("#school").val().trim(),
            specializing:$("#specializing").val().trim(),
            date:$("#date").val().trim(),
            work:$("#work").val().trim()
        }
        return data;
    }

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
});
