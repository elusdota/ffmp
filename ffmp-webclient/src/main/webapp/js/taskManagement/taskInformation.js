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
            $("#maintenanceProject").val(data.maintenanceProject.name);
            $("#proid").val(data.maintenanceProject.id);
            $("#customer").val(data.customer.name);
            $("#description").val(data.description);
            $("#repairnumber").val(data.repairnumber);
        },  error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $.ajax('rest/task/flowchart?id=' + id, {
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            var code;
            var sv;
            for (i = 0; i < data.length; i++) {
                if(i==0){
                    code = data[i].id + '=>' + data[i].type + ': ' + data[i].name + '|past\n';
                    sv=data[i].id+'->';
                }else{
                    code = code + data[i].id + '=>' + data[i].type + ': ' + data[i].name + '|invalid\n';
                    if(data[i].type=='end'){
                        sv=sv+data[i].id+'\n';
                    }else{
                        sv=sv+ data[i].id+'->';
                    }
                }
            }
            var chart;
            chart = flowchart.parse(code+sv);
            chart.drawSVG('canvas', {
                // 'x': 30,
                // 'y': 50,
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
                    'start': {
                        'font-color': 'red',
                        'element-color': 'green',
                        'fill': 'yellow'
                    },
                    'end': {
                        'class': 'end-element'
                    }
                },
                'flowstate': {
                    'past': {'fill': '#CCCCCC', 'font-size': 12},
                    'current': {'fill': 'yellow', 'font-color': 'red', 'font-weight': 'bold'},
                    'future': {'fill': '#FFFF99'},
                    'request': {'fill': 'blue'},
                    'invalid': {'fill': '#444444'},
                    'approved': {'fill': '#58C4A3', 'font-size': 12, 'yes-text': 'APPROVED', 'no-text': 'n/a'},
                    'rejected': {'fill': '#C45879', 'font-size': 12, 'yes-text': 'n/a', 'no-text': 'REJECTED'}
                }
            });
            for (i = 0; i < data.length; i++) {
                var d=data[i].id;
                $('[id^='+data[i].id+']').click(function(){
                    $("#main-content").load("taskManagement/taskNode?id="+d, function () {
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
    $("#main-content").load("taskManagement/projectInformation?id="+data, function () {
        $("#main-content").fadeIn();
    });
});
function test(){
    alert('info here');
}