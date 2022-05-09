<%--
  Created by IntelliJ IDEA.
  User: 16772
  Date: 2022/5/9
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UpLoad Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>上传文件</small>
                </h1>
            </div>
        </div>
    </div>
    <p style="color: red" th:text="${uploadFileError}"></p>
    <form action="/file/upLoad" enctype="multipart/form-data" method="post">
        文件：<input type="file" id="file" name="file" style="width: 30%"><br>
        文件大小：<input type="text" id="fileSize" name="filesize" readonly class="form-control" style="width: 30%"><br>
        文件类型：<select name="fid" id="fid" class="form-control" style="width: 30%"></select><br>
        <input type="submit" class="btn btn-primary" value="上传">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
    $(function () {
        var html = "";
        //发送ajax请求查询文件类型下拉列表
        $.ajax({
            url: '/file/getFileType',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                //循环遍历
                for (var i = 0; i < result.length; i++) {
                    html +="<option value='"+result[i].id+"'>"+result[i].name+"</option>"
                }
                //将html追加到下拉列表中
                $("[name='fid']").append(html);
            },
            error: function () {
                console.log('Send Request Fail..'); // 请求失败时的回调函数
            }
        });
        //获取文件大小
        $('#file').on('change', function() {
            var size = $('#file')[0].files[0].size;
            size = size / 1024;
            size = size / 1024;
            //parseFloat(size).toFixed(2)保留小数点后两位
            $('#fileSize').val(parseFloat(size).toFixed(2) + "M");
        });
    });
</script>
</body>
</html>
