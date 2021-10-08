<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>测试</title>
    <style>
        ul {
            width: 600px;
            list-style: none;
            margin: 0 auto;
        }

        li {
            margin: 10px 0 10px 0;
        }

        img {
            width: 200px;
        }
        form{
            width: 200px;
            height: 200px;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="/employee/employee_toadd">员工注册</a></li>
    <li><a href="/employee/employee_select">员工列表</a></li>
    <li><a href="/el">el表达式</a></li>
    <form action="/file_upload" method="post" enctype="multipart/form-data" id="submit">
        <li>
            <input type="file" name="file" id="file">
            <input type="hidden" name="hd" id="hd">
        </li>
        <li>
            <img src="" id="img" style="visibility: hidden;border: 1px solid salmon">
            <span id="size" style="visibility: hidden"></span>
        </li>
        <input type="button" value="图片上传" id="subBtn">
    </form>
</ul>
</body>
<script>
    // 文件表单
    var file = document.getElementById('file');
    // 隐藏域
    var hd = document.getElementById("hd");
    // 表单
    var submit = document.getElementById("submit");
    // 图片上传按钮
    var subBtn = document.getElementById("subBtn");

    // img显示要上传的图片
    var img = document.getElementById("img");
    // 文件大小
    var size = document.getElementById("size");
    subBtn.addEventListener("click", function () {
        hd.value = file.value;
        submit.submit();
    });
    file.addEventListener('change', function () {
        img.style.visibility = 'visible';
        var file = this.files[0];
        let url = URL.createObjectURL(file);
        img.src = url;
        size.style.visibility = 'visible';
        size.innerHTML="<h4>大小："+file.size/1024+" KB</h4>";
    });
</script>
</html>