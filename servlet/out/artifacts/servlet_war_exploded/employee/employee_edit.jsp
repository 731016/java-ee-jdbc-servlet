<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/23
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
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
<form action="/employee/employee_alter" method="post">
    <table>
        <tr>
            <th>员工ID</th>
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
                <input name="ename" type="text" value="${employeeOne.getEname()}">
            </td>
            <td>
                <input type="radio" name="sex" value="男">男
                <input type="radio" name="sex" value="女">女
            </td>
            <td>
                <select name="pid">
                    <c:forEach items="${sessionScope.departmentList}" var="department">
                        <option value="${department.getPid()}">${department.getPname()}</option>
                    </c:forEach>
                </select>
                <%--            ${employeeOne.getDepartment().getPname()}--%>
            </td>

            <c:choose>
                <c:when test="${employeeOne.getSalary()>=8000}">
                    <td style="background-color:olivedrab;"><input name="salary" type="text"
                                                                   value="${employeeOne.getSalary()}"></td>
                </c:when>
                <c:when test="${employeeOne.getSalary()>=6000}">
                    <td style="background-color:crimson;"><input name="salary" type="text"
                                                                 value="${employeeOne.getSalary()}"></td>
                </c:when>
                <c:otherwise>
                    <td>
                        <input name="salary" type="text" value="${employeeOne.getSalary()}">
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
        $('input[name="sex"][value="${employeeOne.getSex()}"]').prop('checked', true);
        $('select').find('option[value="${employeeOne.getDepartment().getPid()}"]').prop("selected",true);
        $('select').find('option[value="${employeeOne.getDizhi()}"]').prop("selected",true);
        $('select').find('option[value="${employeeOne.getJob()}"]').prop("selected",true);
    });
</script>
</html>
