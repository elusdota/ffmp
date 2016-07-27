/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    $("#save").click(function () {
        if ($("#saveForm").valid()) {
            $.ajax('rest/task', {
                type: 'POST',
                data: JSON.stringify(getSaveData()),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data, XMLHttpRequest, jqXHR) {
                    $("#main-content").load("taskManagement/runTask", function () {
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
        var maintenanceProject = {
            name: $('#maintenanceProject').val()
        };
        var data = {
            maintenanceProject:maintenanceProject,
            name: $("#name").val().trim(),
            startdate: $("#startdate").val().trim(),
            enddate: $("#enddate").val().trim(),
            description: $("#description").val().trim(),
            repairnumber: $("#repairnumber").val().trim()
        }
        return data;
    }
})
