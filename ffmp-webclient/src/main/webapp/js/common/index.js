/**
 * Created by jiangliang on 2016/6/22.
 */
$(document).ready(function () {
    $("ul > li > a").not($("li.treeview > a")).not($("li.dropdown > a")).click(function (evt) {
        $("#main-content").fadeOut(function () {
            var targetUrl = $(evt.currentTarget).data("url");
            $(evt.currentTarget).addClass("active")
            var obj = $("#main-content").load(targetUrl, function () {
                //$('[data-toggle="tooltip"]').tooltip();
                $("#main-content").fadeIn();
            });
        });
    });
})