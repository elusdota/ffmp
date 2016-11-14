/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    var menui=$("#maintenanceProject").select2({
        theme: "bootstrap",
        language: "zh-CN",
        ajax: {
            url: "rest/maintenanceProject/findByNameLike",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) {
            return markup;
        }, // 定义option格式使其工作
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });

    function formatRepo(repo) {
        var markup = "<option value=" + repo.id + ">" + repo.name + "</option>";
        return markup;
    }

    function formatRepoSelection(repo) {
        return repo.name || "";
    }
    //绑定字典内容到指定的Select控件
    function BindSelect(ctrlName, url) {
        var control = $('#' + ctrlName);
        //设置Select2的处理
        control.select2({
            allowClear: true,
            templateResult: formatRepo,
            templateSelection: formatRepoSelection,
            escapeMarkup: function (m) {
                return m;
            }
        });

        //绑定Ajax的内容
        $.getJSON(url, function (data) {
            control.empty();//清空下拉框
            $.each(data, function (i, item) {
                control.append("<option value='" + item.id + "'>&nbsp;" + item.name + "</option>");
            });
        });
    }
    var code = $("#id").val().trim();
    if (code != null && code != "") {
        $.ajax('rest/repairForm?id=' + code, {
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data, XMLHttpRequest, jqXHR) {
                $("#name").val(data.parts);
                BindSelect("maintenanceProject","rest/maintenanceProject/findByNameLike?name="+data.projectNumber);
                $("#maintenanceProject").val(data.projectNumber);
                $("#maintenanceProject").select2();
                $("#repairnumber").val(data.code);
                $("#description").val(data.description);
            }, error: function (XMLHttpRequest) {
                $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                $("#message").modal("show");
            }
        });
    }
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
                }, error: function (XMLHttpRequest) {
                    $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
                    $("#message").modal("show");
                }
            });
        }
    });
    function getSaveData() {
        var maintenanceProject = {
            code: $('#maintenanceProject').val()
        };
        var data = {
            maintenanceProject: maintenanceProject,
            name: $("#name").val().trim(),
            startdate: $("#startdate").val().trim(),
            enddate: $("#enddate").val().trim(),
            description: $("#description").val().trim(),
            repairnumber: $("#repairnumber").val().trim()
        }
        return data;
    }
})
