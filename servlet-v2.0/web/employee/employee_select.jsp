<%@ page import="java.awt.*" %>
<%@ page import="com.servlet.pojo.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工查询</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        table,
        .top {
            width: 900px;
            margin: 50px auto;
            text-align: center;
        }

        #add {
            margin-bottom: 20px;
        }

        img {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="top">
    <a href="/employee/employee_toadd">添加员工</a>
    <span style="color: red; display: block">双击头像可修改!!!</span>
    <form action="/employee/employee_conditionSelect" method="post">
        <select name="pidSelect">
            <option value="-1">所有部门</option>
            <c:forEach items="${sessionScope.departmentList}" var="department">
                <option value="${department.getPid()}">${department.getPname()}</option>
            </c:forEach>
        </select>
        <input type="submit" value="查询">
    </form>
</div>
<form action="/employee/employee_conditionDel" method="post">
    <table border="0" cellpadding="10px">
        <tr>
            <th>
                <input type="checkbox" id="selectAll">全选
                <button name="condDel" type="submit">条件删除</button>
            </th>
            <th>员工ID</th>
            <th>员工头像</th>
            <th>员工姓名</th>
            <th>性别</th>
            <th>部门</th>
            <th>工资</th>
            <th>地址</th>
            <th>职位</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach items="${sessionScope.employeeList}" var="employee">
            <tr>
                <td><input type="checkbox" name="eid" value="${employee.getEid()}"></td>
                <td>${employee.getEid()}</td>
                <td>
                    <img style="width: 50px;height: 50px" src="/upload/${employee.getFile()}">
                </td>
                <td>${employee.getEname()}</td>
                <td>${employee.getSex()}</td>
                <td>${employee.getDepartment().getPname()}</td>

                <c:choose>
                    <c:when test="${employee.getSalary()>=8000}">
                        <td style="background-color:olivedrab;">${employee.getSalary()}</td>
                    </c:when>
                    <c:when test="${employee.getSalary()>=6000}">
                        <td style="background-color:orange;">${employee.getSalary()}</td>
                    </c:when>
                    <c:when test="${employee.getSalary()>=0}">
                        <td style="background-color:crimson;">${employee.getSalary()}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${employee.getSalary()}</td>
                    </c:otherwise>
                </c:choose>

                <td>${employee.getDizhi()}</td>
                <td>${employee.getJob()}</td>
                <td>
                    <a name="del" href="#">删除</a>
                </td>
                <td>
                    <a href="/employee/employee_edit?eid=${employee.getEid()}">修改</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<script>
    // 单个用户删除
    var del = document.getElementsByName("del");
    for (let i = 0; i < del.length; i++) {
        del[i].onclick = function () {
            // 父级的父级的第4个子元素td，包含员工姓名
            let ename = this.parentNode.parentNode.children[3];
            var ok = confirm("确定删除" + ename.innerText + "吗？");
            if (!ok) {
                return false;
            } else {
                // 父级的父级的第1个子元素td里面的input，value为员工编号
                let eid = this.parentNode.parentNode.children[0].children[0];
                location = "/employee_del?eid=" + eid.value;
            }
        }
    }
    // 添加用户
    /*var add = document.getElementById("add");
    add.onclick = function () {
        location = "/employee/employee_toadd";
    }*/
    // 全选
    var selectAll = document.getElementById("selectAll");
    var checkboxEid = document.getElementsByName("eid");
    selectAll.addEventListener('click', function () {
        for (let i = 0; i < checkboxEid.length; i++) {
            checkboxEid[i].checked = this.checked;
        }
    });
    // 修改用户
    /*var edit = document.getElementsByName("edit");
    for (let i = 0; i < edit.length; i++) {
        edit[i].addEventListener("click", function () {
            // 父级的父级的第2个子元素
            url = "/employee/employee_edit?eid=" + this.parentNode.parentNode.children[1].innerText;
            location = url;
        });
    }*/
    // 双击用户头像修改
    var img = document.getElementsByTagName("img");
    for (let x = 0; x < img.length; x++) {
        img[x].addEventListener("dblclick", function () {
            // 父级的上一个元素节点
            var eid = this.parentNode.previousElementSibling.innerText;
            location = "/uploadfile?eidFile=" + eid;
        });
    }
</script>
</body>
</html>
