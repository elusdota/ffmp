/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $("#save").click(function () {
        if ($("#saveForm").valid()) {
            $.ajax('rest/maintenanceProject', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $("#main-content").load("taskManagement/maintenanceProject", function () {
                        $("#main-content").fadeIn();
                    });
                }, error: function (XMLHttpRequest) {
                    $.messager.alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
                }
            });
        }
    });
    function getSaveData() {
        var organization = {
            name: $('#organization').val()
        };
        var customer = {
            name: $('#customer').val()
        };
        var data = {
            delegate:organization,
            customer:customer,
            name: $("#name").val().trim(),
            address:$("#address").val().trim(),
            area:$("#area").val().trim(),
            totalHeight:$("#totalHeight").val().trim(),
            floors:$("#floors").val().trim(),
            nature:$("#nature").val().trim(),
            manager:$("#manager").val().trim(),
            managerTelephone:$("#managerTelephone").val().trim(),
            equipmentCase:$("#equipmentCase").val().trim()

        }
        return data;
    }
})
