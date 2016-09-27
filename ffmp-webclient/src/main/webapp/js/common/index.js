/**
 * Created by jiangliang on 2016/6/22.
 */
$(document).ready(function () {
    $("ul > li > a").not($("li.treeview > a")).not($("li.dropdown > a")).click(function (evt) {
        $("#main-content").fadeOut(function () {
            $(evt.currentTarget).closest("li").siblings().removeClass("active");
            var targetUrl = $(evt.currentTarget).data("url");
            $("#main-content").load(targetUrl, function () {
                $("#main-content").fadeIn(function(){
                    $(evt.currentTarget).closest("li").addClass("active");
                });
            });
        });
    });
    $("[data-layout='fixed']").click();

  //  $("#main-content").height($(document).height() -100);
});