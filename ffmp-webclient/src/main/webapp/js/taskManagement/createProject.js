/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
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
                },  error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    $("#customer").select2({
        theme: "bootstrap",
        language: "zh-CN",
        ajax: {
            url: "rest/customer/findByNameLike",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data,
                    pagination: {
                        more: (params.page * 2) < data.length
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // 定义option格式使其工作
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });
    $("#organization").select2({
        theme: "bootstrap",
        language: "zh-CN",
        ajax: {
            url: "rest/organization/findByNameLike",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data,
                    pagination: {
                        more: (params.page * 2) < data.length
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // 定义option格式使其工作
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });

    function formatRepo (repo) {
        alert(repo.id)
        var markup =  "<option value="+ repo.id + ">"+ repo.name + "</option>";
        return markup;
    }
    function formatRepoSelection (repo) {
        alert(repo.name)
        return repo.name || "" ;
    }
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
            inputDate:$("#inputDate").val().trim(),
            days:$("#days").val().trim(),
            managerTelephone:$("#managerTelephone").val().trim(),
            equipmentCase:$("#equipmentCase").val().trim()

        }
        return data;
    }
})
