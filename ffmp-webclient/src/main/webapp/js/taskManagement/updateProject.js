/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var id = $("#id").val().trim();
    $.ajax('rest/maintenanceProject?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#organization").val(data.delegate.name);
            $("#customer").val(data.customer.name);
            $("#address").val(data.address);
            $("#area").val(data.area);
            $("#totalHeight").val(data.totalHeight);
            $("#floors").val(data.floors);
            $("#nature").val(data.nature);
            $("#manager").val(data.manager);
            $("#inputDate").val(data.inputDate);
            $("#managerTelephone").val(data.managerTelephone);
            $("#days").val(data.days);
            $("#equipmentCase").val(data.equipmentCase);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $("#save").click(function () {
        if ($("#saveForm").valid()) {
            $.ajax('rest/maintenanceProject/update', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $("#main-content").load("taskManagement/maintenanceProject", function () {
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
        var organization = {
            name: $('#organization').val()
        };
        var customer = {
            name: $('#customer').val()
        };
        var data = {
            id:$("#id").val().trim(),
            delegate:organization,
            customer:customer,
            name: $("#name").val().trim(),
            address:$("#address").val().trim(),
            area:$("#area").val().trim(),
            totalHeight:$("#totalHeight").val().trim(),
            floors:$("#floors").val().trim(),
            nature:$("#nature").val().trim(),
            manager:$("#manager").val().trim(),
            inputDate:$("#inputDate").val().trim(),
            days:$("#days").val().trim(),
            managerTelephone:$("#managerTelephone").val().trim(),
            equipmentCase:$("#equipmentCase").val().trim()

        }
        return data;
    }
})
