/**
 * Created by jiangliang on 2016/7/17.
 */
$(document).ready(function () {
    var id = $("#id").val().trim();
    $.ajax('rest/task?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $("#name").val(data.name);
            $("#maintenanceProject").val(data.maintenanceProject.code);
            $("#proid").val(data.maintenanceProject.id);
            $("#customer").val(data.customer.name);
            $("#description").val(data.description);
            $("#repairnumber").val(data.repairnumber);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $.ajax('rest/task/flowchart?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var stepse = data.flowchartStepses;
            var code;
            var sv = data.value;
            for (i = 0; i < stepse.length; i++) {
                if (i == 0) {
                    code = stepse[i].parametric + "=>" + stepse[i].type + ": " + stepse[i].name + "|"+stepse[i].color+"\n";
                } else {
                    code = code + stepse[i].parametric + "=>" + stepse[i].type + ": " + stepse[i].name + "|"+stepse[i].color+"\n";
                }
            }
            var chart;
            chart = flowchart.parse(code + sv);
            chart.drawSVG('canvas', {
                'line-width': 3,
                'line-length': 50,
                'text-margin': 10,
                'font-size': 14,
                'font': 'normal',
                'font-family': 'Helvetica',
                'font-weight': 'normal',
                'font-color': 'black',
                'line-color': 'black',
                'element-color': 'black',
                'fill': 'white',
                'yes-text': 'yes',
                'no-text': 'no',
                'arrow-end': 'block',
                'scale': 1,
                'symbols': {
                    //'start': {
                    //    'font-color': 'red',
                    //    'element-color': 'green',
                    //    'fill': 'yellow'
                    //},
                    'end': {
                        'class': 'end-element'
                    }
                },
                'flowstate': {
                    'without': {'fill': '#CCCCCC', 'font-size': 12},
                    'current': {'fill': 'yellow', 'font-color': 'red', 'font-weight': 'bold'},
                    'future': {'fill': '#FFFF99'},
                    'request': {'fill': 'blue'},
                    'invalid': {'fill': '#444444'},
                    'approved': {'fill': '#58C4A3', 'font-size': 12,'yes-text': '批准', 'no-text': '/'},
                    'rejected': {'fill': '#C45879', 'font-size': 12, 'yes-text': '/', 'no-text': '拒绝'}
                }
            });
            for (i = 0; i < data.length; i++) {
                var d = data[i].id;
                $('[id^=' + data[i].id + ']').click(function () {
                    $("#main-content").load("taskManagement/taskNode?id=" + d, function () {
                        $("#main-content").fadeIn();
                    });
                });
            }
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
$("#viewProject").click(function () {
    var data = $("#proid").val().trim();
    $("#main-content").load("taskManagement/projectInformation?id=" + data, function () {
        $("#main-content").fadeIn();
    });
});
function test() {
    alert('info here');
}