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

        table {
            width: 80%;
            margin: 50px auto;
            text-align: center;
        }

        #add {
            margin-bottom: 20px;
        }

        img {
            vertical-align: middle;
        }

        .top {
            position: fixed;
            top: 5px;
            left: 50px;
        }

        .bottom {
            width: 100%;
            margin: auto;
            text-align: center;
        }

        .bottom a,
        .bottom span {
            margin: 0 10px 0 10px;
        }
    </style>
</head>
<body>
<div class="top">
    <a href="/employee/employee_toadd">添加员工</a>
    <span style="color: red; display: block;margin: 10px 0 10px 0">双击头像可修改!!!</span>
    <form action="/employee/employee_conditionSelect" method="post">
        <select name="pidSelect">
            <option value="-1">所有部门</option>
            <c:forEach items="${sessionScope.departmentList}" var="department">
                <c:choose>
                    <c:when test="${department.getPid() == depid}">
                        <option value="${department.getPid()}" selected>${department.getPname()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${department.getPid()}">${department.getPname()}</option>
                    </c:otherwise>
                </c:choose>
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
                        <td style="background-color:#a9c178;">${employee.getSalary()}</td>
                    </c:when>
                    <c:when test="${employee.getSalary()>=6000}">
                        <td style="background-color:#d1a34f;">${employee.getSalary()}</td>
                    </c:when>
                    <c:when test="${employee.getSalary()>=0}">
                        <td style="background-color:#c56879;">${employee.getSalary()}</td>
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
<div class="bottom">
    <span>${sessionScope.i}/${sessionScope.pages}</span>
    <c:choose>
        <c:when test="${depid == null}">
            <a href="/employee/employee_select?i=1">首页</a>
            <%--        所有部门--%>
            <c:if test="${sessionScope.i != 1}">
                <a href="/employee/employee_select?i=${sessionScope.i-1}">上一页</a>
            </c:if>
            <c:if test="${sessionScope.i != sessionScope.pages}">
                <a href="/employee/employee_select?i=${sessionScope.i+1}">下一页</a>
            </c:if>
            <a href="/employee/employee_select?i=${sessionScope.pages}">末页</a>
            <input id="inputPage" type="text" placeholder="请输入页码..." value="">
            <button id="goPage" type="button">GO</button>
        </c:when>
        <c:otherwise>
            <a href="/employee/employee_conditionSelect?i=1">首页</a>
            <%--        单个部门--%>
            <c:if test="${sessionScope.i != 1}">
                <a href="/employee/employee_conditionSelect?i=${sessionScope.i-1}">上一页</a>
            </c:if>
            <c:if test="${sessionScope.i != sessionScope.pages}">
                <a href="/employee/employee_conditionSelect?i=${sessionScope.i+1}">下一页</a>
            </c:if>
            <a href="/employee/employee_conditionSelect?i=${sessionScope.pages}">末页</a>
            <input id="inputPage" type="number" placeholder="请输入页码..." value="">
            <button id="goPage" type="button">GO</button>
        </c:otherwise>
    </c:choose>

</div>
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
    var inputPage = document.getElementById("inputPage");
    var goPage = document.getElementById("goPage");
    goPage.addEventListener('click', function () {
        var depidSelect = document.getElementsByName('pidSelect')[0];
        let pidValue = depidSelect.options[depidSelect.selectedIndex].value;
        if (pidValue == -1) {
            location.href = '/employee/employee_select?i=' + inputPage.value;
        } else {
            location.href = '/employee/employee_conditionSelect?i=' + inputPage.value;
        }
        url = '';
    });
</script>
</body>
</html>
