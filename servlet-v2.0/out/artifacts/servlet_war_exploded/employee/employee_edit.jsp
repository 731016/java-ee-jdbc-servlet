<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        table {
            width: 1000px;
            margin: 100px auto;
        }

        tr {
            text-align: center;
        }

        th {
            width: 12.5%;
        }
    </style>
</head>
<body>
<a href="/employee/employee_select">返回员工列表</a>
<span style="color: red; display: block">双击头像可修改!!!</span>
<form action="/employee/employee_alter" method="post">
    <table>
        <tr>
            <th>员工ID</th>
            <th>员工头像</th>
            <th>员工姓名</th>
            <th>性别</th>
            <th>部门</th>
            <th>工资</th>
            <th>地址</th>
            <th>职位</th>
            <th>操作</th>
        </tr>
        <tr>
            <td><input name="eid" type="hidden" value="${employeeOne.getEid()}">${employeeOne.getEid()}</td>
            <td>
                <img style="width: 50px;height: 50px" src="/upload/${employeeOne.getFile()}">
            </td>
            <td>
                <input name="ename" type="text" value="${employeeOne.getEname()}">
            </td>
            <td>
                <c:choose>
                    <c:when test="${employeeOne.getSex() == '男'}">
                        <input type="radio" name="sex" value="男" checked>男
                        <input type="radio" name="sex" value="女">女
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="sex" value="男">男
                        <input type="radio" name="sex" value="女" checked>女
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <select name="pid">
<%--                    部门列表--%>
                    <c:forEach items="${sessionScope.departmentList}" var="department">
                        <c:choose>
<%--                            当前员工的部门id--%>
                            <c:when test="${department.getPid() == employeeOne.getDepartment().getPid()}">
                                <option value="${department.getPid()}" selected>${department.getPname()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${department.getPid()}">${department.getPname()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>

            <c:choose>
                <c:when test="${employeeOne.getSalary()>=8000}">
                    <td style="background-color:olivedrab;">
                        <input name="salary" type="number" value="${employeeOne.getSalary()}">
                    </td>
                </c:when>
                <c:when test="${employeeOne.getSalary()>=6000}">
                    <td style="background-color:crimson;">
                        <input name="salary" type="number" value="${employeeOne.getSalary()}">
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <input name="salary" type="number" value="${employeeOne.getSalary()}">
                    </td>
                </c:otherwise>
            </c:choose>

            <td>
                <select name="dizhi">
                    <option value="广东省">广东省</option>
                    <option value="湖北省">湖北省</option>
                    <option value="湖南省">湖南省</option>
                </select>

            </td>
            <td>
                <select name="job">
                    <option value="产品经理">产品经理</option>
                    <option value="开发工程师">开发工程师</option>
                    <option value="项目主管">项目主管</option>
                </select>
            </td>
            <td>
                <input name="edit" type="submit" value="修改">
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    $(function () {
        <%--$('input[name="sex"][value="${employeeOne.getSex()}"]').prop('checked', true);--%>
        <%--$('select').find('option[value="${employeeOne.getDepartment().getPid()}"]').prop("selected", true);--%>
        $('select').find('option[value="${employeeOne.getDizhi()}"]').prop("selected", true);
        $('select').find('option[value="${employeeOne.getJob()}"]').prop("selected", true);
    });
    var img = document.getElementsByTagName("img");
    for (let x = 0; x < img.length; x++) {
        img[x].addEventListener("dblclick", function () {
            // 父级的上一个元素节点
            var eid = this.parentNode.previousElementSibling.innerText;
            location = "/uploadfile?eidFile=" + eid;
        });
    }
</script>
</html>
