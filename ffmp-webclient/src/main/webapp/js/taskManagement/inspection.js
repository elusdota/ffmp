/**
 * Created by jiangliang on 2016/7/15.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    getType();
    $('#monthTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "名称", field: "name", align: 'center', sortable: true}
            , {title: "编码", field: "code", align: 'center', sortable: true}
        ],
        striped: true
    });
    $('#quarterTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "名称", field: "name", align: 'center', sortable: true}
            , {title: "编码", field: "code", align: 'center', sortable: true}
        ],
        striped: true
    });
    $('#yearTable').bootstrapTable({
        //height: 350,
        columns: [
            {title: "序号", formatter: runningFormatter}
            , {title: "名称", field: "name", align: 'center', sortable: true}
            , {title: "编码", field: "code", align: 'center', sortable: true}
        ],
        striped: true
    });
    $.ajax('rest/inspections/month?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#monthTable').bootstrapTable("append", data.mrrStandards);
            $("#monthRatio").val(data.ratio);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $.ajax('rest/inspections/year?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#yearTable').bootstrapTable("append", data.mrrStandards);
            $("#yearRatio").val(data.ratio);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $.ajax('rest/inspections/quarter?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#quarterTable').bootstrapTable("append", data.mrrStandards);
            $("#quarterRatio").val(data.ratio);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $.ajax('rest/maintenanceProject?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    //序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }
});
$("#insertTable").click(function () {
    if ($("#InspectionForm").valid()) {
        var inspectionName = $("#type").val().trim();
        var name = $("#typemin").val().trim();
        if (inspectionName == '月度巡检') {
            $('#monthTable').bootstrapTable("append", getInsertData(name));
        }
        if (inspectionName == '季度巡检') {
            $('#quarterTable').bootstrapTable("append", getInsertData(name));
        }
        if (inspectionName == '年度巡检') {
            $('#yearTable').bootstrapTable("append", getInsertData(name));
        }
    }
});
$("#save").click(function () {
    $.ajax('rest/inspections', {
        type: 'POST',
        data: JSON.stringify(getSaveData()),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#main-content").load("taskManagement/maintenanceProject", function () {
                $("#main-content").fadeIn();
            });
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
//获取保存的数据
function getSaveData() {
    var monthTable = {
        name: '月度巡检',
        ratio: $("#monthRatio").val().trim(),
        mrrStandards: $("#monthTable").bootstrapTable("getData")
    }
    var quarterTable = {
        name: '季度巡检',
        ratio: $("#quarterRatio").val().trim(),
        mrrStandards: $("#quarterTable").bootstrapTable("getData")
    }
    var yearTable = {
        name: '年度巡检',
        ratio: $("#yearRatio").val().trim(),
        mrrStandards: $("#yearTable").bootstrapTable("getData")
    }
    var inspection = [];
    if ($("#monthTable").bootstrapTable("getData").length > 0) {
        inspection.push(monthTable);
    }
    if ($("#quarterTable").bootstrapTable("getData").length > 0) {
        inspection.push(quarterTable);
    }
    if ($("#yearTable").bootstrapTable("getData").length > 0) {
        inspection.push(yearTable);
    }
    var data = {
        id: $("#id").val().trim(),
        inspections: inspection
    }
    return data;
}
//获取插入的数据
function getInsertData(name) {
    var data1 = {};
    $.ajax("rest/mrrstandard/findOneByNameOnly?name=" + name, {
        type: 'GET',
        async: false,
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            data1 = data;
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    return data1;
}
function getType() {
    $.ajax("rest/mrrstandard/findRoot", {
        type: 'GET',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var selectlist = data;
            if (data != null) {
                for (i = 0; i < selectlist.length; i++) {
                    $("#typemax").append("<option id='" + data[i].id + "' value='" + data[i].name + "'>" + data[i].name + "</option>");
                }
                type_onChange();
            }
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
}
function type_onChange() {
    $.ajax("rest/mrrstandard/findOneByName?name=" + $("#typemax").val().trim(), {
        type: 'GET',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var selectlist = data;
            $("#typemin").empty();
            if (data != null) {
                for (i = 0; i < selectlist.length; i++) {
                    $("#typemin").append("<option id='" + data[i].id + "' value='" + data[i].name + "'>" + data[i].name + "</option>");
                }
            }
        },
        error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
}