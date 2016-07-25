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
            if (data != null) {
                var menu = "<li class=" + '"header"' + ">" + "navigantion" + "</li><li><a href=" + '"javascript:"' + " data-url=" + '"index"' + "> <i class=" + '"fa fa-dashboard"' + "></i> <span>" + "home" + "</span></a></li>";
                $.each(data, function (i, item) {
                    if (item.children.length > 0) {
                        var li = " <li class=" + 'treeview' + "><a  href=" + '"javascript:"' + "><i class=" + '"fa ' + item.icons + '"' + "></i><span>" + item.name + "</span>" +
                            "<span class="+'"pull-right-container"'+"><i class=" + '"fa fa-angle-left pull-right"' + "></i></span></a>";
                        var ulli = "<ul class=" + '"treeview-menu"' + ">";
                        $.each(item.children, function (s, item1) {
                            var childrenModel = "<li><a href=" + '"javascript:"' + " data-url=" + '"' + item1.src + '"' + "><i class=" + '"fa ' + item1.icons + '"' + "></i><span>" + item1.name + "</span></a></li>";
                            ulli = ulli + childrenModel;
                        });
                        menu = menu + li + ulli + "</ul></li>";
                    } else {
                        var li = "<li><a data-url=" + '"' + item.src + '"' + "><i class=" + '"fa ' + item.icons + '"' + "></i><span>" + item.name + "</span></a></li>";
                        menu = menu + li;
                    }
                });
                document.getElementById('navigantion').innerHTML = menu;
            }
        }
        , error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status !== 200) {
                alert("失败", jqXHR.responseJSON.message);
            }
        }
    });
});