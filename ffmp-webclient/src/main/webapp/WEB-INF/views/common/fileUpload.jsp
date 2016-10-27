<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/8/26
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="<c:url value='/bower_components/blueimp-gallery/css/blueimp-gallery.min.css'/>">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="<c:url value='/bower_components/blueimp-file-upload/css/jquery.fileupload.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/blueimp-file-upload/css/jquery.fileupload-ui.css'/>">
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript><link rel="stylesheet" href="<c:url value='/bower_components/blueimp-file-upload/css/jquery.fileupload-noscript.css'/>"></noscript>
<noscript><link rel="stylesheet" href="<c:url value='/bower_components/blueimp-file-upload/css/jquery.fileupload-ui-noscript.css'/>"></noscript>

<section class="content-header">
    <h1>
        文件上传
    </h1>
    <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="javascript:;" data-url="contract/contract"> 合同管理</a></li>
        <li class="active"> 文件上传</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <sec:authorize access="@userDetailsUtils.isAuthorized('/文件上传/添加文件')">
                <button type="button" id="createContract" class="btn btn-default">添加文件</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/文件上传/开始上传')">
                <button type="button" id="updateContract" class="btn btn-default" disabled>开始上传</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/文件上传/取消上传')">
                <button type="button" id="deleteContract" class="btn btn-default" disabled>取消上传</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/文件上传/删除文件')">
                <button type="button" id="deleteContract" class="btn btn-default" disabled>删除文件</button>
            </sec:authorize>
        </div>
        <div class="box-body">
            <!-- The file upload form used as target for the file upload widget -->
            <form id="fileupload" action='<s:url value="/upload"/>' method="POST" enctype="multipart/form-data">
                <!-- Redirect browsers with JavaScript disabled to the origin page -->
                <noscript><input type="hidden" name="redirect" value="<%=request.getContextPath()%>/contract/contractFileUpload">
                </noscript>
                <input type="hidden" class="form-control" id="documentid"
                       name="contractId" value="${param.id}">
                <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                <div class="row fileupload-buttonbar">
                    <div class="col-lg-7">
                        <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>添加文件...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                        <button type="submit" class="btn btn-primary start">
                            <i class="glyphicon glyphicon-upload"></i>
                            <span>开始上传</span>
                        </button>
                        <button type="reset" class="btn btn-warning cancel">
                            <i class="glyphicon glyphicon-ban-circle"></i>
                            <span>取消上传</span>
                        </button>
                        <button type="button" class="btn btn-danger delete">
                            <i class="glyphicon glyphicon-trash"></i>
                            <span>删除</span>
                        </button>
                        <input type="checkbox" class="toggle">
                        <!-- The global file processing state -->
                        <span class="fileupload-process"></span>
                    </div>
                    <!-- The global progress state -->
                    <div class="col-lg-5 fileupload-progress fade">
                        <!-- The global progress bar -->
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="0"
                             aria-valuemax="100">
                            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                        </div>
                        <!-- The extended global progress state -->
                        <div class="progress-extended">&nbsp;</div>
                    </div>
                </div>
                <!-- The table listing the files available for upload/download -->
                <table role="presentation" class="table table-striped">
                    <tbody class="files"></tbody>
                </table>
            </form>
        </div>
        <!-- The blueimp Gallery widget -->
        <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
            <div class="slides"></div>
            <h3 class="title"></h3>
            <a class="prev">‹</a>
            <a class="next">›</a>
            <a class="close">×</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
        </div>

        <!-- The template to display files available for upload -->
        <script id="template-upload" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
                <tr class="template-upload fade">
                    <td>
                        <span class="preview"></span>
                    </td>
                    <td>
                        <p class="name">{%=file.name%}</p>
                        <strong class="error text-danger"></strong>
                    </td>
                    <td>
                        <p class="size">Processing...</p>
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
                    </td>
                    <td>
                        {% if (!i && !o.options.autoUpload) { %}
                            <button class="btn btn-primary start">
                                <i class="glyphicon glyphicon-upload"></i>
                                <span>开始上传</span>
                            </button>
                        {% } %}
                        {% if (!i) { %}
                            <button class="btn btn-warning cancel">
                                <i class="glyphicon glyphicon-ban-circle"></i>
                                <span>取消上传</span>
                            </button>
                        {% } %}
                    </td>
                </tr>
            {% } %}

        </script>
        <!-- The template to display files available for download -->
        <script id="template-download" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
                <tr class="template-download fade">
                    <td>
                        <span class="preview">
                            {% if (file.thumbnailUrl) { %}
                                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                            {% } %}
                        </span>
                    </td>
                    <td>
                        <p class="name">
                            {% if (file.url) { %}
                                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                            {% } else { %}
                                <span>{%=file.name%}</span>
                            {% } %}
                        </p>
                        {% if (file.error) { %}
                            <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                        {% } %}
                    </td>
                    <td>
                        <span class="size">{%=o.formatFileSize(file.size)%}</span>
                    </td>
                    <td>
                        <a class="btn btn-primary" role="button" data-type="get" data-url="{%=file.downloadUrl%}" href="{%=file.downloadUrl%}">
                                <i class="glyphicon glyphicon-download"></i>
                                <span>下载</span>
                            </a>
                    </td>
                    <td>
                        {% if (file.deleteUrl) { %}
                            <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                                <i class="glyphicon glyphicon-trash"></i>
                                <span>删除</span>
                            </button>
                            <input type="checkbox" name="delete" value="1" class="toggle">
                        {% } else { %}
                            <button class="btn btn-warning cancel">
                                <i class="glyphicon glyphicon-ban-circle"></i>
                                <span>取消</span>
                            </button>
                        {% } %}
                    </td>
                </tr>
            {% } %}

        </script>
        <div class="box-footer clearfix">

        </div>
    </div>

</section>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/vendor/jquery.ui.widget.js'/>"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="<c:url value='/bower_components/blueimp-tmpl/js/tmpl.min.js'/>"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="<c:url value='/bower_components/blueimp-load-image/js/load-image.all.min.js'/>"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="<c:url value='/bower_components/blueimp-canvas-to-blob/js/canvas-to-blob.min.js'/>"></script>
<!-- blueimp Gallery script -->
<script src="<c:url value='/bower_components/blueimp-gallery/js/jquery.blueimp-gallery.min.js'/>"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.iframe-transport.js'/>"></script>
<!-- The basic File Upload plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload.js'/>"></script>
<!-- The File Upload processing plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-process.js'/>"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-image.js'/>"></script>
<!-- The File Upload audio preview plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-audio.js'/>"></script>
<!-- The File Upload video preview plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-video.js'/>"></script>
<!-- The File Upload validation plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-validate.js'/>"></script>
<!-- The File Upload user interface plugin -->
<script src="<c:url value='/bower_components/blueimp-file-upload/js/jquery.fileupload-ui.js'/>"></script>
<!-- The main application script -->
<script src="<c:url value='/js/common/fileupload.js'/>"></script>
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="<c:url value='/bower_components/blueimp-file-upload/js/cors/jquery.xdr-transport.js'/>"></script>
<![endif]-->