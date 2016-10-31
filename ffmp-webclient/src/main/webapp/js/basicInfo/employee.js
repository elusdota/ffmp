/**
 * Created by jiangliang on 2016/8/11.
 */
$(document).ready(function () {
    $('.datepicker').datepicker({
        language: 'zh-CN'
    });
    $("#updateEmployee").attr("disabled", "true");
    $("#deleteEmployee").attr("disabled", "true");
    $("#documentManager").attr("disabled", "true");
    $('#employeeTable').bootstrapTable({
        method: 'POST',
        url: 'rest/employee/findAll',
        sidePagination: 'server',
        striped: true,
        singleSelect: true,
        checkbox: true,
        clickToSelect: true,
        onCheck: function (row) {
            $("#updateEmployee").removeAttr("disabled");
            if(row.work){
                $("#deleteEmployee").removeAttr("disabled");
            }
            $("#documentManager").removeAttr("disabled");
        },
        onUncheck: function (row) {
            $("#updateEmployee").attr("disabled", "true");
            $("#deleteEmployee").attr("disabled", "true");
            $("#documentManager").attr("disabled", "true");
        },
        queryParams: function (params) {
            var fin = {
                offset: params.offset,
                limit: params.limit,
                order: params.order,
                sort: params.sort,
                search: params.search
            };
            return JSON.stringify(fin);
        },
        columns: [{
            field: 'state', checkbox: true
        }
            , {title: '序号', formatter: runningFormatter}
            , {title: "名称", field: "name", sortable: true}
            , {title: "编号", field: "code", sortable: true}
            , {title: "性别", field: "sex", sortable: true}
            , {title: "身份证", field: "cardid", sortable: true}
            , {title: "电话", field: "phone", sortable: true}
            , {title: "邮箱", field: "email", sortable: true}
            , {title: "学历", field: "diploma", sortable: true}
            , {title: "毕业学校", field: "school", sortable: true}
            , {title: "专业", field: "specializing", sortable: true}
            , {title: "职务", field: "role", sortable: true}
            //, {title: "证书", field: "certificate", sortable: true}
            //, {title: "证书类型", field: "type", sortable: true}
            , {title: "职称", field: "professional", sortable: true}
            , {title: "入职时间", field: "date", sortable: true}
            , {
                title: "在职", field: "work", sortable: true, formatter: function (val) {
                    if (val) {
                        return '是';
                    }
                    else {
                        return '否';
                    }
                }
            }
        ]
    });
//序号加载
    function runningFormatter(value, row, index) {
        return index + 1;
    }

})
$("#createEmployee").click(function () {
    $("#main-content").load("basicInformation/createEmployee", function () {
        $("#main-content").fadeIn();
    });
});
$("#updateEmployee").click(function () {
    var data = $('#employeeTable').bootstrapTable('getSelections');
    $("#main-content").load("basicInformation/updateEmployee?id=" + data[0].id, function () {
        $("#main-content").fadeIn();
    });
});
$("#deleteEmployee").click(function () {
    var data = $('#employeeTable').bootstrapTable('getSelections')[0];
    $.ajax('rest/employee?id=' + data.id, {
        type: 'DELETE',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            $('#employeeTable').bootstrapTable('refresh');
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
});
$("#documentManager").click(function () {
    var data = $('#employeeTable').bootstrapTable('getSelections');
        $("#main-content").load("common/fileUpload",{id:data[0].id}, function () {
            $("#main-content").fadeIn();
        });
});