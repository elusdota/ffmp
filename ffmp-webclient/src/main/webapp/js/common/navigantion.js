/**
 * Created by jiangliang on 2016/6/21.
 */
$(function () {
    $.ajax('rest/navigantion', {
        type: 'GET',
        async: false,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        mimeType: 'application/json',
        success: function (data, textStatus, jqXHR) {
            var menu = '<li class="header">导航菜单</li>';
                //'<li><a href="javascript:;" data-url="index"> <i class="fa fa-dashboard"></i> <span>主页</span></a></li>';
            if (data.length) {
                $.each(data,function(i,item){
                    var liTag='';
                    if(item.children.length > 0){
                        liTag = liTag +
                            '       <li class="treeview">' +
                            '                <a href="javascript:;">' +
                            '                    <i class="fa ' + item.icons + '"></i>  <span>' + item.name + '</span>' +
                            '                        <span class="pull-right-container">' +
                            '                           <i class="fa fa-angle-left pull-right"></i>' +
                            '                        </span>'+
                            '                </a>'  ;

                        var ulTag =  '<ul class="treeview-menu">';
                        $.each(item.children,function(j,item_child){
                            var child_li = '<li><a href="javascript:;" data-url="' + item_child.src + '"><i class="fa ' + item_child.icons + '"></i> <span>' + item_child.name + '</span></a></li>';
                            ulTag = ulTag + child_li;
                        });
                        menu = menu + liTag + ulTag +"</ul></li>";
                    }else{
                        liTag = liTag + '<li><a href="javascript:;" data-url="' + item.src + '"><i class="fa ' + item.icons + '"></i> <span>' + item.name + '</span></a></li>';
                        menu = menu + liTag;
                    }
                });
            }
            document.getElementById('navigantion').innerHTML = menu;
        }
        , error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });
    $("#personalInformation").click(function () {
        $("#main-content").load("system/information", function () {
            $("#main-content").fadeIn();
        });
    });
});