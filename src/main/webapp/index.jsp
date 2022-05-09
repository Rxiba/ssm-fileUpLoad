<%--
  Created by IntelliJ IDEA.
  User: 16772
  Date: 2022/5/9
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Index Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<style type="text/css">
    th{
        text-align:center;/** 设置水平方向居中 */
        vertical-align:middle/** 设置垂直方向居中 */
    }
    td{
        text-align:center;/** 设置水平方向居中 */
        vertical-align:middle;/** 设置垂直方向居中 */
    }
</style>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>文件列表 —— 显示所有文件信息（包括文件类型）</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row" style="margin-bottom: 10px ; padding-left:15px">
        <a class="btn btn-primary" href="add.html">上传文件</a>
        <div class="btn-group mr-2" role="group" aria-label="First group">
            <input type="radio" name="fid" value="1"/>.jpg文件查询
            <input type="radio" name="fid" value="2"/>.png文件查询
        </div>
        <select name="myselect" id="myselect">
            <option value="1">.jpg文件查询</option>
            <option value="2">.png文件查询</option>
        </select>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>文件编号</th>
                    <th>图片</th>
                    <th>文件名</th>
                    <th>文件类型</th>
                    <th>文件大小</th>
                    <th>上传时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="list"></tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(function () {
        //ajax异步获取所有文件内容，遍历展示
        $.ajax({
            url: '/file/getFileList',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                var html = "";
                for (var i = 0; i < result.length; i++) {
                    html+= "<tr>" +
                        "<td>" + result[i].id + "</td>" +
                        "<td>" + "<img src='pic/" + result[i].realName + "' width='100' >" + "</td>" +
                        "<td>" + result[i].realName + "</td>" +
                        "<td>" + result[i].fileType.name + "</td>" +
                        "<td>" + result[i].fileSize + "M" +"</td>" +
                        "<td>" + result[i].uploadTime + "</td>" +
                        "<td>" + "<a class='btn btn-success' href='/file/downLoad?filename=" + result[i].realName + "'>下载文件</a>" + "</td>" +
                        "</tr>"
                }
                $("#list").append(html);
            },
            error: function () {
                console.log('Send Request Fail..'); // 请求失败时的回调函数
            }
        });

        //根据文件类型查询（单选按钮）
        $("input[name=fid]").click(function(){
            var html = "";
            $("#list").empty();
            $.ajax({
                url: '/file/getFileListByType',
                data:{
                    "fid":$('input[name="fid"]:checked').val()
                },
                type: 'POST',
                dataType:'json',
                success: function (result) {
                    for (var i = 0; i < result.length; i++) {
                        html+= "<tr>" +
                            "<td>" + result[i].id + "</td>" +
                            "<td>" + "<img src='pic/" + result[i].realName + "' width='100' >" + "</td>" +
                            "<td>" + result[i].realName + "</td>" +
                            "<td>" + result[i].fileType.name + "</td>" +
                            "<td>" + result[i].fileSize + "</td>" +
                            "<td>" + result[i].uploadTime + "</td>" +
                            "<td>" + "<a class='btn btn-success' href='/file/downLoad?filename=" + result[i].realName + "'>下载文件</a>" + "</td>" +
                            "</tr>"
                    }
                    $("#list").append(html);
                },
                error: function () {
                    console.log('Send Request Fail..'); // 请求失败时的回调函数
                }
            });
        });

        //根据文件类型查询（下拉列表）
        $("#myselect").change(function(){
            var opt=$("#myselect").val();
            var html = "";
            $("#list").empty();
            $.ajax({
                url: '/file/getFileListByType',
                data:{
                    "fid":$("#myselect").val()
                },
                type: 'POST',
                dataType:'json',
                success: function (result) {
                    for (var i = 0; i < result.length; i++) {
                        html+= "<tr>" +
                            "<td>" + result[i].id + "</td>" +
                            "<td>" + "<img src='pic/" + result[i].realName + "' width='100' >" + "</td>" +
                            "<td>" + result[i].realName + "</td>" +
                            "<td>" + result[i].fileType.name + "</td>" +
                            "<td>" + result[i].fileSize + "</td>" +
                            "<td>" + result[i].uploadTime + "</td>" +
                            "<td>" + "<a class='btn btn-success' href='/file/downLoad?filename=" + result[i].realName + "'>下载文件</a>" + "</td>" +
                            "</tr>"
                    }
                    $("#list").append(html);
                },
                error: function () {
                    console.log('Send Request Fail..'); // 请求失败时的回调函数
                }
            });
        });

    })
</script>
</body>
</html>
